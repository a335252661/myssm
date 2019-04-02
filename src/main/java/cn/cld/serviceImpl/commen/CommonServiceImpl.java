/*
 *  @(#)CommonServiceImpl.java	1.0 2014/04/01
 *
 * Copyright (c) 2014, 上海坦思计算机系统有限公司 版权所有.
 * TES PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package cn.cld.serviceImpl.commen;


import cn.cld.pojo.basic.SimpleServiceResult;
import cn.cld.pojo.basic.SimpleServiceResult.Message;
import cn.cld.service.common.CommonServiceApi;
import cn.cld.untils.SpringUtils;
import cn.cld.untils.StringUtils;
import cn.cld.untils.SystemConstants;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("unchecked")
public class CommonServiceImpl implements CommonServiceApi {

	@Override
	public List<String> getMessages(List<Message> messages) {
		List<String> messageStrs = new ArrayList<String>();
		Map<String, String> messageMap = getMessageFromApplication();
		for (Message message : messages) {
			String pattern = messageMap.get(message.getMsgId());
			Object[] arguments = message.getParams();
			messageStrs.add(MessageFormat.format(pattern, arguments));
		}
		return messageStrs;
	}

	@Override
	public String getMessage(String msgId, Object... arguments) {
		Map<String, String> messageMap = getMessageFromApplication();
		return MessageFormat.format(messageMap.get(msgId), arguments);
	}
	/**
	 * 从applicatiessage信息
	 */
	@Override
	public Map<String, String> getMessageFromApplication(){
		WebApplicationContext webApplicationContext = (WebApplicationContext) SpringUtils.getApplicationContext();
		ServletContext application = webApplicationContext.getServletContext();
		Map<String,String> messageMap = (Map<String, String>) application.getAttribute(SystemConstants.MESSAGE_DATA);
		return messageMap;
	}
}
