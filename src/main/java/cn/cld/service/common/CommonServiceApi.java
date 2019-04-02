/*
 *  @(#)CommonServiceApi.java	1.0 2014/04/01
 *
 * Copyright (c) 2014, 上海坦思计算机系统有限公司 版权所有.
 * TES PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package cn.cld.service.common;

import cn.cld.pojo.basic.SimpleServiceResult.Message;

import java.util.List;
import java.util.Map;


public interface CommonServiceApi {


	/**
	 * 获取message文本
	 *
	 * @param message message参数
	 * @return
	 */
	public List<String> getMessages(List<Message> message);

	/**
	 * 根据msgId及参数format消息
	 *
	 * @param msgId
	 * @param arguments
	 * @return
	 */
	public String getMessage(String msgId, Object... arguments);

	/**
	 * 从application中取所有message信息
	 */
	public Map<String, String> getMessageFromApplication();

}
