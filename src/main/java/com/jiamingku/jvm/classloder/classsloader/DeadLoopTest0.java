package com.jiamingku.jvm.classloder.classsloader;

/**
 * 类加载器死锁：但是状态是runable。跟线程的稍微有点区别
 * <p>
 * 类是按需加载,并且并且线程安全的
 * --------------------------------
 * 昶: hotspot内部，c++进行的加锁，这种死锁用jstack jconsole检测不出来 」
 */
public class DeadLoopTest0 {

    static class dd {
        /**
         * 产生死锁
         */
        static {
            if (true) {
                System.out.println(Thread.currentThread() + " init DeadLoopClass");
                while (true) {
                    System.out.println(Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Runnable script = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + " start");
                dd d = new dd();
                System.out.println(Thread.currentThread() + " over!");
            }
        };

        Thread thread1 = new Thread(script);
        Thread thread2 = new Thread(script);
        thread1.start();
        thread2.start();
    }
}
