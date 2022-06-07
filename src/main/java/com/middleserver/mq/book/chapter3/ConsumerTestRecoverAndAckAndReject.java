package com.middleserver.mq.book.chapter3;

import com.middleserver.mq.book.MqTools;
import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Created by jiamingku on 2019/8/23.
 * 测试的时候先执行发送操作-可以在里面创建队列
 */
@SuppressWarnings("all")
public class ConsumerTestRecoverAndAckAndReject {
    String exchangeName = "test_publish_2";
    String queueName = "test_queue_20191022";
    String routeKey = "test_pbulish_key";

    @Test
    public void publish() throws Exception {
        Channel channel = MqTools.getChannel();
        channel.exchangeDeclare(exchangeName, "direct", true, false, null);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, routeKey);
        byte[] messageBytes = "Hello, world".getBytes();
        for (int i = 0; i < 1000; i++) {
            Map<String, Object> headers = new HashMap<>();
            headers.put("location", "here");
            headers.put("time", "today");
            channel.basicPublish(exchangeName, routeKey, new AMQP.BasicProperties.Builder().headers(headers).build(), (i + " --").getBytes());
        }

        Thread.sleep(1000);
    }

    // ----------------------------------------------ack------------------------------------------
    // ----------------------------------------------ack------------------------------------------
    @Test
    public void testAck() throws Exception {
        Channel channel = MqTools.getChannel();
        boolean autoAck = false;
        channel.basicConsume(queueName, autoAck, "fistConsumerTag",
                new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        String routingKey = envelope.getRoutingKey();
                        long deliveryTag = envelope.getDeliveryTag();
                        System.out.println("deliveryTag = " + deliveryTag + "     " + new String(body));
                        /**
                         * false 代表是不是批量操作
                         * true  代表的是批量的ack
                         */
                        channel.basicAck(deliveryTag, false);
                    }
                }
        );
        Thread.sleep(1000L);
    }
    // ----------------------------------------------no-ack------------------------------------------
    // ----------------------------------------------no-ack------------------------------------------
    @Test
    public void testNoAck() throws Exception {
        Channel channel = MqTools.getChannel();
        boolean autoAck = false;
        channel.basicConsume(queueName, autoAck, "fistConsumerTag",
                new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        String routingKey = envelope.getRoutingKey();
                        long deliveryTag = envelope.getDeliveryTag();
                        System.out.println("deliveryTag = " + deliveryTag + "     " + new String(body));
                        /**
                         * 第二个参数(是否批量)
                         * false 代表是不是批量操作
                         * true  代表的是批量的ack
                         *
                         * 第三个参数(是否进入队列)
                         * 如果是true ：那就是循环的消费，消费一条拒绝一条，然后不断的轮训队列消息
                         * 如果是false: 那就是将消息抛弃
                         */
                        channel.basicNack(deliveryTag, false, false);
                    }
                }
        );
        Thread.sleep(10000L);
    }

    @Test
    public void testNoAck11112() throws Exception {
        Channel channel = MqTools.getChannel();
        boolean autoAck = false;
        channel.basicConsume(queueName, autoAck, "fistConsumerTag",
                new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        String routingKey = envelope.getRoutingKey();
                        long deliveryTag = envelope.getDeliveryTag();
                        System.out.println("deliveryTag = " + deliveryTag + "     " + new String(body));
                        /**
                         * 第二个参数(是否批量)
                         * false 代表是不是批量操作
                         * true  代表的是批量的ack
                         *
                         * 第三个参数(是否进入队列)
                         * 如果是true ：那就是循环的消费，消费一条拒绝一条，然后不断的轮训队列消息
                         * 如果是false: 那就是将消息抛弃
                         *
                         */
                        channel.basicNack(deliveryTag, false, true);
                    }
                }
        );
        Thread.sleep(10000L);
    }
    // ----------------------------------------------Reject------------------------------------------
    // ----------------------------------------------Reject------------------------------------------
    @Test
    public void testBasicReject() throws Exception {
        Channel channel = MqTools.getChannel();
        boolean autoAck = false;
        channel.basicConsume(queueName, autoAck, "fistConsumerTag",
                new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        String routingKey = envelope.getRoutingKey();
                        long deliveryTag = envelope.getDeliveryTag();
                        System.out.println("deliveryTag = " + deliveryTag + "     " + new String(body));
                        /**
                         * 只能一条条的拒绝---
                         * 第二个参数
                         * false 代表不进入队列--直接丢弃了
                         * true  代表进入队列--还可以重复消费
                         *
                         * 跟nack是一样的效果
                         */
                        channel.basicReject(deliveryTag, false);
                    }
                }
        );
        Thread.sleep(1000L);
    }

    /**
     * 发送端口:设置的属性在接受端完全可以获取到,
     * <p>
     * 1.如果basicConsume的autoAck设置false,并且没有手动ack,他会讲消息放在unacked中，如果中时候断开连接，消息会自动归还队列
     * <p>
     * 2.注意并不是消息消费一次(或者调用DefaultConsumer一次，消息才跑到unacked中)，一次会发送很多，一批过去,卡断点就行了
     * <p>
     * 3.从实际的效果来看，一起使用就发生错误:channel.basicAck(deliveryTag, true);, channel.basicRecover(true); 会发生异常-导致这个channel关闭了。
     * 但是这个channel还是可以打印投递过来的消息，只是你确认的时候服务器不接受罢了
     */
    @Test
    public void testBasicAckAndbasicRecover() throws Exception, TimeoutException {
        Connection c = MqTools.getConnection();
        Channel channel = c.createChannel();
        boolean autoAck = false;
        channel.basicConsume(queueName, autoAck, "fist-whatareyoudong",
                new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        long deliveryTag = envelope.getDeliveryTag();
                        System.out.println("deliveryTag = " + deliveryTag + "     " + new String(body));
                        System.out.println(" ===============================");
                        channel.basicAck(deliveryTag, true);
                        channel.basicRecover(true);
                    }
                });
        System.out.println(" ============================ ");
        Thread.sleep(1000000L);
    }


    //-----------------------------------------------------------------下面的方法感觉没啥用----------------------------------------------------------
    //-----------------------------------------------------------------下面的方法感觉没啥用----------------------------------------------------------
    //-----------------------------------------------------------------下面的方法感觉没啥用----------------------------------------------------------
    //-----------------------------------------------------------------下面的方法感觉没啥用----------------------------------------------------------
    //-----------------------------------------------------------------下面的方法感觉没啥用----------------------------------------------------------
    //-----------------------------------------------------------------下面的方法感觉没啥用----------------------------------------------------------
    //-----------------------------------------------------------------下面的方法感觉没啥用----------------------------------------------------------
    //-----------------------------------------------------------------下面的方法感觉没啥用----------------------------------------------------------

    // ----------------------------------------------Recovet------------------------------------------
    // ----------------------------------------------Recovet------------------------------------------
    @Test
    public void testBasicRecover() throws Exception {
        Channel channel = MqTools.getChannel();
        boolean autoAck = false;

        channel.basicConsume(queueName, autoAck, "fistConsumerTag",
                new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        String routingKey = envelope.getRoutingKey();
                        long deliveryTag = envelope.getDeliveryTag();
                        System.out.println("consumerTag = " + consumerTag + "     " + new String(body));
                        /**
                         * false 会发生异常。可以不处理，跟im那个一样,channel.basicRecover(false)这个方法有时候出现问题，好像不支持了。
                         */
                        channel.basicRecover(true);
                        System.out.println(" **********************************");

                    }
                }
        );
        Thread.sleep(100);
        channel.basicConsume(queueName, autoAck, "fistConsumerTag1",
                new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        String routingKey = envelope.getRoutingKey();
                        long deliveryTag = envelope.getDeliveryTag();
                        System.out.println("consumerTag = " + consumerTag + "     " + new String(body));
                        System.out.println(" ===============================");
                        /**
                         * false 就相当于发送给当前
                         */
                        // channel.basicRecover(true);

                    }
                }
        );
        Thread.sleep(100000L);
    }

    /**
     * 注意书上说这个方法，重新发送没有确认的消息。如果requeue=true，是针对于 当前channel
     */
    @Test
    public void testBasicAckAndbasicRecoverImportant() throws Exception, TimeoutException {
        Connection c = MqTools.getConnection();
        Channel channel = c.createChannel();
        boolean autoAck = false;
        channel.basicConsume(queueName, autoAck, "fist-whatareyoudong",
                new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        long deliveryTag = envelope.getDeliveryTag();
                        System.out.println("deliveryTag = " + deliveryTag + "     " + new String(body));
                        System.out.println(" ===============================");
                        channel.basicRecover(true);
                    }
                });
        System.out.println(" ============================ ");
        Thread.sleep(1000000L);
    }

    /**
     * 注意书上说这个方法，重新发送没有确认的消息。如果requeue=true，是针对于当前channel
     */
    @Test
    public void testBasicAckAndbasicRecoverImportant1() throws Exception, TimeoutException {
        Connection c = MqTools.getConnection();
        Channel channel = c.createChannel();
        boolean autoAck = false;
        channel.basicConsume(queueName, autoAck, "fist-whatareyoudong",
                new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||deliveryTag = " + envelope.getDeliveryTag() + "     " + new String(body));
                    }
                });
        System.out.println(" ============================ ");

        Thread.sleep(1000000L);
    }


    @Test
    public void testBasicAckAndbasicRecoverImportantNo111111111111111111111111() throws Exception, TimeoutException {
        Connection c = MqTools.getConnection();
        Channel channel = c.createChannel();
        boolean autoAck = false;

        channel.basicConsume(queueName, autoAck, "fist-whatareyoudong",
                new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        long deliveryTag = envelope.getDeliveryTag();
                        System.out.println("deliveryTag = " + deliveryTag + "     " + new String(body));
                        System.out.println(" ===============================");

                    }
                });
        System.out.println(" ============================ ");

        Thread.sleep(1000000L);
    }

}
