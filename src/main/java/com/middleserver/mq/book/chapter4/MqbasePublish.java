package com.middleserver.mq.book.chapter4;

import com.middleserver.mq.book.MqTools;
import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiamingku on 2019/7/13.
 */
@SuppressWarnings("all")
public class MqbasePublish {

    /**
     * 这种方法可以变化下通过alternate交换器-也就是备份交换器来实现
     * {@link }
     * @throws Exception
     */
    @Test
    public void testMandatory() throws Exception {
        MqTools mqTools = new MqTools();
        Connection connection = mqTools.getConnection();
        Channel channel = connection.createChannel();
        String exchangeName = "t_mandetory_exchange";
        String queueName = "t_mandetory_queue";
        String roukingKey = "mandetoryKey";

        channel.exchangeDeclare(exchangeName, "topic", true, false, null);
        channel.queueDeclare(queueName, true, false, false, null);

        channel.queueBind(queueName, exchangeName, roukingKey);

        // mandetory=true 如果根据路由件找不到的话，就跟给你退回来
        channel.basicPublish(exchangeName, "what", true, MessageProperties.PERSISTENT_TEXT_PLAIN, "hello".getBytes());

        channel.addReturnListener(new ReturnListener() {
            @Override
            public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("replyCode = " + replyCode);
                System.out.println("replyText = " + replyText);
                System.out.println("exchange = " + exchange);
                System.out.println("routingKey = " + routingKey);
                System.out.println("properties = " + properties);
                String message = new String(body);
                System.out.println("message = " + message);
            }
        });

        while(channel.isOpen()) {
            System.out.println("channel.isOpen = " + channel.isOpen());
            Thread.sleep(1000L);
        }
    }

    @Test
    public void testMandatoryOtherImpl() throws Exception {
        MqTools mqTools = new MqTools();

        Connection connection = mqTools.getConnection();


        Channel channel = connection.createChannel();

        Map<String, Object> map = new HashMap<>();
        map.put("alternate-exchange","myAe");

        String exchangeName = "t_mandetory_exchange_other";
        String queueName = "t_mandetory_queue_other";
        String roukingKey = "mandetoryKey_other";

        channel.exchangeDeclare(exchangeName, "topic", true, false, map);
        channel.exchangeDeclare("myAe","fanout", true, false, null);


        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueDeclare("unrouteQueue", true, false, false, null);

        channel.queueBind(queueName, exchangeName, roukingKey);

        channel.queueBind("unrouteQueue","myAe", "");



        // mandetory 如果根据路由件找不到的话，就跟给你退回来
        // 如果找不到就直接丢弃消息
        channel.basicPublish(exchangeName, "what", MessageProperties.PERSISTENT_TEXT_PLAIN, "hello".getBytes());

        channel.addReturnListener(new ReturnListener() {
            @Override
            public void handleReturn(int replyCode, String replyText, String exchange,
                                     String routingKey, AMQP.BasicProperties properties,
                                     byte[] body) throws IOException {
                System.out.println("replyCode = " + replyCode);
                System.out.println("replyText = " + replyText);
                System.out.println("exchange = " + exchange);
                System.out.println("routingKey = " + routingKey);
                System.out.println("properties = " + properties);
                String message = new String(body);
                System.out.println("message = " + message);
            }
        });
    }

    @Test
    public void testImmediate() {
        try {
            MqTools mqTools = new MqTools();

            Connection connection = mqTools.getConnection();


            Channel channel = connection.createChannel();

            String exchangeName = "t_mandetory_exchange";
            String queueName = "t_mandetory_queue";
            String roukingKey = "mandetoryKey";

            channel.exchangeDeclare(exchangeName, "topic", true, false, null);
            channel.queueDeclare(queueName, true, false, false, null);

            channel.queueBind(queueName, exchangeName, roukingKey);

            // mandetory 如果根据路由件找不到的话，就跟给你退回来
            // 如果找不到就直接丢弃消息
            channel.basicPublish(exchangeName, roukingKey, false, MessageProperties.PERSISTENT_TEXT_PLAIN, "hello".getBytes());

            channel.addReturnListener(new ReturnListener() {
                @Override
                public void handleReturn(int replyCode, String replyText, String exchange,
                                         String routingKey, AMQP.BasicProperties properties,
                                         byte[] body) throws IOException {
                    System.out.println("replyCode = " + replyCode);
                    System.out.println("replyText = " + replyText);
                    System.out.println("exchange = " + exchange);
                    System.out.println("routingKey = " + routingKey);
                    System.out.println("properties = " + properties);
                    String message = new String(body);
                    System.out.println("message = " + message);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
