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
public class ConsumerTest {

    /**
     * 推模式
     * <p>
     * 所以断定肯定是junit的问题，百度下说junit测试时@test方法执行完就会终止所有用户线程。测试下果然如此
     * 方法结束会关闭用户线程
     * <p>
     * 1.发送时候指定的属性，在接收的时候完全可以获取到，想传递啥就传递啥。
     * 2.可以通过相同的channel，创建消费函数，在队列的web管理端可以看到相同的channel编号
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

        channel.basicConsume(queueName, false, "fist-whatareyoudong",
                new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        System.out.println(" **********************消费者发送的所有东西都能获取到" +
                                "通过这几个参数全部能获取到相应的参数值");

                    }
                });

        channel.basicConsume(queueName, false, "second-whatareyoudong",
                new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        System.out.println(" ============================ " + envelope.getDeliveryTag());

                    }
                });

        channel1.basicConsume(queueName, false, "second-whatareyoudong",
                new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        System.out.println(" xxxxxxxxxxxxxxxxxxxxxxxxx ");
                    }
                });


        channel2222.basicConsume(queueName, false, "fist-whatareyoudong",
                new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        System.out.println(" **********************消费者发送的所有东西都能获取到" +
                                "通过这几个参数全部能获取到相应的参数值");

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
                        System.out.println(" **********************消费者发送的所有东西都能获取到" +
                                "通过这几个参数全部能获取到相应的参数值");
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
