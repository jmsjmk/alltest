package com.jiamingku.thead.threadpool;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * Created by jiamingku on 2020/2/22.
 */
@SuppressWarnings("all")
public class ThreadPoolExecuterTest {
    public static void main(String[] args) {
    }

    @Test
    public void tst() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        Runnable r1 = () -> System.out.println("线程名称：" + Thread.currentThread().getName() + "，执行:3秒后执行");
        scheduledThreadPool.schedule(r1, 3, TimeUnit.SECONDS);
        Runnable r2 = () -> System.out.println("线程名称：" + Thread.currentThread().getName() + "，执行:延迟2秒后每3秒执行一次");
        scheduledThreadPool.scheduleAtFixedRate(r2, 2, 3, TimeUnit.SECONDS);
        Runnable r3 = () -> System.out.println("线程名称：" + Thread.currentThread().getName() + "，执行:普通任务");
        for (int i = 0; i < 5; i++) {
            scheduledThreadPool.execute(r3);
        }

        try {
            Thread.sleep(1000000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void cacheThreadPool() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 1; i <= 10; i++) {
            final int ii = i;
            try {
                Thread.sleep(ii * 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cachedThreadPool.execute(() -> System.out.println("线程名称：" + Thread.currentThread().getName() + "，执行" + ii));
        }
    }

    @Test
    public void fixThreadPoolTest() throws InterruptedException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        executor.execute(new MyTask());
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        executor.execute(new MyTask());
        TimeUnit.SECONDS.sleep(300);
        Runnable r = null;
        executor.submit(r);
    }

    @Test
    public void shutdownTest() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        executor.execute(new MyTask());
        executor.shutdown();
    }

    static class MyTask implements Runnable {
        @Override
        public void run() {
            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName() + " begin");
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName() + " end");
        }
    }
}
