package com.middleserver.mq.book.chapter3.basecomponet;

import com.middleserver.mq.book.MqTools;
import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by jiamingku on 2019/8/22.
 * <p>
 * 这个主要也是讲解api的一些参数的设置，
 * D--在web管理端表示持久化
 * AD--在web管理端显示自动删除
 * 相对于交换器来说，他没有内部交换器的说法，-他只有排他的说法-就是在当前connetion,channel可见
 * <p>
 * 只要是排他队列:在连接关系的时候就自动删除，跟你是否设置自动删除没关系
 */
@SuppressWarnings("all")
public class QueueTest {

    /**
     * durable：持久化true 表示队列是否存盘，在服务器重启的时候保证队列原不丢失。
     *
     * @throws Exception
     */
    @Test
    public void testBaseQueueName() throws Exception {
        Connection connection = MqTools.getConnection();
        Channel channel = connection.createChannel();
        /**
         * 这种类型的交换器：这种就是匿名队列交换器排他的，只针对与当前connnection,channel 可见,
         * 自动删除,非持久化的队列
         */
        String queueName = channel.queueDeclare().getQueue();
        System.out.println("queueName = " + queueName);
        /**
         * 但是普通的队列是至少有一个连接,并且全部断开
         */
        channel.queueDeclare("q1", true, false, true, null);
        channel.queueDeclare("q2", false, false, true, null);
        channel.queueDeclare("q3", true, true, true, null);
        Thread.sleep(10000);
    }

