package com.middleserver.mq.book.chapter3.fedaration;

import com.middleserver.mq.book.MqTools;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import org.junit.Test;

/**
 * Created by jiamingku on 2019/8/23.
 * 发送消息的所有属性在消费端全部能接接受到
 *
 */
@SuppressWarnings("all")
public class FederationPublishTest {

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
     * 基本的发送,非常基本的发送
     *
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {;

        Channel channel = MqTools.getChannel("10.10.132.162");
        String exchangeName = "test";
        byte[] messageBytes = "Hello, world".getBytes();
        for (int i = 1; i < 10000; i++) {
            channel.basicPublish(exchangeName, "rk", null, messageBytes);
//            Thread.sleep(100L);
        }
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
}
