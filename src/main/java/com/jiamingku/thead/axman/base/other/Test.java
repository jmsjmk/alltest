package com.jiamingku.thead.axman.base.other;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * sleep不会释放监视锁
 */
class SleepTest {
    public synchronized void wantSleep() {
        try {
            System.out.println("Thread isInterrupt:" + Thread.currentThread().isInterrupted());
//            Thread.sleep(1000 * 5);
            LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(5));

            System.out.println("Thread isInterrupt:" + Thread.currentThread().isInterrupted());
        } catch (Exception e) {
            System.out.println("EEEEEEEEEEEE:" + Thread.currentThread().isInterrupted());
            e.printStackTrace();
        }

        System.out.println("111");
    }

    public synchronized void say() {
        System.out.println("123");
    }
}

class T1 extends Thread {
    SleepTest st;

    public T1(SleepTest st) {
        this.st = st;
    }

    public void run() {
        System.out.println("begin");
        st.wantSleep();
        System.out.println("end");
    }
}

class T2 extends Thread {
    SleepTest st;

    public T2(SleepTest st) {
        this.st = st;
    }

    public void run() {
        st.say();
    }
}


public class Test {
    public static void main(String[] args) throws Exception {
        SleepTest st = new SleepTest();
        T1 t1 = new T1(st);
        t1.start();
        try {
            Thread.sleep(1000 * 3);
        } catch (Exception e) {
        }
        System.out.println("马上中断：：：：");
        t1.interrupt();
    }
}
