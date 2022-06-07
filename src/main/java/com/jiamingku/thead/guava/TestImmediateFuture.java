package com.jiamingku.thead.guava;

import com.google.common.util.concurrent.*;
import com.sun.istack.internal.Nullable;

import java.util.List;
import java.util.concurrent.Executors;

@SuppressWarnings("all")
public class TestImmediateFuture {
    public static void main(String[] args) {
        // 1.
        // 创建一个由invoke线程执行的线程池
        ListeningExecutorService executorService = MoreExecutors.newDirectExecutorService();
        // 装饰自定义的线程池返回
        ListeningExecutorService executorService1 = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());

        // 2.
        ListenableFuture<?> listenableFuture = executorService.submit(new TestListenFutureTask.MyCallable(3, 10));

        // 3.
        listenableFuture.addListener(() -> {
            System.out.println("listen success");
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


        ListenableFuture allFutures = null;
        final ListenableFuture transform = Futures.transform(allFutures, new AsyncFunction<List<Integer>, Boolean>() {
            @Override
            public ListenableFuture apply(List<Integer> results) throws Exception {
                return Futures.immediateFuture(String.format("success future:%d", results.size()));
            }
        });
    }

}
