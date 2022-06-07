package com.middleserver.mq.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1.消费的模式：pull/push
 * 2.消息消费：有两个模式广播/集群模式
 * 3.消息在消费之后.
 *   在一条消息明显消费失败了,在管理端，看两个
 */
public class ConsumerTest {
    static String data = "{\"mappings\":{\"perf\":{\"properties\":{\"avg\":{\"type\":\"long\"},\"count\":{\"type\":\"long\"},\"extra1\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"extra2\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"extra3\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"jvmInfo\":{\"properties\":{\"host\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"ip\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"pid\":{\"type\":\"long\"},\"port\":{\"type\":\"long\"}}},\"max\":{\"type\":\"long\"},\"min\":{\"type\":\"long\"},\"namespace\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"p95\":{\"type\":\"long\"},\"p99\":{\"type\":\"long\"},\"p995\":{\"type\":\"long\"},\"stdDev\":{\"type\":\"long\"},\"subtag\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"bizDef\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"sum\":{\"type\":\"long\"},\"time\":{\"type\":\"date\"}}}}}";


    public static void main(String[] args) {
        System.out.println(data);
        System.out.println(" = ");
        SimpleDateFormat simpleDateFormat = null;
        try {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date d = simpleDateFormat.parse("2022-04-22");
            Date d1 = new Date();
            long d11 = (d.getTime() - d1.getTime()) / 24 / 60 / 60 / 1000;
            System.out.println(d11);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void recive() {
        try {
            String nameServerAddr = MqConstance.url;
            DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer("FLM21");
            defaultMQPushConsumer.setNamesrvAddr(nameServerAddr);
            defaultMQPushConsumer.subscribe("FLM2", "*");
            defaultMQPushConsumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                    for (MessageExt messageExt : msgs) {
                        System.out.println(new String(messageExt.getBody()));
                    }
                    System.out.println(context);
                    System.out.println("=====");
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
            });
            defaultMQPushConsumer.start();
            Thread.sleep(1000000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void recive1() {
        try {
//            for (int i = 0; i < 3; i++) {
            String nameServerAddr = MqConstance.url;
            DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer("FLMG2");
            defaultMQPushConsumer.setNamesrvAddr(nameServerAddr);
            
            defaultMQPushConsumer.subscribe("FLM2", "*");
//            defaultMQPushConsumer.setMessageModel();
            AtomicInteger i = new AtomicInteger(0);
            defaultMQPushConsumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                    for (MessageExt messageExt : msgs) {
                        System.out.println(new String(messageExt.getBody()));
                    }

                    System.out.println(" 返回失败................................看看小小队列的索引信息");
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
            });
            defaultMQPushConsumer.start();
//            }
            Thread.sleep(1000000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