    /**
     * 主要是测试排他,其实在实际的使用中不太常用
     * <p>
     * 排他属性的几个注意点<br/>
     * 1.如果你声明了排他队列,不能创建相同的队列名字，在不同的连接中
     * 普通的非排他队列,在不同的连接中可以声明这个队列，如果属性相同的情况下
     *
     * @throws Exception
     */
    @Test
    public void testExclusiveDeclare() throws Exception {
        // 创建两个连接
        Connection connection1 = MqTools.getConnection();
        Connection connection2 = MqTools.getConnection();
        // 创建三个渠道,2个涞源一个connection,
        Channel channel1FromConnection1 = connection1.createChannel();
        Channel channel2FromConnection1 = connection1.createChannel();
        Channel channel1FromConnection2 = connection2.createChannel();
        // AMQP.Queue.DeclareOk queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete,Map<String, Object> arguments) throws IOException;
        /**
         * 排他队列-同名字的队列-----在同一个连接中可以声明
         * 非排他队列是跨连接都可以生情的
         */
        channel1FromConnection1.queueDeclare("test2_queueName1", true, true, true, null);
        channel2FromConnection1.queueDeclare("test2_queueName1", true, true, true, null);
        try {
            /**
             * 排他队列不允许其他的channel生成
             */
            channel1FromConnection2.queueDeclare("test2_queueName1", true, true, true, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!channel1FromConnection2.isOpen()) {
            System.out.println(" channel1FromConnection2 已经关闭 ");
        }

        if (connection2.isOpen()) {
            System.out.println(" connection2  is open  ");
        }

        if (channel1FromConnection1.isOpen()) {
            System.out.println(" chaannel is open  ");
        }
        if (channel2FromConnection1.isOpen()) {
            System.out.println(" chaannel is open  ");
        }

        /**
         * 排他队列只要断开连接的话，这个队列就直接删除
         */
        channel1FromConnection1.queueDeclare("test2_queueName1222", true, true, false, null);


        Thread.sleep(100000L);
    }

    /**
     * 主要是测试排他,其实在实际的使用中不太常用
     * <p>
     * 排他属性的几个注意点<br/>
     * 1.排他队列，发送交换器可以发送进去，开始以为不能发送进去，。。。看来的动手实践啊，但是排他队列只能在当前的连接可以消费
     *
     * @throws Exception
     */
    @Test
    public void testExclusivePublish() throws Exception {
        // 创建两个连接
        Connection connection1 = MqTools.getConnection();
        Connection connection2 = MqTools.getConnection();
        // 创建三个渠道,2个涞源一个connection,
        Channel channel1FromConnection1 = connection1.createChannel();
        Channel channel2FromConnection1 = connection1.createChannel();

        Channel channel1FromConnection2 = connection2.createChannel();
        // AMQP.Queue.DeclareOk queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete,Map<String, Object> arguments) throws IOException;

        channel1FromConnection1.queueDeclare("test2_queueName1", true, true, true, null);
        channel2FromConnection1.queueDeclare("test2_queueName1", true, true, true, null);
        channel1FromConnection1.queueBind("test2_queueName1", "amq.topic", "rk");
        channel2FromConnection1.queueBind("test2_queueName1", "amq.topic", "rk");
        channel1FromConnection1.basicPublish("amq.topic", "rk", MessageProperties.PERSISTENT_TEXT_PLAIN, "hello".getBytes());
        channel2FromConnection1.basicPublish("amq.topic", "rk", MessageProperties.PERSISTENT_TEXT_PLAIN, "hello".getBytes());

        try {
            /**
             *  排他交换队列-可以发送进去--但是不能消息-可以在终端里面看到消息确实是3条，但是web管理端不能消费这个消息
             */
            channel1FromConnection2.basicPublish("amq.topic", "rk", MessageProperties.PERSISTENT_TEXT_PLAIN, "what".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!channel1FromConnection2.isOpen()) {
            System.out.println(" channel1FromConnection2 已经关闭 ");
        }

        if (connection2.isOpen()) {
            System.out.println(" connection2  is open  ");
        }

        if (channel1FromConnection1.isOpen()) {
            System.out.println(" chaannel is open  ");
        }
        if (channel2FromConnection1.isOpen()) {
            System.out.println(" chaannel is open  ");
        }
        Thread.sleep(100000L);
    }


    @Test
    public void testExclusiveConsumer() throws Exception {
        // 创建两个连接
        Connection connection1 = MqTools.getConnection();
        Connection connection2 = MqTools.getConnection();
        // 创建三个渠道,2个涞源一个connection,
        Channel channel1FromConnection1 = connection1.createChannel();
        Channel channel2FromConnection1 = connection1.createChannel();
        Channel channel1FromConnection2 = connection2.createChannel();
        // AMQP.Queue.DeclareOk queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete,Map<String, Object> arguments) throws IOException;
        channel1FromConnection1.queueDeclare("test2_queueName1", true, true, true, null);
        channel2FromConnection1.queueDeclare("test2_queueName1", true, true, true, null);
        channel1FromConnection1.queueBind("test2_queueName1", "amq.topic", "rk");
        channel2FromConnection1.queueBind("test2_queueName1", "amq.topic", "rk");
        channel1FromConnection1.basicPublish("amq.topic", "rk", MessageProperties.PERSISTENT_TEXT_PLAIN, "hello".getBytes());
        channel2FromConnection1.basicPublish("amq.topic", "rk", MessageProperties.PERSISTENT_TEXT_PLAIN, "hello".getBytes());

        try {
            /**
             *  排他交换队列-可以发送进去--但是不能消费消息-可以在终端里面看到消息确实是3条，但是web管理端不能消费这个消息
             */
            channel1FromConnection2.basicPublish("amq.topic", "rk", MessageProperties.PERSISTENT_TEXT_PLAIN, "what".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        /** channel2FromConnection1  如果换成为其他的连接 直接就报错了，排他队列对当前连接跟channle可见*/
        /**
         * channel1FromConnection1
         * channel2FromConnection1
         */
        channel1FromConnection2.basicConsume("test2_queueName1", false, "fist-whatareyoudong",
                new DefaultConsumer(channel2FromConnection1) {
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        if (channel2FromConnection1.isOpen()) {
                            System.out.println("is open!.");
                        }
                        /**
                         * 这里面发生异常之后，这个连接暂时是消息了，还会接受下面的消息，但是都跑unack里面去了
                         */
                        System.out.println(" ============================ " + new String(body));
                    }
                });


        if (!channel1FromConnection2.isOpen()) {
            System.out.println(" channel1FromConnection2 已经关闭 ");
        }

        if (connection2.isOpen()) {
            System.out.println(" connection2  is open  ");
        }

        if (channel1FromConnection1.isOpen()) {
            System.out.println(" chaannel is open  ");
        }
        if (channel2FromConnection1.isOpen()) {
            System.out.println(" chaannel is open  ");
        }
        while (true) {
            try {
                if (channel2FromConnection1.isOpen()) {
//                    System.out.println(" chaannel is open  ");
                }
            } catch (Exception e) {
                System.out.println("error");
                System.out.println("error");
                System.out.println("error");
                System.out.println("error");
            }
        }
    }

    // -------------------------------------------测试排他结束--------------------------------------------------------

    /**
     * 测试passive,
     * 没有passive的判断是否存在-不存在就创建，
     * 有passive的存在就抛异常
     *
     * @throws Exception
     */
    @Test
    public void testPassive() throws Exception {
        Channel channel1 = MqTools.getChannel();
        // 不存在就创建
        channel1.queueDeclare("test3_queueName21", true, false, true, null);
        try {
            // 不存在就抛出异常
            AMQP.Queue.DeclareOk ok = channel1.queueDeclarePassive("test3_queueName312121212");
            System.out.println("ok = " + ok);
        } catch (Exception e) {
            // 同样也会关闭连接
            e.printStackTrace();
        }
        if (channel1.isOpen()) {
            System.out.println(" true ");
        } else {
            System.out.println(" false");
        }
    }

    // -------------------------------------测试队列删除

    /**
     * 删除一个不存在的交换器，删除一个不存在的队列都是可以的不会抛出异常
     *
     * @throws Exception
     */
    @Test
    public void testQueueDelete() throws Exception {
        Channel channel1 = MqTools.getChannel();
        channel1.queueDeclare("test3_queueName_isnotempty", true, false, true, null);
        String queueName = "test3_queueName_isnotempty";

        String qexist = "eeee";
        channel1.exchangeDeclare("test3_queueName_isnotempty_exchange", "direct", true, true, false, null);
        channel1.queueDeclare(queueName, true, false, true, null);
        channel1.queueBind(queueName, "test3_queueName_isnotempty_exchange", "fist_routingKey");
        try {
            /** 队列的名字不存在也可以删除--瞎写一个队列都可以删除*/
            channel1.queueDelete(qexist, true, true);
            System.out.println(" 可以直接删除。。。 ");
        } catch (Exception e) {
            // 同样也会关闭连接
            e.printStackTrace();
        }
        if (channel1.isOpen()) {
            System.out.println(" true ");
        } else {
            System.out.println(" false");
        }
    }
    // -------------------------------------------------------------------------------------------------------------------------

    /**
     * 一个队列可以绑定n个键附加到一个交换器上，你在web管理端解除绑定的时候，一个个的解绑
     *
     * @throws Exception
     */
    @Test
    public void testMutiple() throws Exception {
        Channel channel1 = MqTools.getChannel();
        channel1.queueDeclare("test_unbind", true, false, true, null);
        channel1.exchangeDeclare("test_unbind", "direct", true, true, false, null);
        String queueName = "test_unbind_queue";


        channel1.queueDeclare(queueName, true, false, true, null);
        channel1.queueBind(queueName, "test_unbind", "fist_routingKey");
        channel1.queueBind(queueName, "test_unbind", "second_routingKey");
        channel1.queueBind(queueName, "test_unbind", "third_routingKey");

        try {
            // 解除绑定关系
            channel1.queueUnbind(queueName, "test_unbind", "fist_routingKey");
        } catch (Exception e) {
            // 同样也会关闭连接
            e.printStackTrace();
        }
        if (channel1.isOpen()) {
            System.out.println(" true ");
        } else {
            System.out.println(" false");
        }
    }

    // ---------------------------------------------------------------------------------------------------------------------------------------

    /**
     * 检查是否存在这个交换器,不存在就抛出异常。其实这个特性很重要的，如何mq在使用预分配的使用的话这个还是很关键的。
     *
     * @throws Exception
     */
    @Test
    public void test4() throws Exception {
        Connection connection = MqTools.getConnection();
        Channel channel = connection.createChannel();
        try {
            // 但是对于发送一个不存在的交换器，这个channel关闭的很慢。这个需要看下源代码测试下
            /**
             {@link com.middleserver.mq.book.chapter3.PublishTestNonExchange}
             */
            channel.exchangeDeclarePassive("fist_exchange31");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (channel.isOpen()) {
            System.out.println(" channel is open ");
        } else {
            System.out.println(" 发生异常了！channel会关闭 ");
        }
    }
}
