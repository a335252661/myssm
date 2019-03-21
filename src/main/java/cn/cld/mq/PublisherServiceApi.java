package cn.cld.mq;

/**
 * 队列发布接口
 *
 */
public interface PublisherServiceApi {

    /**
     * TOPIC
     public void publishTopic(Message message);
     public void publishTopic(Message message,String topicName);
     */

    /**
     * 通过消息类型    发布消息到  对应队列
     * @param message
     */
    public void publishQueue(Message message);

    /**
     * 通过队列名 发布消息
     * @param message
     * @param queueName
     */
    public void publishQueue(Message message, String queueName);
}
