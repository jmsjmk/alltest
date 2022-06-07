package com.middleserver.mq.book.chapter4;

import com.middleserver.mq.book.MqTools;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ReturnListener;

import java.io.IOException;

/**
 * 备份交换器,消息发送到一个交换器的时候，如果没有路由到匹配的队列，
 * 1.没有指定mandatory的话消息会丢失
 * 2.指定了madatory=true会返回给生产者,设置监听事件来获取原始消息
 * 3.最好用的方法设置备胎交换器--路由不到跑到备胎交换器中
 * <p>
 * Created by jiamingku on 2019/8/24.
 *
 *
 */
@SuppressWarnings("all")
public class ExchangeNoAE {
    public static void main(String[] args) throws Exception {
        Connection c = MqTools.getConnection();
        Channel channel = c.createChannel();
        /**
         * 工作交换器-设置备胎交换器
         */
        channel.exchangeDeclare("test_mandatory_noae", "direct", true, false, null);
        channel.queueDeclare("test_mandatory_queue", true, false, false, null);
        channel.queueBind("test_mandatory_queue", "test_mandatory_noae", "test_mandatory_key");

        /**
         * 发送的交换器存在，但是路由键不存在的情况,没有路由到队列---mq打回到生产者
         * mandatory=false的时候消息丢失了
         */
        channel.basicPublish("test_mandatory_noae", "test_mandatory_key1212", true, null, "hello".getBytes());
        channel.addReturnListener(new ReturnListener() {
            @Override
            public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("replyCode = " + replyCode);
                System.out.println("replyText = " + replyText);
                System.out.println("exchange = " + exchange);
                System.out.println("routingKey = " + routingKey);
                System.out.println("properties = " + properties);
                System.out.println("new String = " + new String(body));
            }
        });
    }
}
