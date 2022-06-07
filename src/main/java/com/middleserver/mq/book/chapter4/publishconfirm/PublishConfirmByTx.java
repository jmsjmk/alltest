package com.middleserver.mq.book.chapter4.publishconfirm;

import com.middleserver.mq.book.MqTools;
import com.middleserver.mq.book.chapter3.PublishTest;
import com.rabbitmq.client.Channel;
import org.junit.Test;

/**
 * Created by jiamingku on 2019/8/30.
 * 在使用RabbitMQ的时候，可以通过消息持久化操作来解决因为服务器的异常崩溃而导致的消息丢失，
 * 除此之外，我们还会遇到一个问题，当消息的生产者将消息发送出去之后，消息到底有没有正确地到达服务器呢?
 * mandatory能正确工作的原因就是，这个消息已经到了rabbitmq，只是路由不到消息了。才能将消息发送给客户端。所以跟所说的生产者确认是没有关系的事
 * {@link com.middleserver.mq.book.chapter4.TestMandatory} 前提必须的找到这个交换器--如果交换器找不到mandatory也是不行的
 *
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
public class PublishConfirmByTx {

    /**
     * 基于事务机制来执行的
     * 1.channel.txSelect
     * 2.channel.txCommit
     * 3.channel.txRollback
     * @throws Exception
     *
     * 不采取事务机制的话：交换器不存在,虽然server端很快能判断出来消息-但是客户端立马不能知道消息丢失了，
     * 采用了事务机制，立马就能通过异常来进行判断，防止消息丢失
     */
    @Test
    public void testBasicTx() throws Exception {
        Channel channel = MqTools.getChannel();
        String exchangeName = "exchange_publish_confirm";
        String queueName = "queue_confirm_1";
        String routeKey = "exchange_publish_confirm_key";
        channel.exchangeDeclare(exchangeName, "direct", true, false, null);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, routeKey);
        byte[] messageBytes = "Hello, world".getBytes();
        // 1.开启事务模式
        channel.txSelect();
        channel.basicPublish(exchangeName +333, routeKey, null, messageBytes);
        channel.txCommit();
    }

    /**
     * 基于事务机制来执行的
     * 1.channel.txSelect
     * 2.channel.txCommit
     * 3.channel.txRollback
     *
     * @throws Exception
     */
    @Test
    public void testBasicRollback() throws Exception {
        Channel channel = MqTools.getChannel();
        String exchangeName = "exchange_publish_confirm";
        String queueName = "queue_confirm_1";
        String routeKey = "exchange_publish_confirm_key";
        channel.exchangeDeclare(exchangeName, "direct", true, false, null);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, routeKey);
        byte[] messageBytes = "Hello, world".getBytes();
        try {
            // 1.开启事务模式
            channel.txSelect();
            channel.basicPublish(exchangeName, routeKey, null, messageBytes);
            int result = 1 / 0;
            channel.txCommit();
        } catch (Exception e) {
            e.printStackTrace();
            channel.txRollback();
        }
    }

    /**
     * 如果交换器 写错了。不能马上感觉到channel关闭,事务机制保,在消息成功接受之后
     * 才能被提交，所以。如果channel关闭了，那消息就完了。接收不到
     * <p>
     * {@link PublishTest#testNoExistExchange()}
     *
     * @throws Exception
     */
    @Test
    public void testNoExchange() throws Exception {
        Channel channel = MqTools.getChannel();
        String exchangeName = "exchange_publish_confirm";
        String queueName = "queue_confirm_1";
        String routeKey = "exchange_publish_confirm_key";
        channel.exchangeDeclare(exchangeName, "direct", true, false, null);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, routeKey);
        byte[] messageBytes = "Hello, world".getBytes();
        try {
            // 1.开启事务模式
            channel.txSelect();
            channel.basicPublish(exchangeName + "disdfsdfd", routeKey, null, messageBytes);
            channel.txCommit();
        } catch (Exception e) {
            e.printStackTrace();
            channel.txRollback();
        }
    }
    // ---------------------------------------------------------------事务机制能保证mq的发送消息失败的情况----但是会吸干mq的性能
    // ---------------------------------------------------------------事务机制能保证mq的发送消息失败的情况----但是会吸干mq的性能
    // ---------------------------------------------------------------事务机制能保证mq的发送消息失败的情况----但是会吸干mq的性能
    // ---------------------------------------------------------------事务机制能保证mq的发送消息失败的情况----但是会吸干mq的性能
    // ---------------------------------------------------------------事务机制能保证mq的发送消息失败的情况----但是会吸干mq的性能
    // ---------------------------------------------------------------事务机制能保证mq的发送消息失败的情况----但是会吸干mq的性能
    // ---------------------------------------------------------------事务机制能保证mq的发送消息失败的情况----但是会吸干mq的性能
    @Test
    public void testBatchAck() throws Exception {
        Channel channel = MqTools.getChannel();
        String exchangeName = "exchange_publish_confirm";
        String queueName = "queue_confirm_1";
        String routeKey = "exchange_publish_confirm_key";
        channel.exchangeDeclare(exchangeName, "direct", true, false, null);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, routeKey);
        byte[] messageBytes = "Hello, world".getBytes();
        // 1.开启事务模式
        // 不用发送一次将信道设置成为事务模式--不需要没次都这么操作可以提取到外面去
        channel.txSelect();
        for (int i = 0; i < 1000; i++) {
            try {
                /** 一条条的提交-保证事务---发送之后会出现阻塞-等待mq的回应才能继续发送下一条
                 *
                 * 发布确认机制最大的好处就是他是异步的,一旦发布消息在等信息返回确认的同时,可以继续发送
                 *
                 * */
                channel.basicPublish(exchangeName + "disdfsdfd", routeKey, null, messageBytes);
                channel.txCommit();
            } catch (Exception e) {
                e.printStackTrace();
                channel.txRollback();
            }
        }
    }
}
