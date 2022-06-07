package com.middleserver.mq.rabbitmq.topic;

import com.middleserver.mq.rabbitmq.RabbitMqTools;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by jiamingku on 2019/4/13.
 */
@SuppressWarnings("all")
public class RabbitmqConsumer {
    private static final String EXCHANGE_NAME = "exchange_name1";
    private static final String ROUTE_KEY = "routingkey_demo";
    private static final String QUEUE_NAME = "queue_demo";
    private static final String IP_ADDRESS = "127.0.0.1";
    private static final int port = 5672;


    public static void main(String[] args) throws Exception {
        Connection connnection = RabbitMqTools.getConnection();
        // 创建信道
        Channel channel = connnection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "direct", true, false, null);
        channel.basicQos(64);
//		channel.exchangeDeclare(EXCHANGE_NAME, "direct", true, false, null);

//		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTE_KEY);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                System.out.println("recv messag = " + consumerTag);
                System.out.println("new String(body) = " + new String(body));
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        channel.basicConsume(QUEUE_NAME, consumer);
        TimeUnit.SECONDS.sleep(51000);
        channel.close();
        connnection.close();


    }
}
