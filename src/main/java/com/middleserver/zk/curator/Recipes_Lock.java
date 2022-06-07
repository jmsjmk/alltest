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
 * 1.lock.acquire-线程-先挂临时节点。然后进入阻筛状态.
 * 2.使用分布式锁的时候,需要指定路径
 */
public class Recipes_Lock {
    static String lock_path = "/test1-tst1/curator_recipes_lock_pat";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString(ZkConstance.CONNECTION_STR)
            .retryPolicy(new ExponentialBackoffRetry(100, 3)).build();

    public static void main(String[] args) throws Exception {
        client.start();
        final InterProcessMutex lock = new InterProcessMutex(client, lock_path);
        final CountDownLatch down = new CountDownLatch(1);
        for (int i = 0; i < 30; i++) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        down.await();
                        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
                        lock.acquire();
                        Thread.sleep(1000L);

                    } catch (Exception e) {
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss|SSS");
                    String orderNo = sdf.format(new Date());
                    System.out.println("生成的订单号是 : " + orderNo);
                    try {
                        Thread.sleep(100000000L);
                        lock.release();
                    } catch (Exception e) {
                    }
                }
            }).start();
        }
        down.countDown();
        Thread.sleep(100000L);
    }
}