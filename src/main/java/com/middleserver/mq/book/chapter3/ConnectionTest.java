package com.middleserver.mq.book.chapter3;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ShutdownListener;
import com.rabbitmq.client.ShutdownSignalException;
import org.junit.Test;

/**
 * Created by jiamingku on 2019/8/22.
 */
@SuppressWarnings("all")
public class ConnectionTest {

    /**
     * 一般在不会判断连接是否打开,根据结果去判断是否可以执行channel后续的执行操作
     * <p>
     * if(chaanel.isOpen) {
     * channel.exchange..
     * }
     * <p>
     * 直接认为就是open如果没打开就发生异常抛出
     *
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("root123");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setHost("10.10.132.164");
        connectionFactory.setPort(5672);
        Connection connection = connectionFactory.newConnection();
        if (connection.isOpen()) {
            System.out.println(" 新打开了一个mq连接 ");
        }
    }


    @Test
    public void test2() throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("root123");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setHost("my.rabbitmq.com");
        connectionFactory.setPort(5672);
        Connection connection = connectionFactory.newConnection();
        if (connection.isOpen()) {
            System.out.println(" 新打开了一个mq连接 ");
        }

        connection.addShutdownListener(new ShutdownListener() {
            @Override
            public void shutdownCompleted(ShutdownSignalException cause) {
                System.out.println(" connecton cloase: "  + cause.getMessage());
                System.out.println(" = " + connection.getCloseReason().toString());
            }
        });
        connection.close();

        Thread.sleep(10000L);
    }
}
