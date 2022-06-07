package com.middleserver.mq.rabbitmq.topic;

import com.middleserver.mq.rabbitmq.RabbitMqTools;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

/**
 * Created by jiamingku on 2019/4/13.
 */
@SuppressWarnings("all")
public class RabbitmqProducer {
    private static final String EXCHANGE_NAME = "exchange_name";
    private static final String QUEUE_NAME = "queue_demo";
    private static final String IP_ADDRESS = "localhost";


    private static final String ROUTE_KEY = "routingkey_demo";
    private static final String ROUTE_KEY1 = "routingkey_demo1";
    private static final String ROUTE_KEY2 = "routingkey_demo2";
    private static final int port = 5672;


    public static void main(String[] args) throws Exception {

        Connection connnection = RabbitMqTools.getConnection();
        // 创建信道
        Channel channel = connnection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "direct", true, false, null);
        channel.exchangeDeclare(EXCHANGE_NAME, "direct", true, false, null);
        channel.exchangeDeclare(EXCHANGE_NAME, "direct", true, false, null);
        channel.exchangeDeclare(EXCHANGE_NAME, "direct", true, false, null);
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        //  相同的队列，相同的交换器，绑定了多个key,rabbit支持指定不同的绑定key
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTE_KEY);
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTE_KEY1);
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTE_KEY2);

        String message = "hello wolrd";

        // 发送消息，直接发送就行了。
        //  channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8")); 如果没有指定route_key直接执行就行
        channel.basicPublish(EXCHANGE_NAME, ROUTE_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
        channel.basicPublish(EXCHANGE_NAME, ROUTE_KEY1, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
        channel.basicPublish(EXCHANGE_NAME, ROUTE_KEY2, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());


        channel.close();
        connnection.close();

    }
}
