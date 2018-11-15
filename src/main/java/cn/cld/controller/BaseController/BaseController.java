package cn.cld.controller.BaseController;


import cn.cld.pojo.basic.JsonResult;
import cn.cld.pojo.basic.ServiceResult;
import cn.cld.pojo.basic.SimpleServiceResult;
import cn.cld.untils.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * Controller类的基础类，可以 除掉参数中的空格
 * @author tongc
 *
 */
public class BaseController {
    protected static final Logger logger = LoggerFactory.getLogger(BaseController.class);
	private static final String MSG_NEW_LINE = "<br>";

	/**
	 * 转换SeviceResult对象为JsonResult
	 *
	 * @param serviceResult
	 * @return
	 */
	protected JsonResult convertSeviceResultToJsonResult(SimpleServiceResult serviceResult){
		JsonResult json = new JsonResult();
		json.setOk(serviceResult.isOk());
		json.setComment(serviceResult.getComment());
		json.setMsgType(serviceResult.getMsgType());

		List<ServiceResult<?>.Message> msgs = serviceResult.getMsgs();
		if(msgs != null){
			StringBuffer sb = new StringBuffer();
			for(ServiceResult<?>.Message msg : msgs){
				String msgContent = this.getMessage(msg.getMsgId());
				if(msgContent != null){
					sb.append(MessageFormat.format(msgContent, (Object[])msg.getParams()) + MSG_NEW_LINE);
				}else{
					sb.append(msg.getMsgId());
				}
			}
			json.setMessage(sb.toString());
		}

		if(serviceResult instanceof ServiceResult){
			json.setData(((ServiceResult<?>)serviceResult).getData());
		}

		return json;
	}

	/**
	 * 获取message
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected String getMessage(String key){
		WebApplicationContext webApplicationContext = (WebApplicationContext) SpringUtils.getApplicationContext();
		ServletContext servletContext = webApplicationContext.getServletContext();
		Map<String, String> message=(Map<String, String>) servletContext.getAttribute("message");
		return message.get(key);
	}


}
