package com.jiamingku.thead;

import javax.management.timer.TimerNotification;
import java.util.concurrent.*;

/**
 * jvm 关闭的时候-触发回掉线程.
 */
public class JVMHOOK {
    public static void main(String[] args) {
        System.out.println(" jvm start ");
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(2)) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                // check dbsource

                System.out.println("前置钩子被执行");

                super.beforeExecute(t, r);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("后置钩子被执行");
                super.afterExecute(r, t);
            }

            @Override
            protected void terminated() {
                System.out.println("terminated.....");
            }
        };


        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                System.out.println("hook. begin");
                System.out.println(threadPoolExecutor.getActiveCount());
                Thread.sleep(10 * 1000L);

                threadPoolExecutor.execute(new Thread() {
                    @Override
                    public void run() {
                        System.out.println("ddddd");
                    }
                });
                Thread.sleep(10 * 1000L);
                System.out.println("hook. end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));

        /**
         *     public ThreadPoolExecutor(int corePoolSize,
         *                               int maximumPoolSize,
         *                               long keepAliveTime,
         *                               TimeUnit unit,
         *                               BlockingQueue<Runnable> workQueue,
         *                               ThreadFactory threadFactory) {
         *         this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
         *              threadFactory, defaultHandler);
         */

        threadPoolExecutor.execute(new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("--------49:" + threadPoolExecutor.getActiveCount());
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        System.out.println("59:" + threadPoolExecutor.getActiveCount());
//        try {
//            Thread.sleep(300* 1000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        System.out.println(" jvm end ");
    }
}
