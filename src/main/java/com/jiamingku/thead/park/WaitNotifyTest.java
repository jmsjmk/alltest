package com.jiamingku.thead.park;

import java.util.concurrent.locks.LockSupport;

/**
 * park / unpark 之间有先后顺序的问题
 * <p>
 * wait / notify 之间没有先后顺序的关系
 */
public class WaitNotifyTest {

    private volatile boolean flag = false;

    public synchronized void begin() {
        try {
            System.out.println("wait..in");
            this.wait();
            System.out.println("wait..out");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void end() {
        try {
            System.out.println("notifyAll..in");
            this.notifyAll();
            System.out.println("notifyAll..out");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void park() {
        System.out.println("park in");
        LockSupport.park();
        System.out.println("park out");
    }

    public static void main(String[] args) {
        WaitNotifyTest waitNotifyTest = new WaitNotifyTest();
        new Thread(() -> {
            waitNotifyTest.end();
        }, "endThred").start();
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            waitNotifyTest.begin();
        }, "waitThred").start();
        System.out.println("------------------------------");
        System.out.println("------------------------------");
        WaitNotifyTest waitNotifyTest1 = new WaitNotifyTest();
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                waitNotifyTest1.park();
            }
        };
        t.start();
        LockSupport.unpark(t);


        try {
            Thread.sleep(1000000000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
