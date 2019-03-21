package cn.cld.mq;

import cn.cld.untils.SpringUtils;
import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import javax.annotation.Resource;
import javax.jms.Queue;


/**
 *  消息发布管理器
 * @author Tommy
 *
 */
public class PublisherManager implements PublisherServiceApi {
	
	private static final Logger logger = Logger.getLogger(PublisherManager.class);
	
	@Resource
	private JmsTemplate template;


//	/**
//	 * 通过消息类型    发布消息到  对应队列
//	 * @param message
//	 */
//	@Override
//	public void publishTopic(Message message){
//		publishTopic(message, message.getDestination());
//	}
//	
//	/**
//	 * 通过队列名 发布消息
//	 * @param message
//	 * @param queueName
//	 */
//	@Override
//	public void publishTopic(Message message,String topicName){
//		Topic destinationTopic = (Topic)SpringUtils.getBean(topicName);
//		template.convertAndSend(destinationTopic, message);
//	}
//	
	/**
	 * 通过消息类型    发布消息到  对应队列
	 * @param message
	 */
	@Override
	public void publishQueue(Message message){
		publishQueue(message, message.getDestination());
	}
	
	/**
	 * 通过队列名 发布消息
	 * @param message
	 * @param queueName
	 */
	@Override
	public void publishQueue(Message message,String queueName){
		Queue destinationQueue = (Queue)SpringUtils.getBean(queueName);
		logger.debug("***********Sending MQ start!!!Queue=["+queueName+"]************");
		template.convertAndSend(destinationQueue, message);
		logger.debug("***********Sending MQ end!!!Queue=["+queueName+"]************");
	}

}
