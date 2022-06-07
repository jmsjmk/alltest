package com.jiamingku.thead.threadpool;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class BaseTestRejectedExecutionHandler {
    public static void main(String[] args) {

        // 1. 放弃策略
        try {
            ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 20, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));
            for (int i = 0; i < 16; i++) {
                MyTask myTask = new MyTask(i);
                executor.execute(myTask);
                System.out.println("线程池中线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目：" +
                        executor.getQueue().size() + "，已执行玩别的任务数目：" + executor.getCompletedTaskCount());
            }
            executor.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(" ======================= ");
        System.out.println(" ======================= ");
        // 1. CallerRunsPolicy （线程运行状态-调用者执行)
        try {
            ArrayBlockingQueue a = new ArrayBlockingQueue<Runnable>(5);
            ThreadPoolExecutor executor1 = new ThreadPoolExecutor(5, 10, 20,
                    TimeUnit.MILLISECONDS, a, new ThreadPoolExecutor.DiscardOldestPolicy());
            for (int i = 0; i < 16; i++) {
                MyTask myTask = new MyTask(i);
                if (i == 15) {
                    String s = Arrays.stream(a.stream().toArray()).map(Objects::toString).collect(Collectors.joining(","));
                    System.out.println("s = " + s);
                    executor1.execute(myTask);
                    s = Arrays.stream(a.stream().toArray()).map(Objects::toString).collect(Collectors.joining(","));
                    System.out.println("s = " + s);
                } else {
                    executor1.execute(myTask);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 1. CallerRunsPolicy （线程运行状态-调用者执行)
        try {
            ThreadPoolExecutor executor1 = new ThreadPoolExecutor(5, 10, 20,
                    TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5), new ThreadPoolExecutor.DiscardOldestPolicy()
            );
            for (int i = 0; i < 16; i++) {
                MyTask myTask = new MyTask(i);
                if (i == 15) {

                    executor1.execute(myTask);
                } else {
                    executor1.execute(myTask);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 1. CallerRunsPolicy （线程运行状态-调用者执行)
        try {
            ThreadPoolExecutor executor1 = new ThreadPoolExecutor(5, 10, 20,
                    TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5), new ThreadPoolExecutor.CallerRunsPolicy()
            );
            for (int i = 0; i < 16; i++) {
                MyTask myTask = new MyTask(i);
                if (i == 10) {
                    executor1.execute(myTask);
                } else {
                    executor1.execute(myTask);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class MyTask implements Runnable {
        private int taskNum;

        public MyTask(int num) {
            this.taskNum = num;
        }

        @Override
        public void run() {
            System.out.println("正在执行task " + taskNum);
            try {
                Thread.currentThread().sleep(400000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task " + taskNum + "执行完毕");
        }

        @Override
        public String toString() {
            return "MyTask[" + taskNum + ']';
        }
    }
}

