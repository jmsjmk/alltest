package com.middleserver.zk.original;

import com.middleserver.zk.ZkConstance;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

public class ExistApi implements Watcher {
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zk;

    public static void main(String[] args) throws Exception {
        String path = "/test1-test3";
        zk = new ZooKeeper(ZkConstance.CONNECTION_STR,
                5000,
                new ExistApi());
        connectedSemaphore.await();
        // 同步方式
        Stat stat = zk.exists(path, true);

        // 异步方式
        zk.exists(path, true, new AsyncCallback.StatCallback() {
            @Override
            public void processResult(int i, String s, Object o, Stat stat) {
                System.out.println("i = " + i);
                System.out.println("s = " + s);
                System.out.println("o = " + o);
                System.out.println("stat = " + stat);
            }
        }, "CTX");
        System.out.println("stat = " + stat);
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("一个watch可以重复使用....");
        try {
            if (KeeperState.SyncConnected == event.getState()) {
                if (EventType.None == event.getType() && null == event.getPath()) {
                    connectedSemaphore.countDown();
                } else if (EventType.NodeCreated == event.getType()) {
                    System.out.println("Node(" + event.getPath() + ")Created");
                    /**
                     * 反复注册
                     */
                    zk.exists(event.getPath(), true);
                } else if (EventType.NodeDeleted == event.getType()) {
                    System.out.println("Node(" + event.getPath() + ")Deleted");
                    zk.exists(event.getPath(), true);
                } else if (EventType.NodeDataChanged == event.getType()) {
                    System.out.println("Node(" + event.getPath() + ")DataChanged");
                    zk.exists(event.getPath(), true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}