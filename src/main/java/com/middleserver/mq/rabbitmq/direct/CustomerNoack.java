package com.middleserver.mq.rabbitmq.direct;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@SuppressWarnings("all")
public class CustomerNoack {
    public final static String QUEUE_NAME = "rabbitMQ.test";

    public static Connection getConnection() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        //设置RabbitMQ相关信息
        factory.setHost("localhost");
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setPort(5672);
        //创建一个新的连接
        Connection connection = factory.newConnection();
        return connection;
    }


    public static void main(String[] args) throws Exception, TimeoutException {
        //创建一个新的连接
        Connection connection = getConnection();
        //创建一个通道
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(QUEUE_NAME, "topic", true, true, null);

        /**
         * 有生产者有消费者两端的属性一定要严格控制必须相同的
         */
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        System.out.println("Customer Waiting Received messages");

        // 告诉服务器我们需要那个频道的消息，如果频道中有消息，就会执行回调函数handleDelivery
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Customer Received '" + message + "'");
            }
        };
        //自动回复队列应答 -- RabbitMQ中的消息确认机制
        /**
         * 当你消耗队列，但是没有给ack应答,这样导致的后悔就是unacked 那列显示的数据是
         * true/false
         */
        String s = channel.basicConsume(QUEUE_NAME, false, consumer);
        System.out.println("s = " + s);
    }
}
