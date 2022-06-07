package com.jiamingku.thead.blokqueue;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 1.延迟队列--里面使用的是优先级队列
 * 2.
 */
public class DelayQueueTest {

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        DelayQueue<DelayedUser> delayQueue = new DelayQueue<>();
        delayQueue.put(null);
    }
}

class DelayedQueueProducer implements Runnable {
    private DelayQueue<DelayedUser> delayQueue;

    private Integer messageCount;

    private long delayedTime;

    @Override
    public void run() {
        for (int i = 0; i < messageCount; i++) {
            try {
                DelayedUser delayedUser = new DelayedUser(
                        new Random().nextInt(1000) + "", delayedTime);
                System.out.println("put delayedUser {}" + delayedUser);
                delayQueue.put(delayedUser);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

class DelayedQueueConsumer implements Runnable {

    private DelayQueue<DelayedUser> delayQueue;

    private int messageCount;

    @Override
    public void run() {
        for (int i = 0; i < messageCount; i++) {
            try {
                DelayedUser element = delayQueue.take();
                System.out.println("take {}" + element);
            } catch (InterruptedException e) {
                System.out.println("e = " + e);
            }
        }
    }
}

class DelayedUser implements Delayed {

    private String name;
    private long avaibleTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAvaibleTime() {
        return avaibleTime;
    }

    public void setAvaibleTime(long avaibleTime) {
        this.avaibleTime = avaibleTime;
    }

    public DelayedUser(String name, long delayTime) {
        this.name = name;
        //avaibleTime = 当前时间+ delayTime
        this.avaibleTime = delayTime + System.currentTimeMillis();
    }

    /**
     * 应该是延迟执行吧。------------------
     *
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        //判断avaibleTime是否大于当前系统时间，并将结果转换成MILLISECONDS
        long diffTime = avaibleTime - System.currentTimeMillis();
        return unit.convert(diffTime, TimeUnit.MILLISECONDS);
    }

    /**
     * 比较接口----用户队列排序
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Delayed o) {
        //compareTo用在DelayedUser的排序
        return (int) (this.avaibleTime - ((DelayedUser) o).getAvaibleTime());
    }
}