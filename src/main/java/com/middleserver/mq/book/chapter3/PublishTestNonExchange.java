package com.middleserver.mq.book.chapter3;

import com.middleserver.mq.book.MqTools;
import com.rabbitmq.client.*;

/**
 * Created by jiamingku on 2019/8/29.
 * 记住一个关键点：就是exchange不存在的情况下-你发送这个消息，是发送不不出去的，服务器立马知道了这个是非法的exchange
 *
 * 但是对于客户端来说，你不能立马感知到，所以。你在继续发送另外的消息就有可能丢失。这时候消息就丢失了根本没有进入队列
 */
@SuppressWarnings("all")
public class PublishTestNonExchange {

    /**
     * 这里面一定该有个池化技术在里面
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Connection connection = MqTools.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("channel_publish_test", "direct", true);
        channel.queueDeclare("channel_publish_test_queue", true, false, false, null);

        channel.queueBind("channel_publish_test_queue", "channel_publish_test", "testKey");
        byte[] messageBodyBytes = "Hello, world!".getBytes();

        channel.addShutdownListener(new ShutdownListener() {
            @Override
            public void shutdownCompleted(ShutdownSignalException cause) {
                System.out.println("cause = " + cause.getMessage());
                if (channel.isOpen()) {
                    System.out.println(" 渠道还是打开的1111111111111111111111 ");
                }
            }
        });

        /**
         * 发布消息如果指定的交换器不存在--这个channel是关闭的，并且不是马上就能感觉是关闭了。
         * mandatory==是找不到队列时候发送消息，如果找不到交换器时候，这个channnel就关闭了但是不够及时
         * 并且发送消息-发送的交换器不存在时候。你是无感知的
         */
        channel.basicPublish("channel_publish_test11111", "testKefffy", false,
                new AMQP.BasicProperties.Builder().expiration(String.valueOf(100000000)).build(), "Hello.World".getBytes());
        int i = 0;
        // ---如果是true的话，在轮训一定事件之后就开始抛出


        while (true) {
            if (channel.isOpen()) {
                System.out.println(i++);
            }
            channel.basicPublish("channel_publish_test", "testKey", new AMQP.BasicProperties.Builder().expiration(String.valueOf(100000000)).build(), "Hello.World".getBytes());
            System.out.println("发送完成 ");
            if (false) {
                break;
            }
        }
        // 如果上面的代码去掉，这个语句还会执行，如果事件很短，客户端的这个channel.isopen =true 的情况下，虽然能发，但是服务器已经不会处理这个消息了
        channel.basicPublish("channel_publish_test", "testKey", new AMQP.BasicProperties.Builder().expiration(String.valueOf(100000000)).build(), "Hello.World".getBytes());
        System.out.println(" 第二次发布 ");
        System.out.println("\"hell\" = " + "hell");
    }
}
