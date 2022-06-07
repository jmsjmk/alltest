package com.jiamingku.thead.park;

import org.junit.Test;

import java.util.concurrent.locks.LockSupport;

/**
 * 1)线程如果被中断park阻塞不了(无论多少次park都没有用), 但如果复位线程的中断状态,第一道park阻塞不住,第二道park可以,其他的可以被阻塞)
 * <p>
 * 2)unpark会唤醒park,调用(unpark)时机无论线程是否在park或者即将park都将阻塞不住
 * <p>
 * 3)中断的线程--park阻塞不住(跟wait类似) 线程恢复了中断状态,park就会阻塞住,需要两次以上
 * <p>
 * ====================================================
 * 啥时候能看这个？？？？？
 * <p>
 * https://blog.csdn.net/weixin_39687783/article/details/85058686?spm=1001.2014.3001.5501
 * <p>
 * 如何理解呢？线程中断了,park中断不了的原因是：
 * if (Thread::is_interrupted(thread, false)) {
 * // 线程执行了中断，返回
 * return;
 * }
 * park的工作原理就是获取一个许可(变量)，park之后就恢复这个许可恢复
 */
public class ParkInterruptTest0 {

    volatile static int tag = 0;

    /**
     * // t1.interrupt() -----只对启动了的线程有用,没启动没有效果
     *
     * @param args
     */
    public static void main(String[] args) {
        ParkInterruptTest0 parkInterruptTest0 = new ParkInterruptTest0();
        Runnable r1 = () -> parkInterruptTest0.test1();
        Thread t1 = new Thread(r1);

        t1.start();
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.interrupt();
        System.out.println("线程已经被中断.....");
        tag = 1;
    }

    public void test1() {
        while (tag != 1) {

        }
        System.out.println("当前线程--已经被中断了看看park能被阻塞不" + ",,当前线程的中断状态::::::" + Thread.currentThread().isInterrupted());
//        LockSupport.park();  // -----这个没阻塞住
        int count = 0;
        while (count < 1000) {
            count++;
            LockSupport.park();
            System.out.println("中断多少次都没啥用.......");
        }
        System.out.println("111");
        Thread.interrupted();
        System.out.println("Thread.currentThread().isInterrupted() = " + Thread.currentThread().isInterrupted());
        try {
            Thread.sleep(1100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LockSupport.park();  // -----这个阻塞住了
        System.out.println("222");
        LockSupport.park();
        while (true) {
            System.out.println(" === ");
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test3() {
        ParkInterruptTest0 parkInterruptTest0 = new ParkInterruptTest0();
        Runnable r1 = () -> parkInterruptTest0.test2();
        Thread t1 = new Thread(r1);
        t1.start();
        try {
            Thread.sleep(4000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LockSupport.unpark(t1);
        while (true) {

        }
    }

    public void test2() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LockSupport.park();
        System.out.println(" 被唤醒....1 ");
        System.out.println("=== = ");
    }

    @Test
    public void test4() {
        Thread t = new Thread() {
            @Override
            public void run() {
                while (true) {
                    int i = 0;
                    while (i < 1000000) {
                        i++;
                    }
                }
            }
        };
        t.start();
        t.interrupt();
        System.out.println("*********** is true" + t.isInterrupted());
        boolean b = true;
        while (b) {
            System.out.println("000000" + t.isInterrupted());
            LockSupport.unpark(t);
            int i = 0;
            while (i < 1000000000) {
                i++;
            }
        }
        try {
            Thread.sleep(1000000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
