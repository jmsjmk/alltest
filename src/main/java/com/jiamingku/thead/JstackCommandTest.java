package com.jiamingku.thead;

import com.jiamingku.jvm.classloder.classsloader.DeadLoopTest0;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 1.synchronized: TIMED_WAITING (on object monitor) 一般多会出现 on object monitor
 * 2.TIMED_WAITING (parking) -一般都是LockSupports进行的支持
 *
 * {@link DeadLoopTest0}
 */
public class JstackCommandTest {

    /*
    "t4" #15 prio=5 os_prio=31 tid=0x00007fe72ea40800 nid=0x5707 waiting on condition [0x0000700001e98000]
        java.lang.Thread.State: TIMED_WAITING (sleeping)
	        at java.lang.Thread.sleep(Native Method)
	        at com.jiamingku.jvm.command.JstackCommandTest.lambda$main$3(JstackCommandTest.java:45)
	        at com.jiamingku.jvm.command.JstackCommandTest$$Lambda$4/123961122.run(Unknown Source)
	        at java.lang.Thread.run(Thread.java:748)

	 #15 虚拟机启动之后是第15个启动的线程
	 prio   : java内定义的线程的优先JstackCommandTest级
     os_prio: 操作系统级别的优先级
     tid: java内的线程id
     nid: 操作系统级别线程的线程id =====这个东西咋用呢？

     waiting on condition [0x0000700001e98000] :线程栈的起始地址.
     */
    public static void main(String[] args) throws Exception {
        System.out.println("ThreadStatus status test....");

        JstackCommandTest threadStatusTest = new JstackCommandTest();
        /**
         * 1.waiting on condition 可以理解成为等待一个条件。如下就是等待唤醒睡眠唤醒条件
         */
        Thread threadSleep = new Thread("ThreadSleep") {
            @Override
            public void run() {
                try {
                    Thread.sleep(100000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        threadSleep.start();

        // ---------------------BLOCK--------------------------------
        /*
         1）- waiting to lock <0x000000076aeba270> (a com.jiamingku.thead.base.ThreadStatusTest)
            -等待锁
         2）- locked <0x000000076aeba270> (a com.jiamingku.thead.base.ThreadStatusTest)
            -已经获取锁的线程

         */
        Thread threadSync1 = new Thread("threadSync1") {
            @Override
            public void run() {
                try {
                    threadStatusTest.syncMethod();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        threadSync1.start();
        Thread threadSync2 = new Thread("threadSync2") {
            @Override
            public void run() {
                try {
                    threadStatusTest.syncMethod();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        threadSync2.start();
        Thread threadSync3 = new Thread("threadSync3") {
            @Override
            public void run() {
                try {
                    threadStatusTest.syncMethod();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        threadSync3.start();
        // ---------------------BLOCK===END--------------------------------

        /*
         *
         *
         *
         *
         *
         *
         *
         */
        // ---------------------WAIT--------------------------------
        /*
         * 线程:threadWait:   in Object.wait() 说明了释放锁
         *
         */
        Thread threadWait1 = new Thread("threadWait") {
            @Override
            public void run() {
                try {
                    threadStatusTest.syncMethodAndWait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        threadWait1.start();

        Thread threadWait2 = new Thread("threadWait2") {
            @Override
            public void run() {
                try {
                    threadStatusTest.syncMethodAndWait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        threadWait2.start();
        // ---------------------WAIT--------------------------------

        Thread threadWaitTime1 = new Thread("threadWaitTime1") {
            @Override
            public void run() {
                try {
                    threadStatusTest.syncMethodAndWaitTime();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        threadWaitTime1.start();

        Thread threadWaitTime2 = new Thread("threadWaitTime2") {
            @Override
            public void run() {
                try {
                    threadStatusTest.syncMethodAndWaitTime();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        threadWaitTime2.start();

        // ---------------------------------------------------------
        Thread parkThread = new Thread("parkTest") {
            @Override
            public void run() {
                LockSupport.park();
            }
        };
        parkThread.start();

        Thread parkThreadTime = new Thread("parkTestTime127") {
            @Override
            public void run() {
                System.out.println(" parkTestTime127==========  begin");
                TimeUnit.NANOSECONDS.convert(100, TimeUnit.SECONDS);
                LockSupport.parkNanos(TimeUnit.NANOSECONDS.convert(100, TimeUnit.SECONDS));
                System.out.println(" parkTestTime127===||||=======  end...");

            }
        };
        Object o = new Object();
        parkThreadTime.start();
        Thread parkThreadTimeObject = new Thread("parkTestTime139") {
            @Override
            public void run() {
                System.out.println(" parkTestTime139==========  begin");
                LockSupport.park(o);
                System.out.println(" parkTestTime139===..........=======  end...");

            }
        };
        parkThreadTimeObject.start();


        Thread parkThreadTimeObject1 = new Thread("parkTestTime149") {
            @Override
            public void run() {
                System.out.println(" parkTestTime139==========  begin");
                LockSupport.park(o);
                System.out.println(" parkTestTime139===..........=======  end...");

            }
        };
        parkThreadTimeObject1.start();
        // main ----sleep
        Thread.sleep(10000000L);
        Thread.sleep(103330L);
    }

    public synchronized void syncMethod() throws InterruptedException {
        Thread.sleep(100000L);
    }

    public Object lock = new Object();

    public void syncMethodAndWait() throws InterruptedException {
        synchronized (lock) {
            System.out.println(" 准备wait方法");
            lock.wait();
        }
    }


    public Object lock1 = new Object();

    public void syncMethodAndWaitTime() throws InterruptedException {
        synchronized (lock1) {
            System.out.println(" 准备wait方法");
            lock1.wait(1000000L);
            System.out.println(" wait时间结束。。。。");
        }
    }
}
