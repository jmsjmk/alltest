package com.middleserver.mq.book.chapter3.cluster;

import com.middleserver.mq.book.MqTools;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiamingku on 2019/8/23.
 * 发送消息的所有属性在消费端全部能接接受到
 *
 */
@SuppressWarnings("all")
public class ClusterPublishTest {

    /** 发送mq小时时候的主要属性   channel.basicPublish(exchangeName, routeKey, MessageProperties.PERSISTENT_TEXT_PLAIN, messageBytes);*/
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
     * userld
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
     *
     * altername-exchagne 参数
     *
     */



    /**
     * 1.集群测试--交换器一个机器创建-回同步到所有集群节点, 修改那个host连接那个节点都能将消息发送出去
     *
     * 2.但是队列还是创建在对应的节点上
     *
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        Channel channel = MqTools.getChannel();
        String exchangeName = "web_add_queue163";
        String queueName = "add_queue_163";
        String routeKey = "add_queue_163";
        // Exchange.DeclareOk exchangeDeclare(String exchange,String type,boolean durable, boolean autoDelete, boolean internal, Map<String, Object> arguments) throws IOException;
        byte[] messageBytes = "Hello, world".getBytes();
        channel.basicPublish(exchangeName, routeKey, null, messageBytes);
    }

    /**
     * 指定属性
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
     * 关于header测设置
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
        String queueName = "test_queue_2";
        String routeKey = "test_pbulish_key";
        // Exchange.DeclareOk exchangeDeclare(String exchange,String type,boolean durable, boolean autoDelete, boolean internal, Map<String, Object> arguments) throws IOException;
        channel.exchangeDeclare(exchangeName, "direct", true, false, null);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, routeKey);
        byte[] messageBytes = "Hello, world".getBytes();

        channel.basicPublish(exchangeName, routeKey, MessageProperties.PERSISTENT_TEXT_PLAIN, messageBytes);

        // 这个用户id 只有跟你发送的id一样的时候才会推动到mq--设置完了之后。下面的就不能发送了
        channel.basicPublish(exchangeName, routeKey, new AMQP.BasicProperties.Builder().deliveryMode(2).priority(1).userId("root11").build()
                , messageBytes);

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
     *
     * 但是对于客户端来说不能立马感知，所以就容易出现问题。这样消息会丢失。 就是发送出去了就丢失了。
     *
     *
     * @throws Exception
     */
    @Test
    public void test5() throws Exception {
        for (int i = 0 ; i < 10; i ++) {
            Channel channel = MqTools.getChannel();
            String exchangeName = "test_publish_2";
            String queueName = "test_queue_2";
            String routeKey = "test_pbulish_key";
            // Exchange.DeclareOk exchangeDeclare(String exchange,String type,boolean durable, boolean autoDelete, boolean internal, Map<String, Object> arguments) throws IOException;

            channel.exchangeDeclare(exchangeName, "direct", true, false, null);
            channel.queueDeclare(queueName, true, false, false, null);
            channel.queueBind(queueName, exchangeName, routeKey);
            byte[] messageBytes = "Hello, world".getBytes();

            channel = MqTools.getChannel();
            Map<String, Object> headers = new HashMap<>();
            headers.put("location", "here");
            headers.put("time", "today");
             channel.basicPublish(exchangeName, routeKey, new AMQP.BasicProperties.Builder().headers(headers).build(), messageBytes);
            // connetion 的连接用户必须相同的时候才能使用
            channel.basicPublish(exchangeName, routeKey, new AMQP.BasicProperties.Builder().headers(headers).userId("root1").build(), messageBytes);
            if (channel.isOpen()) {
                System.out.println(" = =====" );
            }
            channel.basicPublish(exchangeName, routeKey, new AMQP.BasicProperties.Builder().headers(headers).build(), messageBytes);
        }
    }

    /**
     * 没有指定交换器用系统默认的交换起，rk=queueName
     * @throws Exception
     */
    @Test
    public void test6() throws Exception {
        Channel channel = MqTools.getChannel();
        for (int i = 0 ; i < 10; i ++) {
            String queueName = "test_queue_blank";
            String routeKey = "test_pbulish_key";
            // Exchange.DeclareOk exchangeDeclare(String exchange,String type,boolean durable, boolean autoDelete, boolean internal, Map<String, Object> arguments) throws IOException;

            channel.queueDeclare(queueName, true, false, false, null);
            byte[] messageBytes = "Hello, world".getBytes();

            Map<String, Object> headers = new HashMap<>();
            headers.put("location", "here");
            headers.put("time", "today");
            channel.basicPublish("", queueName, null, messageBytes);
        }
    }

    @Test
    public void test54() throws Exception {
        Channel channel = MqTools.getChannel();
        for (int i = 0 ; i < 100; i ++) {

            String exchangeName = "test_publish_2";
            String queueName = "test_queue_2";
            String routeKey = "test_pbulish_key";
            // Exchange.DeclareOk exchangeDeclare(String exchange,String type,boolean durable, boolean autoDelete, boolean internal, Map<String, Object> arguments) throws IOException;
            // 那怕名字想通也不同
            channel.exchangeDeclare(exchangeName, "direct", true, false, null);
            channel.queueDeclare(queueName, true, false, false, null);
            channel.queueBind(queueName, exchangeName, routeKey);
            byte[] messageBytes = "Hello, world".getBytes();


            channel.basicPublish(exchangeName, routeKey, true, false,  MessageProperties.PERSISTENT_TEXT_PLAIN, messageBytes);
        }
    }



}
