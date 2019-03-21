package cn.cld.mq.messageVo;

import cn.cld.mq.Message;

public class TestQueueMessage extends Message {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6631209028899799501L;
	// 消息参数
	private String testMQParam;
	
	public TestQueueMessage(){
		setDestination("TEST_QUEUE");
	}

	public String getTestMQParam() {
		return testMQParam;
	}

	public void setTestMQParam(String testMQParam) {
		this.testMQParam = testMQParam;
	}



}
