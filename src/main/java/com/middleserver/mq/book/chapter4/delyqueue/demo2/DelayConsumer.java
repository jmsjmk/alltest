package com.middleserver.mq.book.chapter4.delyqueue.demo2;

import com.middleserver.mq.rabbitmq.RabbitMqTools;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Created by jiamingku on 2019/7/8.
 */
@SuppressWarnings("all")
public class DelayConsumer {

    public static void main(String[] args) throws Exception {
        Connection connection = RabbitMqTools.getConnection();

        Channel channel = connection.createChannel();

//        channel.exchangeDeclare("1");

        byte[] messageBodyBytes = "Hello, world!".getBytes();

        byte i = 20;

        channel.basicConsume("deley_queue_2_work", true, "consumer", new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                long deliveryTag = envelope.getDeliveryTag();

//do some work async
                System.out.println(body[0]);
            }
        });

    }
}
