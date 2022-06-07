package com.middleserver.mq.book.chapter4.delyqueue.base;

import com.middleserver.mq.book.MqTools;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Created by jiamingku on 2019/7/8.
 * <p>
 * 1.设置了消息的ttl，没过期的可以被消费者消费，过期的就变成了死信
 * 消费者是消费不了的。
 * <p>
 * 2.如果没有消费者，哪怕过期了消息还是显示那么多条，这中模式只有处理掉这个消息时候判断消息是否过期
 */
@SuppressWarnings("all")
public class MessageTTLConsumer {

    public static void main(String[] args) throws Exception {
        Connection connection = MqTools.getConnection();
        Channel channel = connection.createChannel();
        byte[] messageBodyBytes = "Hello, world!".getBytes();
        byte i = 20;
        channel.basicConsume("delay_no_consumer_queue", false, "consumer", new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                long deliveryTag = envelope.getDeliveryTag();
                System.out.println(body[0]);
            }
        });

    }
}
