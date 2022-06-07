package com.jiamingku.thead.apitools;

/**
 * volatile i++不是原子操作
 * <p>
 * 个人理解：如果一个volatile变量被多核操作,同时写操作会如何进行?会产生锁.
 * 核心1在写完之后刷新那一刻之后对于其他核心再次使用是可见的.如果多核并行执行写操作虽然有缓存一致性协议保证，禁止同时操作缓存区锁指向的内存数据，会出现一个在等待，但是 volatile i; i++这样的操作是不安全的。解释如下
 * <p>
 * i++ 被 线程A 线程B同时拿到，
 * 恰好都要执行i++操作，由于volatile的语义会执行强制刷新，但是只会有一个刷新成功，加入A刷新成功了，但是对于B并没有从新load，所以在刷新，数据就是脏数据。所以不是线程安全的
 */
public class Counter {
    public volatile static int count = 0;

    public static void inc() {
        //这里延迟1毫秒，使得结果明显
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
        }

        count++;
    }

    public static void main(String[] args) {
        //同时启动1000个线程，去进行i++计算，看看实际结果
        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                public void run() {
                    Counter.inc();
                }
            }).start();
        }
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //这里每次运行的值都有可能不同,可能为1000
        System.out.println("运行结果:Counter.count=" + Counter.count);
    }
}