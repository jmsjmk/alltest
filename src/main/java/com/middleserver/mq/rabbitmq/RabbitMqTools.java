package com.middleserver.mq.rabbitmq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Created by jiamingku on 2019/7/7.
 */
@SuppressWarnings("all")
public class RabbitMqTools {
    private static final String EXCHANGE_NAME = "exchange_demo";
    private static final String ROUTING_KEY = "routingkey_demo";

    private static final String QUEUE_NAME = "queue_demo";

    private static final String IP_ADDRESS = "10.10.132.164";

    private static final int PORT = 5672;

    public static Connection getConnection() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        //设置RabbitMQ相关信息
        factory.setHost(IP_ADDRESS);
        factory.setUsername("root");
        factory.setPassword("root123");
        factory.setPort(5672);
        //创建一个新的连接
        Connection connection = factory.newConnection();
        return connection;
    }

    public static Connection getRootConnection() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        //设置RabbitMQ相关信息
        factory.setHost(IP_ADDRESS);
        factory.setUsername("root");
        factory.setPassword("root123");
        factory.setPort(PORT);
        //创建一个新的连接
        Connection connection = factory.newConnection();
        System.out.println("connection = " + connection.isOpen());
        return connection;
    }
}
