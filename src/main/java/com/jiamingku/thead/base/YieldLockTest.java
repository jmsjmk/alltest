package com.jiamingku.thead.base;

/**
 * Created by jiamingku on 2020/3/3.
 * <p>
 * jstack 就可以看出来 yield是不释放锁的
 */
public class YieldLockTest {

    public synchronized void a() throws InterruptedException {
        while (true) {
            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
//            Thread.sleep(1000L);
            Thread.yield();
            System.out.println("yield method complete..");
        }
    }

    public static void main(String[] args) {
        YieldLockTest t = new YieldLockTest();
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                try {
                    t.a();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t2 = new Thread("t2") {
            @Override
            public void run() {
                try {
                    t.a();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t1.start();

        t2.start();
    }
}
