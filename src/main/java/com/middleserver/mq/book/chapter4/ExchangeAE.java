package com.middleserver.mq.book.chapter4;

import com.middleserver.mq.book.MqTools;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ReturnListener;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 备份交换器,消息发送到一个交换器的时候，如果没有路由到匹配的队列,或者没有绑定到任何队列的时候，这个消息是丢失的
 *
 * 1.没有指定mandatory的话消息会丢失
 *
 * 2.指定了madatory=true会返回给生产者,设置监听事件来获取原始消息，编程相对来说比较复杂
 *
 * 3.最好用的方法设置备胎交换器--路由不到跑到备胎交换器中()
 * <p>
 *
 * 4.同时设置了备胎交换跟跟mandatory的话mandatory将失效
 *
 * 5.设置方式有两种
 * 5.1 在代码中声明队列的时候指，就跟这个例子中一样
 * 5.2 通过rabbitmqctl set_policy AE "delete4" '{"alternate-exchange":"myAE"}'
 * 这样的设置在管理端看着是不一样的,点开对应的exchange 你就看到不一样了，一个是feature，一个是policy
 *
 * 存在必有道理的说法，你对已经存在的交换器设置备胎交换器,你只能通过策略进行设置
 *
 * Created by jiamingku on 2019/8/24.
 */
@SuppressWarnings("all")
public class ExchangeAE {
    public static void main(String[] args) throws Exception {
        Connection c = MqTools.getConnection();
        Channel channel = c.createChannel();

        /**
         * 备胎交换器-type=fanout-绑定一个队列
         */
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("alternate-exchange", "myAe");
        channel.queueDeclare("unroutedQueue", true, false, false, null);
        channel.exchangeDeclare("myAe", "fanout", true, false, null);
        channel.queueBind("unroutedQueue", "myAe", "");

        /**
         * 工作交换器-设置备胎交换器,指定了备胎属性里面有备胎交换器，在web管理端就能看到这个AE的标示
         */
        channel.exchangeDeclare("test_mandatory1", "direct", true, false, objectMap);
        channel.queueDeclare("test_mandatory_queue", true, false, false, null);
        channel.queueBind("test_mandatory_queue", "test_mandatory", "test_mandatory_key");

        /**
         * 发送的交换器存在，但是路由键不存在的情况,备胎交换器将会屏蔽mandatory
         */
        channel.basicPublish("test_mandatory1", "test_mandatory_key1212", true, null, "hello".getBytes());

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
