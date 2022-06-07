package com.jiamingku.thead.blokqueue;

import org.junit.Test;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * 延迟队列的基础
 */
public class PriorityBlockQueueTest {

    /**
     * 不实现比较接口报错
     * <p>
     * 扩容时候不影响读。确定完容量，数据copy时候在上锁更换内容
     */
    @Test
    public void test() {
        PriorityBlockingQueue<T> priorityBlockingQueue = new PriorityBlockingQueue<>();
        T t = new T();
        priorityBlockingQueue.add(t);
    }

    class T {

    }

    /**
     * 堆就是顶点最小,别的不用记录就
     */
    @Test
    public void test2() {
        PriorityBlockingQueue<Integer> priorityBlockingQueue = new PriorityBlockingQueue<>();
        priorityBlockingQueue.add(8);
        priorityBlockingQueue.add(18);
        priorityBlockingQueue.add(17);
        priorityBlockingQueue.add(29);
        priorityBlockingQueue.add(2);
        priorityBlockingQueue.add(3);
        System.out.println("priorityBlockingQueue = " + priorityBlockingQueue);

        try {
            Integer a = priorityBlockingQueue.take();
            System.out.println(a);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("priorityBlockingQueue = " + priorityBlockingQueue);

    }
}
