package com.jiamingku.thead.base;

/**
 * 线程在block时候(synchronized),你中断也不可能阻止他阻塞
 */
public class TestInterupt1 {


    public static void main(String[] args) {

        TestInterupt1 t = new TestInterupt1();
        Thread thread1 = new Thread("one") {
            @Override
            public void run() {
                t.a();
            }
        };
        Thread thread2 = new Thread("two") {
            @Override
            public void run() {
                t.a();
            }
        };
        thread1.start();
        thread2.start();
        System.out.println("-------------------");
        thread2.interrupt();
        System.out.println(thread2.isInterrupted());
        System.out.println(thread2.isInterrupted());
        System.out.println(thread2.isInterrupted());
        System.out.println(thread2.isInterrupted());
        System.out.println(thread2.isInterrupted());
        try {
            Thread.sleep(1000000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void a() {
        try {
            Thread.sleep(100000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
