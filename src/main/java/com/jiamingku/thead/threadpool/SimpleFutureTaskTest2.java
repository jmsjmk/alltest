package com.jiamingku.thead.threadpool;


import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

/**
 * 1.创建 FutureTask(相当于票据)
 * 2.传递给线程
 * 3.get获取结果
 *
 * @link com.jiamingku.thead.async.v1.Requester
 */
public class SimpleFutureTaskTest2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask1 = new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println("begin call---1");
                boolean b = true;
//                Thread.sleep(30000000L);
                LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(4000));
                System.out.println("begin call");
                String a = "";
                return "333";
            }
        });
        FutureTask futureTask4 = new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println("begin call---4");
                boolean b = true;
                LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(4000));
                System.out.println("begin call");
                return "333";
            }
        });
        FutureTask futureTask2 = new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println("begin call---2");
                boolean b = true;
                LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(4000));
                System.out.println("begin call");
                return "333";
            }
        });
        FutureTask futureTask3 = new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println("begin call---3");
                boolean b = true;
                LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(4000));
                System.out.println("begin call");
                return "333";
            }
        });
        ExecutorService pool = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor p2 = (ThreadPoolExecutor) pool;
        p2.execute(futureTask1);
        p2.execute(futureTask2);
        p2.execute(futureTask3);
        p2.execute(futureTask4);

        if (pool instanceof ThreadPoolExecutor) {
            ThreadPoolExecutor p1 = (ThreadPoolExecutor) pool;
            System.out.println("活跃的线程数量：：：：:" + p1.getActiveCount());
        }
        Thread.sleep(5 * 1000L);

        if (pool instanceof ThreadPoolExecutor) {
            ThreadPoolExecutor p1 = (ThreadPoolExecutor) pool;
            System.out.println("活跃的线程数量：：：：:" + p1.getActiveCount());
        }

        futureTask1.cancel(true);
        futureTask2.cancel(true);
        futureTask3.cancel(true);
        futureTask4.cancel(true);

        try {
            Object o1 = futureTask1.get();
            System.out.println("o1 = " + o1);
        } catch (Exception E) {
            E.printStackTrace();
        }
        try {
            Object o2 = futureTask2.get();
        } catch (Exception E) {
            E.printStackTrace();
        }

        try {
            Object o3 = futureTask3.get();
        } catch (Exception E) {
            E.printStackTrace();
        }

        try {
            Object o4 = futureTask4.get();
        } catch (Exception E) {
            E.printStackTrace();
        }

        try {
            Thread.sleep(100000000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
