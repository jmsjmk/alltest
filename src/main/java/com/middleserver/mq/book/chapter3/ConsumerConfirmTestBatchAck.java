package com.middleserver.mq.book.chapter3;

import com.middleserver.mq.book.MqTools;
import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by jiamingku on 2019/8/23.
 */
@SuppressWarnings("all")
public class ConsumerConfirmTestBatchAck {

    @Test
    public void testBasicPublish() throws Exception {
        Channel channel = MqTools.getChannel();
        String exchangeName = "test_publish_1";
        String queueName = "test_queue_2";
        String routeKey = "test_pbulish_key";
        // Exchange.DeclareOk exchangeDeclare(String exchange,String type,boolean durable, boolean autoDelete, boolean internal, Map<String, Object> arguments) throws IOException;
        channel.exchangeDeclare(exchangeName, "direct", true, false, null);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, routeKey);
        byte[] messageBytes = "Hello, world".getBytes();
        channel.basicPublish(exchangeName, routeKey, null, messageBytes);
    }

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
        boolean autoAck1 = false;


//        channel1.basicQos(10);
        channel1.basicConsume(queueName, autoAck, "fist-whatareyoudong", new DefaultConsumer(channel1) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                System.out.println("envelope.getDeliveryTag() = " + envelope.getDeliveryTag());
                if (envelope.getDeliveryTag() == 20) {
                    channel1.basicAck(envelope.getDeliveryTag(), true);
                }
            }
        });
    }
}
