package com.middleserver.mq.book.chapter3.basecomponet;

import com.middleserver.mq.book.MqTools;
import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by jiamingku on 2019/8/28.
 */
@SuppressWarnings("all")
public class AutoDeleteTest {

    /**
     * 队列是否自动删除---不关心是否有exchange的绑定.跟消费者有关系,消费者一旦断开这个队列就删除了
     * @throws Exception
     */
    @Test
    public void queueAutoDeleteTest() throws Exception {
        MqTools mqTools = new MqTools();
        Connection connection = mqTools.getConnection();
        Channel channel = connection.createChannel();
        /**
         * Exchange.DeclareOk exchangeDeclare(String exchange,
         String type,
         boolean durable,
         boolean autoDelete,
         boolean internal,
         Map<String, Object> arguments) throws IOException;
         */
        // 初始化了,但是没绑定，设置了自动删除也不会自动删除。 在程序退出的时候也不会自动删除
        channel.exchangeDeclare("exchange_test2", "topic", true, false, false, null);

        channel.queueDeclare("queue_autodelete_true", true, false, true, null);
        // 自动删除但是没有使用过--不会自动删除
        channel.queueDeclare("queue_autodelete_true1", true, false, true, null);

        channel.queueBind("queue_autodelete_true1", "exchange_test2", "test");
        channel.queueBind("queue_autodelete_true", "exchange_test2", "test");

        // 自动删除但是使用过-消费者已断开就会自动删除，跟队列是否持久化没关系
        channel.basicConsume("queue_autodelete_true", false, "consumer", new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("consumerTag = " + consumerTag);
                long deliveryTag = envelope.getDeliveryTag();
                System.out.println("deliveryTag = " + deliveryTag);
                String rk = envelope.getRoutingKey();
                System.out.println("rk = " + rk);
                //do some work async
                System.out.println(body[0]);
            }
        });
        System.out.println("\"dddd\" = " + "dddd");
    }

    /**
     * 所谓的自动删除,前提至少的使用过一次,
     * 1.跟连接是否断开没关系
     * 2.跟是否持久化都没有关系
     *
     * @throws Exception
     */
    @Test
    public void exchangeTest1() throws Exception {
        MqTools mqTools = new MqTools();
        Connection connection = mqTools.getConnection();
        Channel channel = connection.createChannel();
        /**
         Exchange.DeclareOk exchangeDeclare(String exchange,
         String type,
         boolean durable,
         boolean autoDelete,
         boolean internal,
         Map<String, Object> arguments) throws IOException;
         */
        // 初始化了,但是没绑定，设置了自动删除也不会自动删除。
        channel.exchangeDeclare("exchange_test_autodelete_false", "topic", true, false, false, null);
        channel.exchangeDeclare("exchange_test_autodelete_true", "topic", true, true, false, null);
        channel.queueDeclare("queue2_autodelete_true", true, false, true, null);
        channel.queueDeclare("queue2_autodelete_false", true, false, false, null);
    }


    /**
     * 所谓的自动删除,前提至少的使用过一次
     * 1.exchange=关联exhange,或者queue,
     * 2.queue =关联的消费者
     *
     * @throws Exception
     */
    @Test
    public void exchangeTest2() throws Exception {
        MqTools mqTools = new MqTools();

        Connection connection = mqTools.getConnection();

        Channel channel = connection.createChannel();
        /**
         Exchange.DeclareOk exchangeDeclare(String exchange,
         String type,
         boolean durable,
         boolean autoDelete,
         boolean internal,
         Map<String, Object> arguments) throws IOException;
         */
        // 初始化了,但是没绑定，设置了自动删除也不会自动删除。
        channel.exchangeDeclare("exchange_test_autodelete_false", "topic", true, false, false, null);
        channel.exchangeDeclare("exchange_test_autodelete_true", "topic", true, true, false, null);
        channel.queueDeclare("queue2_autodelete_true", true, false, true, null);
        channel.queueDeclare("queue2_autodelete_false", true, false, false, null);
        // 在web管理端进行一次解绑操作-自动删除的交换器-使用过一次并且全部解绑了-就会自动删除
        channel.queueBind("queue2_autodelete_true", "exchange_test_autodelete_true", "test");

        channel.queueBind("queue2_autodelete_false", "exchange_test_autodelete_false", "test");
        channel.basicPublish("exchange_test_autodelete_true", "test", null, "hello".getBytes());
    }
}
