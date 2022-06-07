package com.middleserver.zk.curator;

import com.middleserver.zk.ZkConstance;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * 1.对于基本的使用--就是简单的操作就行.
 * 2.使用Curator实现分布式锁功能
 */
//
public class RecipesLock1 {

    static String lock_path = "/test1-tst1/curator_recipes_lock_pat";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString(ZkConstance.CONNECTION_STR)
            .retryPolicy(new ExponentialBackoffRetry(100, 3)).build();

    public static void main(String[] args) throws Exception {
        client.start();

        String s = client.getState().toString();
        System.out.println("s = " + s);
        byte[] bb = client.getData().forPath("/");
        System.out.println("new String(bb) = " + new String(bb));
        final InterProcessMutex lock = new InterProcessMutex(client, lock_path);
        final CountDownLatch down = new CountDownLatch(1);
        for (int i = 0; i < 130; i++) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        down.await();
                        lock.acquire();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss|SSS");
                    String orderNo = sdf.format(new Date());
                    System.out.println("生成的订单号是 : " + orderNo);
                    try {
                        Thread.sleep(1500L);
                        lock.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        down.countDown();
    }
}