package com.jiamingku.thead.guava;

import com.google.common.util.concurrent.*;
import com.sun.istack.internal.Nullable;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

@SuppressWarnings("ALL")
public class TestListenFutureTask {

    /**
     * 1.创建线程池
     * <p>
     * 2.提交任务
     * <p>
     * 3.任务添加监听
     * <p>
     * 4.执行任务
     *
     * @param args
     */
    public static void main(String[] args) {
        // 1.
        // 创建一个由invoke线程执行的线程池
        ListeningExecutorService executorService = MoreExecutors.newDirectExecutorService();
        // 装饰自定义的线程池返回
        // ListeningExecutorService executorService1 = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());

        // 2.
        ListenableFuture<?> listenableFuture = executorService.submit(new MyCallable(3, 10));

        // 3.
        listenableFuture.addListener(() -> {
            System.out.println("listen success");
            doSomeThing();
        }, executorService);

        // 4.
        // FutureCallback接口包含onSuccess()、onFailure()两个方法
        Futures.addCallback(listenableFuture, new FutureCallback<Object>() {
            @Override
            public void onSuccess(@Nullable Object result) {
                System.out.println("res: " + result + " ThreadId=" + Thread.currentThread().getId());
            }

            @Override
            public void onFailure(Throwable t) {
            }
        }, executorService);
    }

    public static void doSomeThing() {
        System.out.println("doSomeThing method ..... ThreadId=" + Thread.currentThread().getId());
    }

    public static class MyCallable implements Callable {

        private int a;
        private int b;

        public MyCallable(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public Object call() throws Exception {

//            Thread.sleep(1000 * 10);
            System.out.println("执行完成... ThreadId=" + Thread.currentThread().getId());
            return a + b;
        }
    }
}
