package cn.cld.mq;

import cn.cld.untils.JSONUtil;
import java.io.Serializable;


/**
 * 消息基类
 *
 */
public class Message implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * TOPIC or QUEUE 队列名
	 */
	protected String destination;
	
	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	@Override
	public String toString(){
		return JSONUtil.toJsonString(this);
	}
}
