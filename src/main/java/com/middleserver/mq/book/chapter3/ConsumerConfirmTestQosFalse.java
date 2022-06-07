package com.middleserver.mq.book.chapter3;

import com.middleserver.mq.book.MqTools;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by jiamingku on 2019/8/30.
 */
@SuppressWarnings("all")
public class ConsumerConfirmTestQosFalse {
    public static void main(String[] args) throws Exception, TimeoutException {
        Connection c = MqTools.getConnection();
        Channel channel = c.createChannel();
        boolean autoAck = false;
        String exchangeName = "test_publish_2";
        String queueName = "test_queue_2";
        String routeKey = "routeKey";
        channel.exchangeDeclare(exchangeName, "direct", true, false, null);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, routeKey);
        Channel channel1 = c.createChannel();
        Channel channel2 = c.createChannel();
        boolean autoAck1 = false;

        /**
         * 信到上面的所有消费者都都遵循这个设置,只是跟当前渠道有关系
         */
        channel1.basicQos(10,false);

        channel1.basicConsume(queueName, autoAck, "fist-whatareyoudong", new DefaultConsumer(channel1) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("envelope = " + envelope.getDeliveryTag());
                System.out.println("new String(body) = " + new String(body));
                System.out.println(" ============================ ");
            }
        });

        channel1.basicConsume(queueName, autoAck, "fist-whatareyoudong1", new DefaultConsumer(channel1) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("envelope = " + envelope.getDeliveryTag());
                System.out.println("new String(body) = " + new String(body));
                System.out.println(" ============================ ");
            }
        });
        /** 另外的渠道压根就没关系....,全部都是非unack了*/
        channel2.basicConsume(queueName, autoAck, "fist-whatareyoudong1", new DefaultConsumer(channel1) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("envelope = " + envelope.getDeliveryTag());
                System.out.println("new String(body) = " + new String(body));
                System.out.println(" ============================ ");
            }
        });
    }
}
