package com.middleserver.zk.original;

import com.middleserver.zk.ZkConstance;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;

import java.util.List;
import java.util.concurrent.CountDownLatch;

@SuppressWarnings("all")
public class ZooKeeperGetChildrenSyncTest implements Watcher {
    private static final String path = "/test1-test1";
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zk = null;

    public static void main(String[] args) throws Exception {
        zk = new ZooKeeper(ZkConstance.CONNECTION_STR, 5000, new ZooKeeperGetChildrenSyncTest());
        connectedSemaphore.await();
        List<String> childrenList = zk.getChildren(path, true);

        /**
         * 1.可以使用 zk连接时候指定的watch
         * 2.可以自己创建一个watch
         *

         List<String> childrenList = zk.getChildren(path, new Watcher() {
        @Override public void process(WatchedEvent event) {
        System.out.println("event ===== " + event);
        try {
        zk.getChildren(path,this);
        } catch (KeeperException e) {
        e.printStackTrace();
        } catch (InterruptedException e) {
        e.printStackTrace();
        }
        }
        });
         */
        System.out.println(childrenList);
        Thread.sleep(Integer.MAX_VALUE);
    }

    public void process(WatchedEvent event) {
        if (KeeperState.SyncConnected == event.getState()) {
            if (EventType.None == event.getType() && null == event.getPath()) {
                connectedSemaphore.countDown();
            } else if (event.getType() == EventType.NodeChildrenChanged) {
                try {
                    /**
                     * 不断的注册事件.----并且使用的是默认事件-----
                     */
                    System.out.println("ReGet Child:" + zk.getChildren(event.getPath(), true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
