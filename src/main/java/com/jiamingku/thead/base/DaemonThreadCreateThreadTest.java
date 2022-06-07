package com.jiamingku.thead.base;

import org.junit.Test;

/**
 * Created by jiamingku on 2019/9/29.
 * <p>
 * https://www.cnblogs.com/ziq711/p/8228255.html
 * <p>
 * 守护线程创建出来的线程还是守护线程
 */
@SuppressWarnings("all")
public class DaemonThreadCreateThreadTest {

    /**
     * 设置参数一定要在启动前设置,否则抛出异常
     * 普通线程，主线程退出之后守护线程还在执行
     * 守护线程创建出来的线程还是守护线程
     * 非守护线程创建出来的线程非守护线程.守护线程finally方法不一定能执行到
     */
    @Test
    public void testSetdaemon222() throws Exception {
        DaemonThread daemonThread = new DaemonThread("DaemonThread");
        /*
         * 默认都是非守护线程
         */
        System.out.println("daemonThread.isDaemon() = " + daemonThread.isDaemon());
        // 特殊就特俗在idea则
        daemonThread.start();
        Thread.sleep(100000000L);
    }

    // -------------------------------------------------------------------------

    /**
     * 设置参数一定要在启动前设置,否则抛出异常
     */
    @Test
    public void testSetdaemon() throws Exception {
        DaemonThread daemonThread = new DaemonThread("lear");
        System.out.println("daemonThread.isDaemon() = " + daemonThread.isDaemon());
        daemonThread.setDaemon(true);
        System.out.println("daemonThread.isDaemon() = " + daemonThread.isDaemon());
        daemonThread.start();
        Thread.sleep(100000000L);
    }

    /**
     * 设置参数一定要在启动前设置,否则抛出异常
     */
    @Test
    public void testSetdaemonDead() throws Exception {
        DaemonThread daemonThread = new DaemonThread("DaemonThread");
        System.out.println("daemonThread.isDaemon() = " + daemonThread.isDaemon());
        System.out.println("daemonThread.isDaemon() = " + daemonThread.isDaemon());
        daemonThread.start();
        daemonThread.setDaemon(true);
        Thread.sleep(11110L);
    }


    /**
     * 如果启动了设置守护线程就报错
     */
    @Test
    public void testSetdaemon2() throws Exception {
        DaemonThread daemonThread = new DaemonThread("DaemonThread");
        /**
         * 直接创建出来的线程，是非守护的
         */
        System.out.println("daemonThread.isDaemon() = " + daemonThread.isDaemon());
        System.out.println("daemonThread.isDaemon() = " + daemonThread.isDaemon());
        daemonThread.start();
        daemonThread.setDaemon(true);
        Thread.sleep(100000000L);
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("进入主线程：" + Thread.currentThread().getName());
        DaemonThreadFinally daemonThread = new DaemonThreadFinally();
        Thread thread = new Thread(daemonThread);

        boolean b = thread.isAlive();
        System.out.println("thread.isAlive() (---start方法前打印---) = " + b);

        thread.setDaemon(true); // 设为守护线程
        thread.start();
        b = thread.isAlive();
        System.out.println("thread.isAlive() (---start方法后打印---) " + b);
        /**
         * 主线程一但退出--守护线程立马退出-并且退出的很干脆，finally语句都不会执行到
         */
        Thread.sleep(1000L);
        System.out.println("退出主线程：" + Thread.currentThread().getName());
    }

    class Thread3 extends Thread {
        @Override
        public void run() {
            System.out.println("---------------this.isDaemon() = " + this.isDaemon());
        }
    }

    public class DaemonThread extends Thread {
        public DaemonThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            try {
                while (this.isAlive()) {
                    Thread.sleep(100L);
                    System.out.println("this.isDaemon() = " + this.isDaemon());
                    Thread3 t = new Thread3();
                    t.start();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(" 守护线程走的太快---没来的级回收 ");
            }
        }
    }

    static class DaemonThreadFinally implements Runnable {

        @Override
        public void run() {
            System.out.println("进入守护线程：" + Thread.currentThread().getName());
            try {
                WriteToFile();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println(" 守护线程会不会丢失finally操作。。");
            }
            System.out.println("退出守护线程：" + Thread.currentThread().getName());
        }

        private void WriteToFile() throws Exception {
            while (1 < 99009) {
                Thread.sleep(100L);
                // 如果这个地方在写入文件---如果主线程推出，这个地方就推出，可能导致文件没有写入。
                System.out.println(" = ");
            }
        }
    }
}
