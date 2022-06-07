package com.jiamingku.thead.threadpool;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 1.创建 FutureTask(相当于票据)
 * 2.传递给线程
 * 3.get获取结果
 *
 * @link com.jiamingku.thead.async.v1.Requester
 */
public class SimpleFutureTaskTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask1 = new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                boolean b = true;
                int a = 100;
                int b1 = 0;
                int c = a / b1;
                Thread.sleep(1000000L);
                return "333";
            }
        });
        Thread t = new Thread(futureTask1);
        t.start();
        Thread.sleep(100L);
        Object o = futureTask1.get();
        System.out.println("o = " + o);
        futureTask1.cancel(true);
        System.out.println(" ======走 ");
    }
}
