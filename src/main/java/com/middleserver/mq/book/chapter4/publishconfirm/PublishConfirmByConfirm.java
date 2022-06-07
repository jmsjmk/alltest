package com.middleserver.mq.book.chapter4.publishconfirm;

import com.middleserver.mq.book.MqTools;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.MessageProperties;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by jiamingku on 2019/8/30.
 * 在使用RabbitMQ的时候，可以通过消息持久化操作来解决因为服务器的异常崩溃而导致的消息丢失，
 * 除此之外，我们还会遇到一个问题，当消息的生产者将消息发送出去之后，消息到底有没有正确地到达服务器呢?
 * mandatory能正确工作的原因就是，这个消息已经到了rabbitmq，只是路由不到消息了。才能将消息发送给客户端。所以跟所说的生产者确认是没有关系的事
 * <p>
 * 客户端------>rabbitmq服务器之间的这断路消息丢失了。咋整？
 * <p>
 * 如果不进行特殊配置，默认情况下发送消息的操作是不会返 回任何信息给生产者的，也就是默认情况下生产者是不知道消息有没有正确地到达服务器。
 * 如果在消息到达服务器之前己经丢失,持久化操作也解决不了这个问题，因为消息根本没有到达服务器,何谈持久化?
 * <p>
 * 两种解决方式：
 * 1。通过事务机制实现
 * <p>
 * 2。发送方确认机制实现
 */
@SuppressWarnings("all")
public class PublishConfirmByConfirm {

    /**
     * 基于事务机制来执行的
     * 1.channel.txSelect
     * 2.channel.txCommit
     * 3.channel.txRollback
     * ==========================================
     * 基于发布确认模式
     * <p>
     * confirmSelect
     * waitForConfirms
     * <p>
     * 两种机制是互斥的不能同时执行
     *
     * @throws Exception
     */
    @Test
    public void testBasicConfirmSelect() throws Exception {
        Channel channel = MqTools.getChannel();
        String exchangeName = "exchange_publish_confirm_byselect";
        String queueName = "queue_confirm_2";
        String routeKey = "exchange_publish_confirm_key";
        channel.exchangeDeclare(exchangeName, "direct", true, false, null);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, routeKey);
        byte[] messageBytes = "Hello, world".getBytes();
        // 1.发布确认模式
        channel.confirmSelect();
        channel.basicPublish(exchangeName, routeKey, null, messageBytes);
        // 2.信道不开启select模式就回抛出异常，将  channel.confirmSelect(); 注释掉就发生异常
        if (!channel.waitForConfirms()) {
            System.out.println("send message failed");
        }
    }

    /**
     * 测试异常,不开启确认模式，channel.confirmSelect();
     *
     * @throws Exception
     */
    @Test
    public void testConfiremException() throws Exception {
        Channel channel = MqTools.getChannel();

        String exchangeName = "exchange_publish_confirm_byselect";
        String queueName = "queue_confirm_2";
        String routeKey = "exchange_publish_confirm_key";
        channel.exchangeDeclare(exchangeName, "direct", true, false, null);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, routeKey);
        byte[] messageBytes = "Hello, world".getBytes();
        // 1.发布确认模式
//        channel.confirmSelect();
        channel.basicPublish(exchangeName + "ddd", routeKey, null, messageBytes);
        // 2.信道不开启select模式就回抛出异常
        if (!channel.waitForConfirms()) {
            System.out.println("send message failed");
        }
    }

    /**
     * 测试异常：当交换器不存在的时候的异常能及时发现了
     * <p>
     * <p>
     * waitforConfirms方法的返回条件是=====在接收到ack，noack， 或者被终止断了
     *
     * @throws Exception
     */
    @Test
    public void testConfiremException1() throws Exception {
        Channel channel = MqTools.getChannel();

        String exchangeName = "exchange_publish_confirm_byselect";
        String queueName = "queue_confirm_2";
        String routeKey = "exchange_publish_confirm_key";
        channel.exchangeDeclare(exchangeName, "direct", true, false, null);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, routeKey);
        byte[] messageBytes = "Hello, world".getBytes();
        // 1.发布确认模式
        channel.confirmSelect();
        channel.basicPublish(exchangeName + "ddd", routeKey, null, messageBytes);
        // 2.信道不开启select模式就回抛出异常
        if (!channel.waitForConfirms()) {
            System.out.println("send message failed");
        }
    }


    /**
     * 测试异常：异常一般都是发生在confirm这个地方，就是终止断了等
     * <p>
     * <p>
     * waitforConfirms方法的返回条件是=====在接收到ack，noack， 或者被终止断了
     *
     * @throws Exception
     */
    @Test
    public void testConfiremExceptionNack() throws Exception {
        Channel channel = MqTools.getChannel();

        String exchangeName = "exchange_publish_confirm_byselect1111";
        String queueName = "queue_confirm_2";
        String routeKey = "exchange_publish_confirm_key";
        channel.exchangeDeclare(exchangeName, "direct", true, false, true, null);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, routeKey);
        byte[] messageBytes = "Hello, world".getBytes();
        // 1.发布确认模式
        channel.confirmSelect();
        channel.basicPublish(exchangeName, routeKey, null, messageBytes);
        // 2.信道不开启select模式就回抛出异常
        if (!channel.waitForConfirms()) {
            System.out.println("send message failed");
        }
    }


    /**
     * 同步方式保证消息正确传递mq
     *
     * @throws Exception
     */
    @Test
    public void testBatchConfirm() throws Exception {
        try {
            Channel channel = MqTools.getChannel();
            channel.confirmSelect();
            int MsgCount = 0;
            while (true) {
                channel.basicPublish("exchange", "rk", null, "batch confirm test".getBytes());
                //将发送出去 的消息存入缓存中，缓存可以是 //一个 ArrayList 或者 BlockingQueue 之类的
                if (++MsgCount >= 1000) {
                    MsgCount = 0;
                    try {
                        // ----只要是有回馈,说明前面的都成功了
                        if (channel.waitForConfirms()) {//1 将缓存中的消息清空
                            //将缓存中的消息重新发送
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        //将缓存中 的消息 重新发送
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 异步方式保证消息能正确传递到mq
     *
     * @throws Exception
     */
    @Test
    public void test5() throws Exception {
        try {
            Channel channel = MqTools.getChannel();

            channel.exchangeDeclare("test_plush_confirm1", "direct", true, false, null);

            channel.confirmSelect();

            SortedSet<Long> confirmSet = Collections.synchronizedSortedSet(new TreeSet<Long>());

            channel.addConfirmListener(new ConfirmListener() {
                public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                    System.out.println("deliveryTag = " + deliveryTag);
                    if (multiple) {
                        confirmSet.headSet(deliveryTag + 1).clear();
                    } else {
                        confirmSet.remove(deliveryTag);
                    }
                }

                public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                    System.out.println("Nack, SeqNo: " + deliveryTag + ", multiple: " + multiple);
                    if (multiple) {
                        confirmSet.headSet(deliveryTag + 1).clear();
                    } else {
                        confirmSet.remove(deliveryTag);
                    }
                }
            });

            while (true) {
                long nextSeqNo = channel.getNextPublishSeqNo();
                channel.basicPublish("test_plush_confirm1", "rk", MessageProperties.PERSISTENT_TEXT_PLAIN, "hello".getBytes());
                confirmSet.add(nextSeqNo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
