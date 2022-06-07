package com.middleserver.mq.book.chapter1;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by jiamingku on 2019/7/9.
 */
public class RabbitConsumer {

    private static final String QUEUE_NAME = "queue_demo";
    private static final String IP_ADDRESS = "10.10.132.164";
    private static final int PORT = 5672;//RabbitMQ服务端默认端口为5672

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        // 这就是集群的关键点,可以配置多个ip地址
        Address[] addresses = new Address[]{new Address(IP_ADDRESS, PORT)};
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("root");
        factory.setPassword("root123");
        // 这里的连接方式与生产者的demo略有不同，注意辨别区别
        Connection connection = factory.newConnection(addresses);
        //创建信道
        final Channel channel = connection.createChannel();
        // 设置客户端最多接收未被ack的消息的个数
        // 如果noack如果超过这个数量的话，channel是不会关闭的,服务器这时候不会想这个channel发送消息了
        channel.basicQos(1);
        Consumer consumer = new DefaultConsumer(channel) {
            /**
             * @param consumerTag  在定义消费者的时候这个标签是可以指定的,如果不指定服务器给你生成一个,如果指定了一个生成
             *                     (channel.basicConsume(QUEUE_NAME,false, "fist_mytag",consumer);) 对应的在管理端就是显示这个tag标签
             * @param envelope
             * @param properties
             * @param body
             * @throws IOException
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                if (channel.isOpen()) {
                    System.out.println("channel.isOpen() = " + channel.isOpen());
                }
                System.out.println("consumerTag = " + consumerTag);
                System.out.println("recv message: " + new String(body));
                String routingKey = envelope.getRoutingKey();
                System.out.println("routingKey = " + routingKey);
                String contentType = properties.getContentType();
                System.out.println("contentType = " + contentType);
                long deliveryTag = envelope.getDeliveryTag();
                /**
                 * 这个是每个channel级别的一个投递标记---这个已经验证过了----
                 */
                System.out.println("deliveryTag = " + deliveryTag);
                System.out.println("properties = " + properties);
                System.out.println(" =###################### ");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        channel.basicConsume(QUEUE_NAME, false, "fist_mytag", consumer);

        // 等待回调函数执行完毕之后，关闭资源
        TimeUnit.SECONDS.sleep(5);
        boolean b = true;
        while (b) {
            if (channel.isOpen()) {
                System.out.println("channel.isOpen() = " + channel.isOpen());
            }
            TimeUnit.SECONDS.sleep(1);
        }
        channel.close();
        connection.close();
    }
}
