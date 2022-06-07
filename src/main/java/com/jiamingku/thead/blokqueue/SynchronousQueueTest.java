package com.jiamingku.thead.blokqueue;

import java.util.concurrent.SynchronousQueue;

/**
 * Created by jiamingku on 2020/2/28.
 */
public class SynchronousQueueTest {
    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue synchronousQueue = new SynchronousQueue();
        boolean b = synchronousQueue.offer("1");
        System.out.println("offer " + b);
        System.out.println("synchronousQueue.size() = " + synchronousQueue.size());
        Object resul = synchronousQueue.offer("1");
        System.out.println("resul = " + resul);
        // ----------------------------------------
        synchronousQueue.put(1); // 一只阻塞
        System.out.println("阻塞么！！");
    }
}
