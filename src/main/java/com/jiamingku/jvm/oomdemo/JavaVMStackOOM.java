package com.jiamingku.jvm.oomdemo;

/**
 * 创建一个线程时候需要开辟一个线程栈空间,不断的开辟线程导致内存溢出,
 * 但是不确定是不是真由于栈空间内存不足了,还是操作系统操作了线程数
 */
public class JavaVMStackOOM {
    private void dontStop() throws InterruptedException {
        while (true) {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(100000);
        }
    }

    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    try {
                        dontStop();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}