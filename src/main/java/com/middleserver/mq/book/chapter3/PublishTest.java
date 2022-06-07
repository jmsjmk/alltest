package com.middleserver.mq.book.chapter3;

import com.middleserver.mq.book.MqTools;
import com.rabbitmq.client.*;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiamingku on 2019/8/23.
 * 发送消息的所有属性在消费端全部能接接受到,相对于此api发送的属性都能获取到
 */
@SuppressWarnings("all")
public class PublishTest {

    /** 发送mq消息主要属性   channel.basicPublish(exchangeName, routeKey, MessageProperties.PERSISTENT_TEXT_PLAIN, messageBytes);*/
    /**
     * contentType
     * contentEncoding
     * headers-(Map<String, Object>)
     * deliveryMode
     * priority
     * correlationld
     * replyTo
     * expiration
     * messageld
     * timestamp
     * type
     * userld  -连接的用户跟你指定的用户不是一个的时候就发生异常
     * appld
     * clusterld
     */
    //-------------------------------------------------------------------------------
    /**
     * 声明exchagne时候可以指定一些属性
     *
     * altername-exchagne 参数
     *
     */

    //-------------------------------------------------------------------------------
    /**
     * 声明exchagne时候可以指定一些属性
     * altername-exchagne 参数
     */
    /**
     * 基本的发送,非常基本的发送
     *
     * @throws Exception
     */
    @Test
    public void testBasicPublish() throws Exception {
        Channel channel = MqTools.getChannel();
        String exchangeName = "test_publish_1";
        String queueName = "test_queue_1";
        String routeKey = "test_pbulish_key";
        // Exchange.DeclareOk exchangeDeclare(String exchange,String type,boolean durable, boolean autoDelete, boolean internal, Map<String, Object> arguments) throws IOException;
        channel.exchangeDeclare(exchangeName, "direct", true, false, null);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, routeKey);
        byte[] messageBytes = "Hello, world".getBytes();
        channel.basicPublish(exchangeName, routeKey, null, messageBytes);
    }

    /**
     * 发送到内部交换器立马就报错
     *
     * @throws Exception
     */
    @Test
    public void testBasicPublishToInnerExchange() throws Exception {
        Channel channel = MqTools.getChannel();
        String exchangeName = "test_publish_internal";
        String queueName = "test_queue_1";
        String routeKey = "test_pbulish_key";
        // Exchange.DeclareOk exchangeDeclare(String exchange,String type,boolean durable, boolean autoDelete, boolean internal, Map<String, Object> arguments) throws IOException;
        channel.exchangeDeclare(exchangeName, "direct", true, false, true, null);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, routeKey);
        byte[] messageBytes = "Hello, world".getBytes();
        channel.basicPublish(exchangeName, routeKey, null, messageBytes);

        while (true) {
            System.out.println(" = ");
        }
    }

    /**
     * 指定属性,消息属性
     *
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        Channel channel = MqTools.getChannel();
        String exchangeName = "test_publish_2";
        String queueName = "test_queue_2";
        String routeKey = "test_pbulish_key";
        // Exchange.DeclareOk exchangeDeclare(String exchange,String type,boolean durable, boolean autoDelete, boolean internal, Map<String, Object> arguments) throws IOException;
        channel.exchangeDeclare(exchangeName, "direct", true, false, null);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, routeKey);
        byte[] messageBytes = "Hello, world".getBytes();
        channel.basicPublish(exchangeName, routeKey, MessageProperties.PERSISTENT_TEXT_PLAIN, messageBytes);
    }

    /**
     * 关于header测设置,设置什么那边就能得到什么
     *
     * @throws Exception
     */
    @Test
    public void test3() throws Exception {
        Channel channel = MqTools.getChannel();
        String exchangeName = "test_publish_2";
        String queueName = "test_queue_2";
        String routeKey = "test_pbulish_key";
        // Exchange.DeclareOk exchangeDeclare(String exchange,String type,boolean durable, boolean autoDelete, boolean internal, Map<String, Object> arguments) throws IOException;
        channel.exchangeDeclare(exchangeName, "direct", true, false, null);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, routeKey);
        byte[] messageBytes = "Hello, world".getBytes();

        Map<String, Object> headers = new HashMap<>();
        headers.put("location", "here");
        headers.put("time", "today");
        channel.basicPublish(exchangeName, routeKey, new AMQP.BasicProperties.Builder().headers(headers).build(), messageBytes);
    }

    /**
     * 关于header测设置
     *
     * @throws Exception
     */
    @Test
    public void test4() throws Exception {
        Channel channel = MqTools.getChannel();
        String exchangeName = "test_publish_2";
        String queueName = "test_queue_2222222";
        String routeKey = "test_pbulish_key";
        // Exchange.DeclareOk exchangeDeclare(String exchange,String type,boolean durable, boolean autoDelete, boolean internal, Map<String, Object> arguments) throws IOException;
        channel.exchangeDeclare(exchangeName, "direct", true, false, null);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, routeKey);
        byte[] messageBytes = "Hello, world".getBytes();

        /**
         * 监听也不是马上就能 感知到 马上关闭了的
         */
        channel.addShutdownListener(new ShutdownListener() {
            @Override
            public void shutdownCompleted(ShutdownSignalException cause) {
                System.out.println(".......................");
            }
        });

        channel.basicPublish(exchangeName, routeKey, MessageProperties.PERSISTENT_TEXT_PLAIN, messageBytes);

        // 这个用户id 只有跟你发送的id一样的时候才会推动到mq--设置完了之后。下面的就不能发送了
        channel.basicPublish(exchangeName, routeKey, new AMQP.BasicProperties.Builder().deliveryMode(2).priority(1).userId("root").build()
                , messageBytes);

        while (true) {
            if (channel.isOpen()) {
                System.out.println("is open");
            } else {
                System.out.println("no open ");
                break;
            }
        }

        channel.basicPublish(exchangeName, routeKey, new AMQP.BasicProperties.Builder().deliveryMode(2).priority(1).userId("root").build()
                , messageBytes);

        // 只能换通道
        channel = MqTools.getChannel();
        channel.basicPublish(exchangeName, routeKey, new AMQP.BasicProperties.Builder().deliveryMode(2).priority(1).userId("root").build()
                , messageBytes);

        channel = MqTools.getChannel();
        Map<String, Object> headers = new HashMap<>();
        headers.put("location", "here");
        headers.put("time", "today");
        channel.basicPublish(exchangeName, routeKey, new AMQP.BasicProperties.Builder().headers(headers).userId("root").build(), messageBytes);
    }

    /**
     * 当一个channel.publish一个消息的时候  如果出现了异常，这个channel就会关闭
     * <p>
     * 但是对于客户端来说不能立马感知，所以就容易出现问题。这样消息会丢失。 就是发送出去了就丢失了。
     *
     * @throws Exception
     */
    @Test
    public void test5() throws Exception {
        Channel channel = MqTools.getChannel();
        String exchangeName = "test_publish_2";
        String queueName = "test_queue_1";
        String routeKey = "test_pbulish_key";
        // Exchange.DeclareOk exchangeDeclare(String exchange,String type,boolean durable, boolean autoDelete, boolean internal, Map<String, Object> arguments) throws IOException;

        channel.exchangeDeclare(exchangeName, "direct", true, false, null);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, routeKey);
        byte[] messageBytes = "Hello, world".getBytes();

        /**
         * 监听也不是马上就能 感知到 马上关闭了的---有一定的研究
         */
        channel.addShutdownListener(new ShutdownListener() {
            @Override
            public void shutdownCompleted(ShutdownSignalException cause) {
                System.out.println(".......................");
            }
        });
        for (int i = 0; i < 10000; i++) {
//            channel = MqTools.getChannel();
            Map<String, Object> headers = new HashMap<>();
            headers.put("location", "here");
            headers.put("time", "today");
//             channel.basicPublish(exchangeName, routeKey, new AMQP.BasicProperties.Builder().headers(headers).build(), messageBytes);
            // connetion 的连接用户必须相同的时候才能使用
            channel.basicPublish(exchangeName, routeKey, new AMQP.BasicProperties.Builder().headers(headers).userId("root1").build(), messageBytes);
            if (channel.isOpen()) {
                System.out.println(" = =====");
            }
            channel.basicPublish(exchangeName, routeKey, new AMQP.BasicProperties.Builder().headers(headers).build(), messageBytes);
            System.out.println(" = ");
        }

