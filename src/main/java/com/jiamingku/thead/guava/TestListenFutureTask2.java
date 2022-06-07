package com.jiamingku.thead.guava;

import com.google.common.util.concurrent.*;
import com.sun.istack.internal.Nullable;

import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings("ALL")
public class TestListenFutureTask2 {

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

        ListenableFuture<Integer> future1 = executorService.submit(() -> 1 + 2);
        ListenableFuture<Integer> future2 = executorService.submit(() -> Integer.parseInt("3q"));
        // ListenableFuture<List<Object>> futures = Futures.allAsList(future1, future2);

        // 把结果包装成为一个 新的future
        ListenableFuture<List<Object>> futures = Futures.successfulAsList(future1, future2);

        // futures.get();
        Futures.addCallback(futures, new FutureCallback<List<Object>>() {
            @Override
            public void onSuccess(@Nullable List<Object> result) {
                System.out.println(result);
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
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
