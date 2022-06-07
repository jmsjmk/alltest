package com.middleserver.mq.book.chapter3;

import com.middleserver.mq.book.MqTools;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by jiamingku on 2019/8/30.
 */
@SuppressWarnings("all")
public class ConsumerConfirmTestQosTrue {
    public static void main(String[] args) throws Exception, TimeoutException {
        Connection c = MqTools.getConnection();
        // 创建一个渠道
        Channel channel = c.createChannel();
        boolean autoAck = false;
        String exchangeName = "test_publish_2";
        String queueName = "test_queue_1";
        String routeKey = "routeKey";
        channel.exchangeDeclare(exchangeName, "direct", true, false, null);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, routeKey);
        // 创建二个渠道
        Channel channel1 = c.createChannel();
        Channel channel2 = c.createChannel();
        boolean autoAck1 = false;

        /**
         * 当前渠道的所有 都不能大于10，如果这个渠道订阅了相同的队列。
         */
        channel1.basicQos(2000, true);
        channel1.basicConsume("test_queue_1", autoAck, "fist-whatareyoudong", new DefaultConsumer(channel1) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(" ============================ ");
            }
        });
        channel1.basicConsume("test_queue_2", autoAck, "fist-whatareyoudong11111", new DefaultConsumer(channel1) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(" ******************* ");
            }
        });

        /** 另外的渠道压根就没关系....,全部都是非unack了*/
        channel2.basicConsume(queueName, autoAck, "fist-whatareyoudong1", new DefaultConsumer(channel2) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("envelope = " + envelope.getDeliveryTag());
                System.out.println("new String(body) = " + new String(body));
                System.out.println(" ============================ ");
            }
        });
    }
}
