package com.middleserver.mq.book.chapter4;

import com.middleserver.mq.book.MqTools;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Created by jiamingku on 2019/7/13.
 */
public class RPCServer {
    private static final String RPC_QUEUE_NAME = "rpc_queue";

    public static void main(String[] args) throws Exception {
        Connection connection = MqTools.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(RPC_QUEUE_NAME, true, false, false, null);
        channel.basicQos(1);
        System.out.println(" [x] A waiting RPC requests ");
        Consumer consumer = new DefaultConsumer(channel) {
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(" \"接收关联id：\"+ properties.getCorrelationId() = " + "接收关联id：" + properties.getCorrelationId());
                System.out.println("\"回复队列：\"+ properties.getReplyTo() = " + "回复队列：" + properties.getReplyTo());
                AMQP.BasicProperties replyProps = new AMQP.BasicProperties.Builder().correlationId(properties.getCorrelationId()).build();

                System.out.println("replyProps = " + replyProps);
                System.out.println("envelope = " + envelope);
                String response = "";
                try {
                    String message = new String(body, "UTF-8");
                    int n = Integer.parseInt(message);
                    System.out.println(" [.] fib(" + message + ")");
                    response += fib(n);
                } catch (Exception e) {
                    System.out.println(" [.] " + e.toString());
                } finally {
                    channel.basicPublish("", properties.getReplyTo(), replyProps, response.getBytes("UTF-8"));
                    channel.basicAck(envelope.getDeliveryTag(), false);
                    System.out.println(" 发送完成 ");
                }
            }
        };
        channel.basicConsume(RPC_QUEUE_NAME, false, consumer);
    }


    private static int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fib(n - 1) + fib(n - 2);
    }
}
