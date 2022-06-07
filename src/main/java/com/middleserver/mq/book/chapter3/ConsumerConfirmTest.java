package com.middleserver.mq.book.chapter3;

import com.middleserver.mq.book.MqTools;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by jiamingku on 2019/8/23.
 */
@SuppressWarnings("all")
public class ConsumerConfirmTest {

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

        /**
         * 消息确认方式：autoAck，这种方式叫开发者自动确认消息
         * 1.basicAck告诉客户端可以消费了
         * 2.消费者明确的告诉这个消息拒绝是否回到队列.true表示可以回到队列中,false表示不可以回到队列中
         * 3.消费者明确消息是否进入队列。
         * 4.未确认的消息是否从新发送，或者重新回到队列,
         *
         错误消息
         *Channel error on connection <0.6210.4> (10.10.134.67:57652 -> 10.10.132.164:5672, vhost: '/', user: 'root'), channel 2:
         operation basic.reject caused a channel exception precondition_failed: unknown delivery tag 1
         * **** 这是消息投递到消息者这里面的一个确认机制，但是这集中确认机制不能组合使用，组合使用，就会导致channel关闭 *******
         *
         * 道理应该很简单：你确认一个消息，服务器移除一个消息，但是这个消息有同样的标签，你在拿这个标签去做重复发送，服务器压根就是不接受的。只鹅姐关闭到这个channel
         *  channel的关闭不是那么利索就关闭了，客户端还是会收到消息，已经投递给你的，你还是可以接受到的，但是你确认是确认不了的
         *
         *  这集中模式不能组合使用就行了。这个源代码必须的看懂。2个月。时间。
         */
        channel1.basicQos(10);
        channel1.basicConsume(queueName, autoAck, "fist-whatareyoudong", new DefaultConsumer(channel1) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("envelope.getDeliveryTag() = " + envelope.getDeliveryTag());
                System.out.println("new String(body) = " + new String(body));
                System.out.println(" ============================ ");
                // 1.
                channel1.basicAck(envelope.getDeliveryTag(), false);

                // 2
                channel1.basicReject(envelope.getDeliveryTag(), false);

                // 3.
                channel1.basicNack(envelope.getDeliveryTag(), true, false);

                // 4.
                channel1.basicRecover();
            }
        });
    }
}
