package com.middleserver.mq.rabbitmq.topic;

import com.middleserver.mq.rabbitmq.RabbitMqTools;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

/**
 * Created by jiamingku on 2019/4/13.
 */
@SuppressWarnings("all")
public class RabbitmqProducerMacthManyKeyToOneQueue {
    private static final String EXCHANGE_NAME = "exchange_name";
    private static final String QUEUE_NAME = "queue_demo";
    private static final String IP_ADDRESS = "localhost";


    private static final String ROUTE_KEY1 = "*.rabbitmq.*";
    private static final String ROUTE_KEY2 = "com.#";
    private static final int port = 5672;


    public static void main(String[] args) throws Exception {

        Connection connnection = RabbitMqTools.getRootConnection();
        // 创建信道
        Channel channel = connnection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "topic", true, false, null);

        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        // 相同的队列，相同的交换器，绑定了多个key,rabbit支持指定不同的绑定key
        // 也就是一个队列与交换器绑定多个key
        // 发送的时候，key 被交换器多个bindkey匹配-但是都是匹配到一个对象的时候，只会发送一份消息
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTE_KEY1);
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTE_KEY2);
        String message = "hello wolrd";

        // 1.一个队列绑定两个键消息只会发送一份
        // channel.basicPublish(EXCHANGE_NAME, "com.rabbitmq.hello", MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());

        // 2.一个队列绑定两个键消息只会发送一份
        channel.basicPublish(EXCHANGE_NAME, "com.*.*", MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());



        channel.close();
        connnection.close();

    }
}
