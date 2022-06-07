package com.jiamingku.thead.base;

/**
 * Created by jiamingku on 2020/3/7.
 */
@SuppressWarnings("all")
public class ThreadDeadLockTest {

    public static void main(String[] args) {
        String lock1 = "A";
        String lock2 = "B";
        Thread thread = new Thread("t1") {
            @Override
            public void run() {
                synchronized (lock1) {
                    System.out.println("获取线程1的锁头");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock2) {
                        System.out.println("获取线程2的锁头");
                    }

                }
            }
        };
        thread.start();
        Thread thread2 = new Thread("t2") {
            @Override
            public void run() {
                synchronized (lock2) {
                    System.out.println("获取线程2的锁头");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock1) {
                        System.out.println("获取线程1的锁头");
                    }

                }
            }
        };
        thread2.start();
    }
}
