package com.middleserver.zk.original;

import com.middleserver.zk.ZkConstance;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

public class UpdateDataSync implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zk;
    public static final String path = "/test1-test5";

    public static void main(String[] args) throws Exception {
        zk = new ZooKeeper(ZkConstance.CONNECTION_STR, 5000, new UpdateDataSync());
        connectedSemaphore.await();
        Stat stat = zk.setData(path, "456".getBytes(), -1);
        System.out.println("stat = " + stat);
        try {
            zk.setData(path, "458886".getBytes(), 1111);
        } catch (KeeperException e) {
            // 版本号发生异常--报错
            System.out.println("Error: " + e.code() + "," + e.getMessage());
        }
        Thread.sleep(Integer.MAX_VALUE);
    }

    Stat stat = new Stat();

    @Override
    public void process(WatchedEvent event) {
        System.out.println("event = " + event);
        if (KeeperState.SyncConnected == event.getState()) {
            if (EventType.None == event.getType() && null == event.getPath()) {
                connectedSemaphore.countDown();
            } else if (event.getType() == EventType.NodeDataChanged) {
                try {
                    System.out.println(new String(zk.getData(event.getPath(), true, stat)));
                    System.out.println(stat.getCzxid() + "," + stat.getMzxid() + "," + stat.getVersion());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}