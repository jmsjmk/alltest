package com.middleserver.mq.rabbitmq.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@SuppressWarnings("all")
public class ProducerNoRouteKey {
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

    /**
     * @param args
     * @throws IOException
     * @throws TimeoutException
     */
    public static void main(String[] args) throws Exception, TimeoutException {

        Connection connection = getConnection();
        Channel channel = connection.createChannel();
        /**
         * 重点:发送消息如果交换器，没有声明queue的话，消息将丢失,(就是队列不存在的时候消息会丢失)
         *
         * 信道去绑定去声明交换器
         * 交换器
         * 1.有名字
         * 2.交换器的类型
         * 3.持久化的，就是容器重启了,或者你关闭了都会存在,这个是容器控制他的存活的
         * 4.发送完就拉倒了，消费者消费完了，队列也不存在的时候就删除了
         *  如果一直没有消费者过来，这个可能会一直存在到 服务器重新启动
         *  在创建连接的时候这里面有个连接池。得不到返回信息，所以程序就停止了。
         */
        channel.exchangeDeclare("test1111", "direct", true, true, null);
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        String message = "Hello RabbitMQ1113333";
        for (int i = 0; i < 100; i++) {
            message = "-------------3";
            message = message + "_" + i;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
            Thread.sleep(100);
            System.out.println("h");
        }
        //发送消息到队列中
        //  channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN , message.getBytes());
        // 写错了就报错
//		for (int i = 0; i < 10000000; i ++) {
//
//			channel.basicPublish("hello-exchange", "hola", null, message.getBytes("UTF-8"));
//		}
        /**
         * 如果发送的消息，没有存在交换器的话在关闭channel的时候会发生异常
         * 这时候终端就会自自己关闭这个channel ，如果你在关闭的话就抛出异常
         */

        System.out.println("发送完成~~~ Producer Send +'" + message + "'");
//		while(true) {
        try {
            Thread.sleep(2 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
//				break;
        }
//		}
        //关闭通道和连接
        channel.close();
        connection.close();
    }
}