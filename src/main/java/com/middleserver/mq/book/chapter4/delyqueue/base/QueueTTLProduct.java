package com.middleserver.mq.book.chapter4.delyqueue.base;

import com.middleserver.mq.book.MqTools;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * TTL:rabbitmq支持两种形式
 * 1.就是队列的ttl,在web管理端能看到TTL是这个队列的属性.
 *
 * 2.设置消息的ttl
 *
 * 这种设置消息的ttl，过期的永远在第一条所以过期全部过期
 */
@SuppressWarnings("all")
public class QueueTTLProduct {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Connection connection1 = MqTools.getConnection();
        Map<String, Object> map = new HashMap<>();
        map.put("x-message-ttl", 10000);

        Channel channel = connection1.createChannel();
        channel.exchangeDeclare("queue_no_consumer", "direct", true);
        channel.queueDeclare("queue_no_consumer_queue", true, false, false, map);

        channel.queueBind("queue_no_consumer_queue", "queue_no_consumer", "testKey");
        byte[] messageBodyBytes = "Hello, world!".getBytes();
        byte i = 20;
        /**
         * 设置两个很大,当没有消费的时候，即使后面的过期了也会暂时在队列中，因为设置消息的ttl 只有处理到才会处理，跟设置队列的ttl最大的区别
         */
        channel.basicPublish("queue_no_consumer", "testKey", new AMQP.BasicProperties.Builder().expiration(String.valueOf(100000000)).build(), messageBodyBytes);
        channel.basicPublish("queue_no_consumer", "testKey", new AMQP.BasicProperties.Builder().expiration(String.valueOf(100000000)).build(), messageBodyBytes);
        while (i-- > 0) {
            channel.basicPublish("queue_no_consumer", "testKey", new AMQP.BasicProperties.Builder().expiration(String.valueOf(1000)).build(), messageBodyBytes);
        }
        System.out.println(" ===complete.. ");

    }
}
