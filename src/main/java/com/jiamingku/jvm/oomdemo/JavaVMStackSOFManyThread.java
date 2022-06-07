package com.jiamingku.jvm.oomdemo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @see JavaVMStackSOF
 * 如何确定-Xss的空间是线程独享还是线程共享的呢?
 * 其实这个参数很简单，启动多线程然后不断的递归
 * 1)如果每个线程的深度都一样就说明是独享
 * 2)否者就是共享---
 * <p>
 * 验证之后就是独享的----记住了其实这个都可以代码都可以废弃
 */
public class JavaVMStackSOFManyThread {
    private AtomicInteger stackLength = new AtomicInteger(1);
    static JavaVMStackSOFManyThread javaVMStackSOFManyThread = new JavaVMStackSOFManyThread();

    static class R1 implements Runnable {
        int i = 0;

        @Override
        public void run() {
            javaVMStackSOFManyThread.stackLeak(i);
        }
    }

    public void stackLeak(int i) {
        try {
            i++;
            Thread.sleep(5);
            stackLeak(i);
        } catch (Throwable t) {
            System.out.println("Thread-Name::" + Thread.currentThread().getName() + "    stack length:" + i);

//            throw t;
        }
    }


    public static void main(String[] args) throws Exception {
        R1 r1 = new R1();
        for (int i = 0; i < 20; i++) {
            new Thread(r1, "Thread-" + i).start();
        }
        Thread.sleep(100000L);
    }
}