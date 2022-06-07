package com.middleserver.mq.book;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Created by jiamingku on 2019/7/13.
 */
@SuppressWarnings("all")
public class MqTools {

    private static String userName = "yoho";
    private static String passwd = "yoho";

    public static Connection getConnection() throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUsername(userName);
        connectionFactory.setPassword(passwd);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setHost("my.rabbitmq.com");
        connectionFactory.setPort(5672);
        Connection connection1 = connectionFactory.newConnection();

        return connection1;
    }

    public static Connection getConnection(String ip) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUsername(userName);
        connectionFactory.setPassword(passwd);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setHost(ip);
        connectionFactory.setPort(5672);
        Connection connection1 = connectionFactory.newConnection();

        return connection1;
    }

    public static Channel getChannel() throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUsername(userName);
        connectionFactory.setPassword(passwd);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setHost("my.rabbitmq.com");
        connectionFactory.setPort(5672);
        Connection connection1 = connectionFactory.newConnection();
        Channel channel1 = connection1.createChannel();
        return channel1;
    }

    public static Channel getChannel(String ip) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUsername(userName);
        connectionFactory.setPassword(passwd);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setHost(ip);
        connectionFactory.setPort(5672);
        Connection connection1 = connectionFactory.newConnection();
        Channel channel1 = connection1.createChannel();
        return channel1;
    }

    public static Connection get() throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUri("amqp://root:root123@10.10.132.162:5672");

        Channel channel = connectionFactory.newConnection().createChannel();
        System.out.println("channel = " + channel.isOpen());
        return null;
    }


    public static void main(String[] args) throws Exception {
//        System.out.println("7 % 5 = " + 7 % 5);
//        System.out.println("5%7 = " + 5 % 7);
        get();
    }
}
