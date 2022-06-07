 TTL:rabbitmq支持两种形式
 1.就是队列的ttl
 1.1设置方式两种
 1.1.1 通过channel.queueDeclare 声明时候加入属性x-message-ttl 单位是毫秒
 1.1.2 通过属性 rabbitmqctl set_policy TTL "食" '{"message-ttl":60000}' --apply-to queues
 1.1.3 通过http api 进行设置

 2.就是消息的ttl
 2.1设置方式在channel.basicPublish方法中加入

 ===============================================
 优先级队列在：声明队列的时候指定参数  com.middleserver.mq.book.chapter4.PriorityQueue.testCreatePriorityQueue
 声明队列是TTL: com.middleserver.mq.book.chapter4.delyqueue.base.QueueTTLProduct
 发送消息指定TTL: channel.basicPublish("ttl_no_consumer", "testKey", new AMQP.BasicProperties.Builder().expiration(String.valueOf(100000000)).build(), messageBodyBytes);
 声明备胎交换器：com.middleserver.mq.book.chapter4.ExchangeAE
 声明死信队列：com.middleserver.mq.book.chapter4.delyqueue.demo2.DelayProduct
             可以设置死信交换器，死信交换器的的key，延迟队列就是常用的属性

 prc时候指定了一些属性：rpc -知道服务端将相应结果发送到那个队列，并且知道这个相应结果跟那个请求关联上就行了。其实就是两个属性。
                     但是用rabbitmq去实现rpc 好像是得不偿失。
               关键属性：replyTo
               关键属性：correlationId