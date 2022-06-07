package com.middleserver.mq.book.chapter4;

import com.middleserver.mq.book.MqTools;
import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 1.优先级是有前提的。消息有堆积的时候才能体现出来优先级队列。生产者速度大于消费者速度
 *
 *
 * Created by jiamingku on 2019/9/25.
 */
public class PriorityQueue {

    @Test
    public void testCreatePriorityQueue() throws Exception {
        Channel channel = MqTools.getChannel();
        Map<String, Object> map = new HashMap<>();
        map.put("x-max-priority", 10);
        channel.queueDeclare("priority_queue", true, false, false, map);
    }

    /**
     * 优先级队列-会将优先级大的消息排在优先级小的前面--，如果没有指定priority的话是0
     *
     * 如果指定了就通过properties这个属性指定
     *
     * @throws Exception
     */
    @Test
    public void testPublishPriorityQueue() throws Exception {
        Channel channel = MqTools.getChannel();

        channel.exchangeDeclare("test_priority_exchange", "fanout", true, false, false, null);

        channel.queueBind("priority_queue", "test_priority_exchange", "d");

        channel.basicPublish("test_priority_exchange", "", false, MessageProperties.PERSISTENT_TEXT_PLAIN, "1".getBytes());

        AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
        builder.priority(5);

        AMQP.BasicProperties properties = builder.build();
        channel.basicPublish("test_priority_exchange", "", properties, "6".getBytes());


        Map<String, Object> map = new HashMap<>();
        map.put("x-max-priority", 10);
        channel.queueDeclare("priority_queue", true, false, false, map);
    }


    /**
     *
     * @throws Exception
     */
    @Test
    public void testConsumerPriorityQueue() throws Exception {
        Channel channel = MqTools.getChannel();
        channel.basicConsume("priority_queue", false, "fist-whatareyoudong",
                new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        System.out.println("new String(body) = " + new String(body));
                        System.out.println("properties = " + properties);
                    }
                });
        Thread.sleep(10000L);
    }

}
