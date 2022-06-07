package com.middleserver.mq.rabbitmq.fanout;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 注意这种模式，是多个队列--绑定到一个交换器上
 *
 * 千万别正混了多个消费者绑定到一个队列上
 *
 *
 */
@SuppressWarnings("all")
public class Customer {
	private static final String queue_name = "hello-queue-fanout";
	private static final String exchange_name = "hello-exchange-fanout";
	private static String routeKey = "hola";
	private final static String QUEUE_NAME = "test";
	private final static String type = "fanout";

	public static void main(String[] args) throws IOException, TimeoutException {
		// 创建连接工厂
		//创建连接工厂
		ConnectionFactory factory = new ConnectionFactory();
		//设置RabbitMQ相关信息
		factory.setHost("localhost");
		factory.setUsername("guest");
		factory.setPassword("guest");
		factory.setPort(5672);

		//创建一个新的连接
		Connection connection = factory.newConnection();

		//创建一个通道
		Channel channel = connection.createChannel();
		/**
		 * 有生产者有消费者两端的属性一定要严格控制必须相同的
		 *
		 *
		 */
		channel.exchangeDeclare(exchange_name, type, true, true, null);

		//声明要关注的队列
		channel.queueDeclare(queue_name, false, false, false, null);

		// 这个就是平时所说的绑定
		channel.queueBind(queue_name, exchange_name, routeKey);
		System.out.println("Customer Waiting Received messages");

		// 告诉服务器我们需要那个频道的消息，如果频道中有消息，就会执行回调函数handleDelivery
		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println("Customer Received '" + message + "'");
			}
		};
		//自动回复队列应答 -- RabbitMQ中的消息确认机制
		channel.basicConsume(queue_name, true, consumer);
	}
}
