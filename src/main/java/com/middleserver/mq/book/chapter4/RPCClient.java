package com.middleserver.mq.book.chapter4;


import com.middleserver.mq.book.MqTools;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

import java.util.UUID;

/**
 * Created by jiamingku on 2019/7/13.
 */
public class RPCClient {
    private Connection connection;
    private Channel channel;
    private String requestQueueName = "rpc_queue";
    private String replyQueueName;
    private QueueingConsumer consumer;

    public RPCClient() throws  Exception { //省略了创建 Connection 和 Channel 的过程，具体可 以参考 1. 4.4 节
        connection = MqTools.getConnection();
        channel = connection.createChannel();
        channel.queueDeclare(requestQueueName, true, false,false,null);
        replyQueueName = channel.queueDeclare().getQueue();
        consumer = new QueueingConsumer(channel);
        channel.basicConsume(replyQueueName, true, consumer);
    }

    public void close() throws Exception {
        connection.close();
    }

    public String call(String message) throws Exception {
        String response = null;
        String corrld = UUID.randomUUID().toString();
        // 1.设置延迟队列
        // 2.设置绑定队列
        AMQP.BasicProperties props = new AMQP.BasicProperties.Builder().correlationId(corrld).replyTo(replyQueueName).build();
        for (int i =0 ; i <100; i ++) {
            channel.basicPublish("", requestQueueName, props, message.getBytes());
            System.out.println("发送:" +i);
        }
        /**
         * 其实对于这单代码其实里面说的东西很多，就拿我们现在的demo来首，发送了100条消息，发送出去
         * 对应的服务器端会相应回来100条，消费者要从这100条中选出来匹配自己条件的，
         *
         * 1。一个connnection创建一个队列，这样在集群环境中，多个机器就有多个队列，服务根据自身的replayto 会告知服务端发送到哪里
         *
         * 2。所以在消费的时候，如果不匹配应该立马noack，这样这个消息还会投递给其他的消费者。否者投递不到其他的消费者中
         *
         * 3。疑问就是，这样消息投递到消费者这里面了。在拒绝会不会造成性能的影响问题，我觉得应该不会，rabbitmq在投递消息的时候，消息是发送了。但是在服务器端，只是打了一个标签
         *
         *    当你ack，他才会删除，否者他一致保留。你noack发送的时候，只是发送一个报文，告诉服务器将这个消息的标签 换成 可以投递的，就算搞定了。
         *
         *
         */
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            System.out.println(delivery.getProperties());
            if (delivery.getProperties().getCorrelationId().equals(corrld)) {
                response = new String(delivery.getBody());
                System.out.println(" break " );
                break;

            }
        }
        return response;
    }

    public static void main(String args[]) throws Exception {
        RPCClient fibRpc = new RPCClient();
        System.out.println(" [x) Requesting fib(30)");
        String response = fibRpc.call("30");
        System.out.println(" [.) Got '" + response + "'");
        fibRpc.close();
    }

}
