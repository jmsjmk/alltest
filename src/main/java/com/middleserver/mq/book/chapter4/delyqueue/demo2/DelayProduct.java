package com.middleserver.mq.book.chapter4.delyqueue.demo2;

import com.middleserver.mq.book.MqTools;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.util.HashMap;
import java.util.Map;

/**
 * 备胎交换器-是绑定在交换器上面的
 * 死信息交换器-是绑定在队列上面的,死信交换器起作用的地方就是-消息变成死信的时候会发送到这个交换器
 *
 * 要想实现延迟队列，
 * 两个交换器：
 * 两个队列:
 * <p>
 * 1. 第一个交换器绑定第一个队列通信,并且第一个队列有ttl,并且队列设置了-第二个交换器
 * <p>
 * 2. 第二个交换器绑定第二个队列,也就是我们正常消费的队列
 * <p>
 * 流程就是：客户端--发送消息到第一个交换器--->路由到第一个队列--->等待时间超时,消息变成死信---->通过第二个交换器--->路由到第二个队列目标队列
 * <p>
 * -============================
 * 一个交换器可以有绑定无数个队列
 * <p>
 * yoho-core的exchange---amq.topic，所有的生产者都是通过他发送出去,系统默认的交换器
 * 他路由到队列，======延迟队列的交换器--------名字叫yoho.expire
 * <p>
 * 并且基本的延迟队列，也就是上面所说的第一个队列，是公用的。然后路由的自己的消费队列中去。
 * <p>
 * Created by jiamingku on 2019/7/8.
 */
@SuppressWarnings("all")
public class DelayProduct {

    public static void main(String[] args) throws Exception {
        Connection connection = MqTools.getConnection();
        Channel channel = connection.createChannel();

        /** 死信交换器*/
        channel.exchangeDeclare("dlx_test", "topic", true, true, null);

        /**
         * 绑定死信队列的参数-----------------------
         */
        Map<String, Object> mqArgs = new HashMap<String, Object>();
        mqArgs.put("x-dead-letter-exchange", "dlx_test");
        mqArgs.put("x-message-ttl", 10000);

        // 设置了就是用这个，消息发送给echange时候按照这个路由键去路由
        mqArgs.put("x-dead-letter-routing-key", "dlx_test_rk");

        channel.exchangeDeclare("deley_queue_exchange", "topic", true, true, null);
        channel.queueDeclare("deley_queue_test", false, false, false, mqArgs);
        channel.queueBind("deley_queue_test", "deley_queue_exchange", "dlx_test_rk");

        /**
         * 目标队列
         */
        channel.queueDeclare("deley_queue_2_work", false, false, false, null);
        channel.queueBind("deley_queue_2_work", "dlx_test", "dlx_test_rk");

        byte[] messageBodyBytes = "Hello, world!".getBytes();
        byte i = 20;
        while (i-- > 0) {
            Thread.sleep(8000);
            channel.basicPublish("deley_queue_exchange", "dlx_test_rk", new AMQP.BasicProperties.Builder().build(), new byte[]{i});
        }
        System.out.println(" ===complete.. ");
    }
}
