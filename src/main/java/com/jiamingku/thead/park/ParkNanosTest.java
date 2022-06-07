package com.jiamingku.thead.park;

import java.util.concurrent.locks.LockSupport;

/**
 * 阻塞一段时间
 */
public class ParkNanosTest {
    static ParkNanosTest parkInteruptTest = new ParkNanosTest();

    public static void main(String[] args) {
        Thread thread = new Thread("ParkNanosTest") {
            @Override
            public void run() {
                parkInteruptTest.lockTest();
            }
        };
        thread.start();
        try {
            System.out.println("主线程停顿2s!!");
            Thread.sleep(1 * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(5 * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /**
         *  1)无所谓了,线程没park你调用了unPark没有啥效果,不过你想park，第一次是park不住的
         *  2)并且不会发生异常
         */
        LockSupport.unpark(thread);
        System.out.println("thread.isAlive = " + thread.isAlive());
    }

    public void lockTest() {
        Object o = new Object();
        long l1 = System.currentTimeMillis();
        LockSupport.parkNanos(o, 1000 * 1000 * 1000 * 1);
        long l2 = System.currentTimeMillis();
        System.out.println("阻塞  (l2-l1)ms = " + (l2 - l1) + "ms");
    }
}
