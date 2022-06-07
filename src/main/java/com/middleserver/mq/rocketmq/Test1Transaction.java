package com.middleserver.mq.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class Test1Transaction {

    @Test
    public void send() {
        try {
            String nameServerAddr = "dev-rq01-a.mq.01zhuanche.com:9876";
            DefaultMQProducer defaultMQProducer = new DefaultMQProducer("testWhat");
            defaultMQProducer.setNamesrvAddr(nameServerAddr);
            defaultMQProducer.start();
            Message m = new Message("topTest1", "hello".getBytes(StandardCharsets.UTF_8));
            SendResult sendResult = defaultMQProducer.send(m);
            System.out.println(sendResult.getSendStatus());
            System.out.println(sendResult);
            Thread.sleep(1000000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * sendOneWay
     * senc(+callBack);
     */
    @Test
    public void batchsend() {
        try {
            String nameServerAddr = "dev-rq01-a.mq.01zhuanche.com:9876";
            DefaultMQProducer defaultMQProducer = new DefaultMQProducer("testWhat");
            defaultMQProducer.setNamesrvAddr(nameServerAddr);
            defaultMQProducer.start();

            List<Message> messages = new ArrayList<>();

            Message m0 = new Message("topTest1", "hello".getBytes(StandardCharsets.UTF_8));
            Message m1 = new Message("topTest1", "hello".getBytes(StandardCharsets.UTF_8));
            Message m2 = new Message("topTest1", "hello".getBytes(StandardCharsets.UTF_8));
            Message m3 = new Message("topTest1", "hello".getBytes(StandardCharsets.UTF_8));
            messages.add(m0);
            messages.add(m2);
            messages.add(m1);
            messages.add(m3);
            String mId = m3.getBuyerId();

            System.out.println("mId = " + mId);

            SendResult sendResult = defaultMQProducer.send(messages);
            System.out.println(sendResult.getSendStatus());
            System.out.println(sendResult);
            Thread.sleep(1000000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void recive() {
        try {
            String nameServerAddr = "dev-rq01-a.mq.01zhuanche.com:9876";
            DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer("testWhat");
            defaultMQPushConsumer.setNamesrvAddr(nameServerAddr);
            defaultMQPushConsumer.subscribe("topTest1", "*");
            defaultMQPushConsumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                    System.out.println("size:" + msgs.size());
                    for (MessageExt messageExt : msgs) {
                        System.out.println(new String(messageExt.getBody()));
                    }
                    System.out.println("=====");
                    System.out.println(context);
                    return null;
                }
            });
            defaultMQPushConsumer.start();
            Thread.sleep(1000000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
