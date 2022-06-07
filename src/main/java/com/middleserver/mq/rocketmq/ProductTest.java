package com.middleserver.mq.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 分区 / 分片 / 消息过滤 / 消息查询
 * <p>
 * 消息(message对象):
 * 1)可以设置延迟时间
 * 2)设置topic
 *
 * <p>
 * <p>
 * 1.连接nameServer
 * 2.jvm进程内唯一(组名字相同)
 * 3.同步异步是否有返回值注册回掉方法
 * 4.相关的一些配置
 * defaultMQProducer.setDefaultTopicQueueNums(3);        可以创建topic的数量
 * defaultMQProducer.setRetryTimesWhenSendAsyncFailed(0) 设置放发送失败之后的重试次数
 * defaultMQProducer.setSendMsgTimeout(10)               设置发送消息的超时时间
 * 5.延迟队列的使用是在,消息上面设置延迟等级
 */
@SuppressWarnings("all")
public class ProductTest {

    /**
     * 1.连接nameServer
     * 2.创建消息
     * =================
     * jvm进程内,也就是说:JVM中producer的producerGroup要唯一
     * <p>
     * 有返回值了就是同步的.
     */
    @Test
    public void sendSync() {
        try {
            String nameServerAddr = MqConstance.url;
            DefaultMQProducer defaultMQProducer = new DefaultMQProducer("FLMG2");
            defaultMQProducer.setNamesrvAddr(nameServerAddr);
            defaultMQProducer.start();
            for (int i = 0; i < 20; i++) {
                Message m = new Message("FLM2", "hello".getBytes(StandardCharsets.UTF_8));
                SendResult sendResult = defaultMQProducer.send(m);
                System.out.println(sendResult.getSendStatus());
                System.out.println(sendResult);
            }
            Thread.sleep(1000000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 1.连接nameServer
     * 2.创建消息
     * =================
     * jvm进程内,也就是说:JVM中producer的producerGroup要唯一
     * <p>
     * 有返回值了就是同步的.
     */
    @Test
    public void sendSync1() {
        try {
            String nameServerAddr = MqConstance.url;
            DefaultMQProducer defaultMQProducer = new DefaultMQProducer("FLMG2");
            defaultMQProducer.setNamesrvAddr(nameServerAddr);
            defaultMQProducer.start();


            DefaultMQProducer defaultMQProducer1 = new DefaultMQProducer("FLMG2");
            defaultMQProducer1.setNamesrvAddr(nameServerAddr);
            defaultMQProducer1.start();

            for (int i = 0; i < 20; i++) {
                Message m = new Message("FLM2", "hello".getBytes(StandardCharsets.UTF_8));
                SendResult sendResult = defaultMQProducer.send(m);
                System.out.println(sendResult.getSendStatus());
                System.out.println(sendResult);
            }
            Thread.sleep(1000000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void sendASync() {
        try {
            String nameServerAddr = MqConstance.url;
            DefaultMQProducer defaultMQProducer = new DefaultMQProducer("testWhat123456");
            defaultMQProducer.setNamesrvAddr(nameServerAddr);
            defaultMQProducer.setDefaultTopicQueueNums(3);
            defaultMQProducer.start();
            Message m = new Message("topTestWhat123", "tag123333", "keys11", "hello".getBytes(StandardCharsets.UTF_8));
            defaultMQProducer.send(m, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println(sendResult.getSendStatus());
                    System.out.println(sendResult);
                }

                @Override
                public void onException(Throwable e) {

                }
            });
            DefaultMQProducer defaultMQProducer1 = new DefaultMQProducer("testWhat");
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
    public void sendOneway() {
        try {
            String nameServerAddr = MqConstance.url;
            DefaultMQProducer defaultMQProducer = new DefaultMQProducer("testWhat");
            defaultMQProducer.setNamesrvAddr(nameServerAddr);
            defaultMQProducer.setRetryTimesWhenSendAsyncFailed(0);
            defaultMQProducer.setDefaultTopicQueueNums(2);
            defaultMQProducer.start();
            Message m = new Message("topTest12", "tag123", "keyswhat11", "sendOneway.".getBytes(StandardCharsets.UTF_8));
            defaultMQProducer.sendOneway(m);
            DefaultMQProducer defaultMQProducer1 = new DefaultMQProducer("testWhat");
            Thread.sleep(1000000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 延迟队列-发送是在消息体上面设置,延迟时间
     */
    @Test
    public void sendDelay() {
        try {
            String nameServerAddr = MqConstance.url;
            DefaultMQProducer defaultMQProducer = new DefaultMQProducer("testWhat");
            defaultMQProducer.setNamesrvAddr(nameServerAddr);
            defaultMQProducer.setRetryTimesWhenSendAsyncFailed(0);
            defaultMQProducer.setDefaultTopicQueueNums(2);
            defaultMQProducer.start();

            Message m = new Message("topTest12", "tag123", "keyswhat11", "sendOneway.".getBytes(StandardCharsets.UTF_8));
            m.setDelayTimeLevel(3);
            DefaultMQProducer defaultMQProducer1 = new DefaultMQProducer("testWhat");
            Thread.sleep(1000000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 延迟队列-发送是在消息体上面设置,延迟时间
     * <p>
     * No route info for this topic, topTest12:::::topic的权限需要检查下
     */
    @Test
    public void sendOrderedProducer() {
        try {
            String nameServerAddr = MqConstance.url;
            DefaultMQProducer defaultMQProducer = new DefaultMQProducer("testWhat");
            defaultMQProducer.setNamesrvAddr(nameServerAddr);
            defaultMQProducer.setRetryTimesWhenSendAsyncFailed(0);
            defaultMQProducer.setDefaultTopicQueueNums(2);
            defaultMQProducer.start();
            Message msg = new Message("topTest500", "tag123", "keyswhat113323", "sendOneway.".getBytes(StandardCharsets.UTF_8));
            SendResult sendResult = defaultMQProducer.send(msg, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    System.out.println("arg = " + arg);
                    Integer id = (Integer) arg;
                    int index = id % mqs.size();
                    return mqs.get(index);
                }
            }, 100000000);

            System.out.println("sendResult = " + sendResult);
            Thread.sleep(1000000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