//        channel.close();
        while (channel.isOpen()) {
            System.out.println("messageBytes = ");
        }
        Thread.sleep(100000);
    }

    /**
     * 没有指定交换器用系统默认的交换起，rk=queueName,系统会默认给你绑定系统默认的交换器上
     *
     * @throws Exception
     */
    @Test
    public void test6() throws Exception {
        Channel channel = MqTools.getChannel();
        for (int i = 0; i < 10; i++) {
            String queueName = "test_queue_blank";
            String routeKey = "test_pbulish_key";
            // Exchange.DeclareOk exchangeDeclare(String exchange,String type,boolean durable, boolean autoDelete, boolean internal, Map<String, Object> arguments) throws
            // IOException;

            channel.queueDeclare(queueName, true, false, false, null);
            byte[] messageBytes = "Hello, world".getBytes();

            Map<String, Object> headers = new HashMap<>();
            headers.put("location", "here");
            headers.put("time", "today");
            channel.basicPublish("", queueName, null, messageBytes);
        }
    }

    /**
     * 交换器不存在的时候就抛出系统异常
     */
    @Test
    public void testNoExistExchange() throws Exception {
        Channel channel = MqTools.getChannel();
        String exchangeName = "test_publish_2";
        String queueName = "test_queue_2";
        String routeKey = "test_pbulish_key";
        // Exchange.DeclareOk exchangeDeclare(String exchange,String type,boolean durable, boolean autoDelete, boolean internal, Map<String, Object> arguments) throws IOException;
        // 那怕名字想通也不同
        channel.exchangeDeclare(exchangeName, "direct", true, false, null);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, routeKey);
        byte[] messageBytes = "Hello, world".getBytes();
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
            channel.basicPublish("genbenmeiyouzhegejiaohuanqi", routeKey, true, false, MessageProperties.PERSISTENT_TEXT_PLAIN, ("hello " + i).getBytes());
            channel.basicPublish("test_publish_2", routeKey, true, false, MessageProperties.PERSISTENT_TEXT_PLAIN, ("hello " + i).getBytes());
        }
    }

    /**
     * 交换器不存在的时候就抛出系统异常
     * 这个会丢失消息发送10万条。有时候会丢，有时候不会丢
     */
    @Test
    public void ppppppppppppppppppppppppp() throws Exception {
        Channel channel = MqTools.getChannel();
        String exchangeName = "test_publish_2";
        String queueName = "test_queue_2";
        String routeKey = "test_pbulish_key";
        // Exchange.DeclareOk exchangeDeclare(String exchange,String type,boolean durable, boolean autoDelete, boolean internal, Map<String, Object> arguments) throws IOException;
        // 那怕名字想通也不同
        channel.exchangeDeclare(exchangeName, "direct", true, false, null);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, routeKey);
        byte[] messageBytes = "Hello, world".getBytes();
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
            channel.basicPublish("test_publish_2", routeKey, true, false, MessageProperties.PERSISTENT_TEXT_PLAIN, ("hello " + i).getBytes());
        }
    }


}
