package com.jiamingku.thead.base;

import org.junit.Test;

/**
 * 中断的线程不能wait.无论是先执行了wait后在进行中断的. 还是先中断了之后在进行wait操作
 */
public class TestInterupt {

    /**
     * 不启动线程-这个方法是不好使的
     */
    @Test
    public void test1() {
        Thread t1 = new Thread(() -> {
        }, "TestNoStart");

        boolean b = t1.isInterrupted();
        System.out.println("b = " + b);
        t1.interrupt();
        b = t1.isInterrupted();
        System.out.println("b = " + b);
    }


    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000L);
                } catch (InterruptedException e) {
                    System.out.println(" 发生异常 ");
                    System.out.println("this.isInterrupted() = " + this.isInterrupted());
                }
                try {
                    Object o = new Object();
                    this.interrupt(); // 先中断-在进入wait,或者先wait在中断都是一样的发生异常
                    synchronized (o) {
                        o.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println(" 中断状态的线程是不在在那个对象中wait的.... ");
                }
                System.out.println(" =====再次wait ");
            }
        };

        thread.start();
        System.out.println(" main is running. ");
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(" 准备中断那个线程");

        thread.interrupt();
        try {
            Thread.sleep(100000000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
