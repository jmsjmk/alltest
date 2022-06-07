package com.middleserver.mq.rabbitmq.testkazhu;

import com.middleserver.mq.rabbitmq.RabbitMqTools;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 注意这种模式，是多个队列--绑定到一个交换器上
 *
 * 千万别正混了多个消费者绑定到一个队列上
 *
 *
 */
@SuppressWarnings("all")
public class Customer111 {


	public static void main(String[] args) throws Exception {
		// 创建连接工厂
		//创建连接工厂
		Connection connection = new RabbitMqTools().getConnection();

		Channel channel = connection.createChannel();


		// 告诉服务器我们需要那个频道的消息，如果频道中有消息，就会执行回调函数handleDelivery
		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println("Customer Received '" + message + "'");
				channel.basicNack(envelope.getDeliveryTag() ,false, true);
			}
		};
		//自动回复队列应答 -- RabbitMQ中的消息确认机制
		channel.basicConsume("test_queue", false, consumer);
	}
}
