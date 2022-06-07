package com.middleserver.mq.book.chapter1;

import com.middleserver.mq.rabbitmq.RabbitMqTools;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

/**
 * Created by jiamingku on 2019/7/9.
 */
@SuppressWarnings("all")
public class RabbitProducer {
    private static final String EXCHANGE_NAME = "exchange_demo";
    private static final String ROUTING_KEY = "routingkey_demo";
    private static final String QUEUE_NAME = "queue_demo";
    public static void main(String[] args) throws Exception {
        // 创建链接
        Connection connection = RabbitMqTools.getRootConnection();
        // 创建信道
        Channel channel = connection.createChannel();
        //创建一个 type="direct"、持久化的、非自动删除的交换器
        channel.exchangeDeclare(EXCHANGE_NAME, "direct", true, false, null);
        //创建一个持久化、非排他的、非自动删除的队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        //队列绑定交换器-并且指定bindKey
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "info");
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "debug");
        //发送一条持久化的消息: hello world !
        String message = "Hello World !";
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
        channel.basicPublish(EXCHANGE_NAME, "info", MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
        channel.basicPublish(EXCHANGE_NAME, "debug", MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
        //关闭资源
        channel.close();
        connection.close();
    }
}
