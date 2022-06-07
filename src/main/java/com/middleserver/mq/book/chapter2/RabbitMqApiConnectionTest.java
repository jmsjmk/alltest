package com.middleserver.mq.book.chapter2;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;

import java.net.URISyntaxException;
import java.security.KeyManagementException;

/**
 * Created by jiamingku on 2019/7/12.
 */
@SuppressWarnings("all")
public class RabbitMqApiConnectionTest {

    private String USERNAME = "guest";
    private int PORT = 5672;
    private String virtualHost = "/";
    private String IPADDRESS = "10.10.132.164";

    private String PASSWORD = "guest";

    public Connection getConnection() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);
        factory.setVirtualHost(virtualHost);
        factory.setHost(IPADDRESS);
        factory.setPort(PORT);
        Connection conn = factory.newConnection();
        System.out.println("conn.isOpen() = " + conn.isOpen());
        return conn;
    }

    // --------------------------------------------------创建Connection------------------------------------
    @Test
    public void testConnection() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);
        factory.setVirtualHost(virtualHost);
        factory.setHost(IPADDRESS);
        factory.setPort(PORT);
        Connection conn = factory.newConnection();
        System.out.println("conn.isOpen() = " + conn.isOpen());
    }

    @Test
    public void testConnectionURI() throws Exception, KeyManagementException, URISyntaxException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://root:root123@10.10.132.164:5672");
        Connection connection = factory.newConnection();
        System.out.println("connection = " + connection.isOpen());
    }
    // --------------------------------------------------创建交换器队列------------------------------------

    @Test
    public void testExchangeDeclare() throws Exception {
        Connection connection = getConnection();
        Channel channel = connection.createChannel();
        // 渠道--创建交换器，创建队列，
        String exchange_name = "test_exchange";
        String routingKey = "rk1";
        channel.exchangeDeclare(exchange_name, "direct", true);
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, exchange_name, routingKey);
    }
}
