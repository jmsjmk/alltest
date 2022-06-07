package com.middleserver.mq.book.chapter4.delyqueue.base;

import com.middleserver.mq.book.MqTools;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * TTL:rabbitmq支持两种形式
 * 1.就是队列的ttl
 * 2.设置消息的ttl
 *
 * 这种设置消息的ttl，前面的消息没有过期导致后面的即使过期了也不会从队列中移除
 *
 * 没有消费者存在的时候也是可以的：在投递给消费前判断-就是对头开始判断.过期了就扔掉
 *
 *
 */
@SuppressWarnings("all")
public class MessageTTLProduct {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Connection connection1 = MqTools.getConnection();
        Channel channel = connection1.createChannel();
        channel.exchangeDeclare("ttl_no_consumer", "direct", true);
        channel.queueDeclare("ttl_no_consumer_queue", true, false, false, null);
        channel.queueBind("ttl_no_consumer_queue", "ttl_no_consumer", "testKey");
        byte[] messageBodyBytes = "Hello, world!".getBytes();
        byte i = 20;
        /**
         * 设置两个很大,当没有消费的时候，即使后面的过期了也会暂时在队列中，因为设置消息的ttl 只有处理到才会处理，跟设置队列的ttl最大的区别
         */
        channel.basicPublish("ttl_no_consumer", "testKey", new AMQP.BasicProperties.Builder().expiration(String.valueOf(10000)).build(), messageBodyBytes);
        channel.basicPublish("ttl_no_consumer", "testKey", new AMQP.BasicProperties.Builder().expiration(String.valueOf(10000)).build(), messageBodyBytes);
        while (i-- > 0) {
            channel.basicPublish("ttl_no_consumer", "testKey", new AMQP.BasicProperties.Builder().expiration(String.valueOf(1000)).build(), messageBodyBytes);
        }
        System.out.println(" ===complete.. ");
    }
}
