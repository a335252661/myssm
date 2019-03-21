package cn.cld.mq.consumer;

import cn.cld.mq.messageVo.TestQueueMessage;
import cn.cld.service.MQTestServiceApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.net.InetAddress;
public class TestQueueConsumer {
	private static final Logger logger = LoggerFactory.getLogger(TestQueueConsumer.class);

	@Resource
	private MQTestServiceApi MQTestService;

	/**
	 * 消费者处理方法
	 * 
	 * @param message
	 */
	public void receive(final TestQueueMessage message) {
		String hostname;
		try {
			hostname = InetAddress.getLocalHost().getHostName();
		} catch (Exception e) {
			hostname = "";
		}
		String param = message.getTestMQParam();
		if (logger.isDebugEnabled()) {
			logger.debug("receive hostname=["+hostname+"] MQ测试消息：{"+param+"}");
		}
		//调用service方法
		MQTestService.testMQ();
		
	}

}
