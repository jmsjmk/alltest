package com.middleserver.mq.book.chapter3;

import com.middleserver.mq.book.MqTools;
import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by jiamingku on 2019/8/23.
 */
@SuppressWarnings("all")
public class ConsumerTestDelivery {

    /**
     * 推模式
     * 消费者在进行消息消费的时候,一般是设置非自动ack,自己确定消息是否发送完成-也就是手动ack
     * <p>
     * 手动ack,要指定个投递标签+真价值=投递标签就是channel级别是共享的(一个channel两个消费者，true/false就是是否是批量的)
     */
    @Test
    public void test1() throws Exception {
        Connection connection = MqTools.getConnection();
        Connection connection2 = MqTools.getConnection();
        Channel channel = connection.createChannel();
        Channel channel1 = connection.createChannel();
        Channel channel2222 = connection2.createChannel();
        boolean autoAck = false;
        String exchangeName = "test_publish_2";
        String queueName = "test_queue_2";
        String routeKey = "routeKey";
        channel.exchangeDeclare(exchangeName, "direct", true, false, null);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, routeKey);

        /**
         * 同一个渠道的deliveryTag是增加的，也就是共享
         */
        channel.basicConsume(queueName, false, "fist-whatareyoudong",
                new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                        System.out.println("envelope = " + envelope.getDeliveryTag());
                    }
                });
        channel.basicConsume(queueName, false, "second-whatareyoudong",
                new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        System.out.println("envelope = " + envelope.getDeliveryTag());

                    }
                });
        /**
         * 不同渠道的delivery是分开的，
         */
        channel1.basicConsume(queueName, false, "second-whatareyoudong",
                new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        System.out.println("envelope = " + envelope.getDeliveryTag());
                    }
                });
        // -----------------------------------------同一个连接--同一个channel,还有不同的channel------
        Thread.sleep(10000000L);
    }

    /**
     * 发送端口:设置的属性在接收端完全可以获取到,
     * <p>
     * 1.如果自动ack设置时候是不自动设置true，他会讲消息放在unacked中，如果中时候断开连接，消息会自动归还队列
     * <p>
     * 2.
     */
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
         * 下面是各种事件监听的方法
         */
        channel1.basicQos(22222);
        channel1.basicConsume(queueName, autoAck, "fist-whatareyoudong",
                new DefaultConsumer(channel1) {
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        System.out.println("new String(body) = " + new String(body));
                        System.out.println(" ============================ ");
                        System.out.println(" **********************消费者发送的所有东西都能获取到" + "通过这几个参数全部能获取到相应的参数值");
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {
                        System.out.println("客户端准备关闭连接....");
                        System.out.println("consumerTag = " + consumerTag);
                    }

                    //
                    @Override
                    public void handleConsumeOk(String consumerTag) {
                        System.out.println("Override ==== = ");
                    }
                });
//        Thread.sleep(1000L);
//        channel1.close();
        while (true) {
            if (channel1.isOpen()) {
                System.out.println(" ================================================================ 超过了qos 但是还是打开的 ");
                Thread.sleep(10);
            }
        }
    }
}
