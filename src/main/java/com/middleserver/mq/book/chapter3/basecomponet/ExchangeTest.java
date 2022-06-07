package com.middleserver.mq.book.chapter3.basecomponet;

import com.middleserver.mq.book.MqTools;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import org.junit.Test;

/**
 * Created by jiamingku on 2019/8/22.
 * 就是声明exchang的api的方法测试, exchange-是没匿名交换器的说法的,但是有匿名队列的说法
 * <p>
 * {@link Channel#exchangeDeclare(java.lang.String, java.lang.String, boolean, boolean, boolean, java.util.Map)}
 * 是否持久化--在mq的web管理端显示D
 * 是否自动删除--在mq的web管理端显示AD
 * 是否是内部交换-在mq的web管理端显示I
 */
@SuppressWarnings("all")
public class ExchangeTest {

    // -------------------------------------------不可变类-java引用传递-------
    @Test
    public void t() {
        String s = "fuck";
        String b = a(s);
        System.out.println("b = " + b);
        System.out.println("s = " + s);
    }

    String a(String s) {
        return s = 1000 + "";
    }
    // ----------------------------------------------------------------------------

    /**
     * 测试自动删除交换器====交换器的自动删除
     * <p>
     * 无论是交换器的删除还是队列的删除,没有使用过的时候，设置自动删除在在连接断开的时候是不会删除的
     *
     * @throws Exception
     */
    @Test
    public void testDeclareFeature() throws Exception {
        Connection connection = MqTools.getConnection();
        Channel channel = connection.createChannel();
        /**
         * 0.设置一个exchange之后设置了durable,autodelete,internal，对应的在管理端会显示
         * D,AD,I ,内部交换器在执行发送的时候,系统日志会发生错误，但是不抛出异常 {@link ExchangeTest#testInternalExchage()}
         */
        channel.exchangeDeclare("fist_exchange33", "direct", true, true, true, null);

        /**
         * 1.fist_exchange3 ,没有与任何的交换器绑定或队列,虽然自动删除等于true，在连接断开的时候也不会自动删除
         *
         * 因为没有使用过,最少的使用过一次
         */
        channel.exchangeDeclare("fist_exchange3", "direct", true, true, false, null);

        /**
         * autoDelete: 设置是否自动删除。 autoDelete设置为true则表示自动删除。自动删除的前提是至少有一个队列或者交换器与这个交换器绑定,
         * 之后所有与这个交换器绑定的队列或者交换器都与此解绑。
         *
         * 注意不能错误地把这个参数理解为 : "当与此交换器 连接的客户端都断开时 ， RabbitMQ会自动删除本交换器 "。
         *
         * 交换器可以设置 interl：
         * internal: 设置是否是内置的。如果设置为 true，则表示是内置的交换器，客户端程序无法直接发送消息到这个交换器中，只能通过交换器路由到交换器这种方式。
         */

        /**
         * 如何模拟自动自动删除?
         *
         * 手动解绑的时候会自动删除,在web管理端进行操作的时候会
         */
        channel.exchangeDeclare("fist_exchange4", "direct", true, true, false, null);
        String queueName = "fist_exchange4";
        channel.queueDeclare(queueName, true, false, true, null);
        channel.queueBind(queueName, "fist_exchange4", "fist_routingKey");
        Thread.sleep(10000L);
    }

    @Test
    public void testInternalExchage() throws Exception {
        Connection connection = MqTools.getConnection();
        Channel channel = connection.createChannel();
        /**
         * 0.设置一个exchange之后设置了durable,autodelete,internal，对应的在管理端会显示
         * D,AD,I ,内部交换器在执行发送的时候,不会发生异常但是服务器的日志会显示错误
         * operation basic.publish caused a channel exception access_refused:
         * cannot publish to internal exchange 'fist_exchange33' in vhost '/'
         */
        channel.exchangeDeclare("fist_exchange33", "direct", true, true, true, null);
        channel.queueDeclare("fist_exchange33_test", true, false, false, null);
        channel.queueBind("fist_exchange33_test", "fist_exchange33", "rk");
        channel.basicPublish("fist_exchange33", "rk", MessageProperties.TEXT_PLAIN, "ddd".getBytes());

        while (channel.isOpen()) {
            System.out.println("channel = " + channel.isOpen());
        }
        Thread.sleep(10000L);
    }
    // ---------------------------------------------------------------------------------------------------------------------------------------

    @Test
    public void testDeleteExchange() throws Exception {
        Connection connection = MqTools.getConnection();
        Channel channel = connection.createChannel();
        /**
         * 1.fist_exchange3 ,没有与任何的交换器绑定,虽然自动删除等于true，在连接断开的时候也不会自动删除
         * 重点)要明确的知道自动删除的意识,跟持久化是没有任何关系的
         */
        channel.exchangeDeclare("delete1", "direct", true, true, false, null);
        String queueName = "delete1_queue";
        channel.queueDeclare(queueName, true, false, true, null);
        channel.queueBind(queueName, "delete1", "fist_routingKey");

        channel.exchangeDeclare("delete2", "direct", true, true, false, null);
        channel.exchangeDeclare("delete3", "direct", true, true, false, null);
        channel.exchangeDeclare("delete4", "direct", true, true, false, null);

        channel.exchangeDeclare("delete5", "direct", true, true, false, null);
        queueName = "delete1_queue5";
        channel.queueDeclare(queueName, true, false, true, null);
        channel.queueBind(queueName, "delete5", "fist_routingKey");

        AMQP.Exchange.DeleteOk d = null;
        // 卡断点看查看绑定消息--
        System.out.println("d = " + d);
        /**
         * 直接删除是没问题的,虽然delete5有对应的绑定关系
         */
        d = channel.exchangeDelete("delete5");

        try {
            /**
             * 删除交换器:ifUnused=true，如果交换器在使用中就发生异常.
             */
            d = channel.exchangeDelete("delete1", true);
            System.out.println("d = " + d);

        } catch (Exception e) {
            e.printStackTrace();
            //并且关闭这个channel
        }

        try {
            /**
             * 上面删除发生异常了,导致这个channel关闭了,在执行删除就发生异常.才导致的没有删除
             */
            d = channel.exchangeDelete("delete1", false);
            System.out.println("d = " + d);
        } catch (Exception e) {
            e.printStackTrace();
            //并且关闭这个channel
        }
        /**
         * 修改成为false，无论使用不使用都删除.其实就是执行直接删
         */
        channel = connection.createChannel();
        d = channel.exchangeDelete("delete1", false);
        System.out.println("d = " + d);

        /**
         * 不存在这个交换器，你删除是不会抛出异常的-----删除不存在的交换器
         */
        d = null;
        d = channel.exchangeDelete("delete23333");
        System.out.println("d = " + d);
    }
}
