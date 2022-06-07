package com.middleserver.mq.book.chapter3.cluster;

import com.middleserver.mq.book.MqTools;
import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by jiamingku on 2019/8/23.
 */
@SuppressWarnings("all")
public class ClusterConsumerTest {

    /**
     * 推模式
     * consumer 发生异常时候 没有变化到unack队列中
     */
    @Test
    public void test1() throws Exception {
        Channel channel = MqTools.getChannel();
        boolean autoAck = false;
        String queueName = "add_queue_163";
        channel.basicConsume(queueName, autoAck, "fist-whatareyoudong",
                new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        System.out.println("consumerTag = " + consumerTag);
                        System.out.println("envelope = " + envelope);
                        long deliveryTag = envelope.getDeliveryTag();
                        System.out.println("deliveryTag = " + deliveryTag);
                        String routingKey = envelope.getRoutingKey();
                        System.out.println("routingKey = " + routingKey);
                        System.out.println("======");
                        System.out.println("properties = " + properties);
                        String contentType = properties.getContentType();
                        System.out.println("contentType = " + contentType);
                        System.out.println("new String(body) = " + new String(body));
                        int size = properties.getHeaders().size();
                        if (size > 0) {
                            System.out.println("properties111 = " + properties.getHeaders().toString());
                        }
                        System.out.println(" ============================ ");
                    }
                });
        Thread.sleep(10000000L);
    }


    /**
     * 推模式
     *
     */
    @Test
    public void test12() throws Exception {
        Channel channel = MqTools.getChannel();
        boolean autoAck = false;
        String queueName = "add_queue_163";
        channel.basicConsume(queueName, autoAck, "fist-whatareyoudong",
                new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        System.out.println("consumerTag = " + consumerTag);
                        System.out.println("envelope = " + envelope);
                        System.out.println(" = " + new String(body) );
                        System.out.println(" ============================ ");
                        channel.basicAck(envelope.getDeliveryTag(),false);
                    }
                });
        Thread.sleep(10000000L);
    }
}
