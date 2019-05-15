package cn.cld.controller;


import cn.cld.pojo.basic.SystemSession;
import cn.cld.untils.DateTimeUtils;
import cn.cld.untils.RandomValidateCode;
import cn.cld.untils.constant.CommonConstants;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.tools.view.servlet.VelocityViewServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;


@Controller
public class UserController extends VelocityViewServlet {

    @Resource(name="redisTemplate")
    protected ListOperations<String, String> redis;

    private long intervalInMS = 300000L;//请求时间段, 以ms为单位
    private int requestMaxCount = 2;//时间段内, 请求次数上限, 其余将不会处理
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("login")
    public ModelAndView userlogin(ModelAndView mav,HttpServletRequest request, Model model){
        System.out.println("登录系统++++++++++++");

        SystemSession session = getSystemSession(request);

        String captcha = request.getParameter("captcha");
        String user = request.getParameter("user");
        String password = request.getParameter("password");

        System.out.println(user);
        System.out.println(password);
        System.out.println(captcha);
        System.out.println("正确验证码： "+session.getAttribute(RandomValidateCode.RANDOMCODEKEY).toString());

        /**
         * @author by cld
         * @date 2019/5/13 13:38
         * @description: 防止暴力破解，做ip限制
         */

        //用户ip
        String loginIp = getIP(request);
        //数据缓存至redis中
        long currentTimeInMS = Calendar.getInstance().getTimeInMillis();
        String frequencyDetail = redis.leftPop("loginIp");
        if(StringUtils.isEmpty(frequencyDetail)){ //redis中未查询到记录
            logger.info("ip: {}, 第一次请求", loginIp);
            //初始化信息到redis
            resetRedisCache(loginIp, currentTimeInMS, 1);
        }else {
            //不是第一次请求, 先比较时间
            JSONObject obj = JSONObject.parseObject(frequencyDetail);
            long requestTime = obj.getLong("requestTime");
            logger.info("当前时间: {}, 上一次请求时间: {}, ,时间差: {}, ", currentTimeInMS,requestTime,currentTimeInMS-requestTime);
            if(currentTimeInMS-requestTime >= this.intervalInMS){ //时间间隔较长, 重新记数
                logger.info("ip: {}, 间隔过长, 重新计数", loginIp);

                resetRedisCache(loginIp, currentTimeInMS, 1);
            }else {
                //有效计时时间内
                int requestCount = obj.getIntValue("requestCount");
                if(requestCount > this.requestMaxCount){ //请求过于频繁
                    logger.info("{} 请求过于频繁", loginIp);
                    resetRedisCache(loginIp, requestTime, requestCount + 1);
                    //return false;
                    String timeDifference = DateTimeUtils.getTimeDifference(new Date(intervalInMS), new Date(currentTimeInMS - requestTime));

                    //过于频繁，锁定ip
                    mav.addObject("errorMessage","错误次数过多，ip被锁定，请"+timeDifference+"秒后重试！");
                    return mav;
                } else{
                    //有效计数时间内, 计数+1
                    logger.debug("ip: {}, 请求计数: {}", loginIp, requestCount);
                    resetRedisCache(loginIp, requestTime, requestCount + 1);
                }
            }
        }


        //正常请求，后续做验证操作 ，dosomething，验证成功返回视图











        mav.setViewName("home");
        return mav;
    }


    private void resetRedisCache(String ip, long currentTimeInMS, int count){
        JSONObject obj = new JSONObject();
        obj.put("requestTime",currentTimeInMS);
        obj.put("requestCount", count);
        redis.leftPush("loginIp", obj.toJSONString());
    }
    private String getIP(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            int index = ip.indexOf(",");
            if(index != -1){
                return ip.substring(0,index);
            }else{
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            return ip;
        }
        return request.getRemoteAddr();
    }

    /**
     * 生成验证码
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/captchaImage.htm")
    public void captchaImage(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("生成验证码");

        response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        RandomValidateCode randomValidateCode = new RandomValidateCode();
        try {
            SystemSession session = getSystemSession(request);
            randomValidateCode.getRandcode(session, response);//输出图片方法
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    /**
     * 获取系统Session
     * @param request
     * @return
     */
    protected SystemSession getSystemSession(HttpServletRequest request){
        SystemSession session = (SystemSession)request.getAttribute(CommonConstants.SHOP_SESSION_KEY);
        return session;
    }

}
