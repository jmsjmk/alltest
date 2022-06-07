package com.middleserver.mq.book.chapter4;

import com.middleserver.mq.book.MqTools;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Created by jiamingku on 2019/8/24.
 */
@SuppressWarnings("all")
public class TestMandatory {

    /**
     * mandatory 这个属性就是必须的找到这个交换器, 如果 找不到这个交换器 也不会触发
     */
    public static void main(String[] args) throws Exception {
        Connection c = MqTools.getConnection();

        Channel channel = c.createChannel();

        channel.exchangeDeclare("test_mandatory", "direct", true, false, null);

        channel.queueDeclare("test_mandatory_queue", true, false, false, null);

        channel.queueBind("test_mandatory_queue", "test_mandatory", "test_mandatory_key");

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
        System.out.println(" = ");

        //channel.basicPublish("test_mandatory", "test_mandatory_key1212", true, null, "hello".getBytes());
//        channel.basicPublish("test_mandatory", "test_mandatory_key123", true, MessageProperties.PERSISTENT_TEXT_PLAIN, "hello".getBytes());
        // --这个方法可以放弃了。没啥用了--- 参数已经过期了
        channel.basicPublish("test_mandatory", "test_mandatory_key", true, true, MessageProperties.PERSISTENT_TEXT_PLAIN, "hello".getBytes());
    }
}
