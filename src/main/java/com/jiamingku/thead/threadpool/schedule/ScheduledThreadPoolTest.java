package com.jiamingku.thead.threadpool.schedule;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        // 创建大小为5的线程池
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 15; i++) {
            Task worker = new Task("task-" + i);
            // 只执行一次
//            scheduledThreadPool.schedule(worker, 5, TimeUnit.SECONDS);
            // 周期性执行，每5秒执行一次
            scheduledThreadPool.scheduleAtFixedRate(worker, 0, 1, TimeUnit.SECONDS);
//            scheduledThreadPool.schedule(worker, 5, TimeUnit.SECONDS);
        }
        Thread.sleep(10000000);
        System.out.println("Shutting down executor...");
        // 关闭线程池
        scheduledThreadPool.shutdown();
        boolean isDone;
        // 等待线程池终止
        do {
            isDone = scheduledThreadPool.awaitTermination(1, TimeUnit.DAYS);
            System.out.println("awaitTermination...");
        } while (!isDone);
        System.out.println("Finished all threads");
    }
}


class Task implements Runnable {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        Date d1 = new Date();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("name = " + name + "startTime = " + DateUtils.defaultMethod(d1) + " endTime = " + DateUtils.defaultMethod(new Date()));
    }
}