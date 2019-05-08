package cn.cld.untils;

import cn.cld.pojo.basic.SystemSession;
import cn.cld.untils.constant.CommonConstants;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 * 自定义Session的工具类
 * @author Tommy
 *
 */
public class SessionManager {
	/**
	 * 获取一个session
	 * @param request
	 * @param response
	 * @return
	 */
	public static SystemSession getSession(HttpServletRequest request, HttpServletResponse response){
		SystemSession session = null;
		Cookie cookie = CookieUtil.getCookieByName(request, CommonConstants.SHOP_SESSION_KEY);
		if(cookie == null){
			session = createSession(request,response);
		}else{
			RedisTemplate<String,SystemSession> redisTemplate = getRedisTemplate();
			session = (SystemSession)redisTemplate.opsForValue().get(cookie.getValue());
			if(session == null){
				session = createSession(request,response);
			}else{
				/**
				 * 设置session 有效时间
				 */
				redisTemplate.expire(session.getSessionId(), CommonConstants.SHOP_SESSION_TIMEOUT, TimeUnit.SECONDS);
			}
		}
		return session;
	}
	
	/**
	 * 获取一个session
	 * @return
	 */
	public static SystemSession getSession(String sessionId){
		SystemSession session = null;
		RedisTemplate<String,SystemSession> redisTemplate = getRedisTemplate();
		session = (SystemSession)redisTemplate.opsForValue().get(sessionId);
		return session;
	}
	
	/**
	 * 新建一个session
	 * @param request
	 * @param response
	 * @return
	 */
	private static SystemSession createSession(HttpServletRequest request, HttpServletResponse response){
		SystemSession session = new SystemSession();;
		String sessionKey = generateSessionKey();
		RedisTemplate<String,SystemSession> redisTemplate = getRedisTemplate();
		session.setSessionId(sessionKey);
		redisTemplate.opsForValue().set(sessionKey, session);
		/**
		 * 设置session 有效时间
		 */
		redisTemplate.expire(sessionKey, CommonConstants.SHOP_SESSION_TIMEOUT, TimeUnit.SECONDS);
		response.setHeader("Set-Cookie",  CommonConstants.SHOP_SESSION_KEY+"="+sessionKey+";Path=/;HTTPOnly");
		return session;
	}
	/**
	 * 通过uuid生成sessionID
	 * @return
	 */
	private static String generateSessionKey(){
		String sessionKey = UUID.randomUUID().toString(); 
		return sessionKey.replace("-","");
	}
	/**
	 * 更新session数据内容
	 * @param sessionKey
	 * @param session
	 */
	public static void updateSession(String sessionKey,SystemSession session){
		RedisTemplate<String,SystemSession> redisTemplate = getRedisTemplate();
		redisTemplate.opsForValue().set(sessionKey, session);
		/**
		 * 设置session 有效时间
		 */
		redisTemplate.expire(sessionKey, CommonConstants.SHOP_SESSION_TIMEOUT, TimeUnit.SECONDS);
	}
	/**
	 * 删除session,使其失效
	 * @param sessionKey
	 */
	public static void deleteSession(String sessionKey){
		RedisTemplate<String,SystemSession> redisTemplate = getRedisTemplate();
		redisTemplate.delete(sessionKey);
	}
	
	private static RedisTemplate<String,SystemSession> getRedisTemplate(){
		@SuppressWarnings("unchecked")
		RedisTemplate<String,SystemSession> redisTemplate = (RedisTemplate<String,SystemSession>)SpringUtils.getBean("redisTemplate");
		return redisTemplate;
	}
}
