package com.jiamingku.thead.threadpool.schedule;

import java.util.Date;
import java.util.concurrent.*;

/**
 * fuck 不能进行定时的调度...+callable接口.
 */
public class ScheduledThreadPoolTest123 {
    public static void main(String[] args) throws Exception {
        // 创建大小为5的线程池
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 1; i++) {
            Task worker = new Task("task-" + i);
            // 只执行一次
            // ScheduledFuture future = scheduledThreadPool.scheduleAtFixedRate(new CallT(), 5, TimeUnit.SECONDS);

//            System.out.println("future = " + future.get().toString());

        }

    }
}


class CallT implements Callable<Object> {
    @Override
    public Object call() throws Exception {
        System.out.println("开始调用 返回");
        return 100;
    }
}

class Task1 implements Runnable {
    private String name;

    public Task1(String name) {
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