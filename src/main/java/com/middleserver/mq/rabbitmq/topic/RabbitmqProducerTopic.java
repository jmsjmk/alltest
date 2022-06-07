package com.middleserver.mq.rabbitmq.topic;

import com.middleserver.mq.rabbitmq.RabbitMqTools;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

/**
 * Created by jiamingku on 2019/4/13.
 */
@SuppressWarnings("all")
public class RabbitmqProducerTopic {
	private static final String EXCHANGE_NAME = "exchange_name_topic";
	private static final String ROUTE_KEY = "routingkey_demo";
	private static final String ROUTE_KEY1 = "routingkey_demo1";
	private static final String ROUTE_KEY2 = "routingkey_demo2";
	private static final String QUEUE_NAME = "queue_demo_topic";
	private static final String IP_ADDRESS = "127.0.0.1";
	private static final int port = 5672;


	public static void main(String[] args) throws Exception {

		Connection connnection = RabbitMqTools.getConnection();
		// 创建信道
		Channel channel = connnection.createChannel();
		// type different ，but name same  ，throw exception
		channel.exchangeDeclare(EXCHANGE_NAME, "topic", true, false, null);
		// 如果队列呢存在，你在声明必须保证属性一模一样否者抛出异常
		channel.queueDeclare("queue_demo", true, false, false, null);
		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTE_KEY);
		channel.queueBind("queue_demo", EXCHANGE_NAME, "#");

		String message = "hello wolrd";

		channel.basicPublish(EXCHANGE_NAME, ROUTE_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());


		channel.close();
		connnection.close();

	}
}
