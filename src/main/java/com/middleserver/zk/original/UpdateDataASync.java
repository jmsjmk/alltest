package com.middleserver.zk.original;

import java.util.concurrent.CountDownLatch;

import com.middleserver.zk.ZkConstance;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class UpdateDataASync implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zk;

    public static void main(String[] args) throws Exception {
        String path = "/test1-test6";
        zk = new ZooKeeper(ZkConstance.CONNECTION_STR, 5000, new UpdateDataASync());
        connectedSemaphore.await();
        zk.create(path, "123".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        zk.setData(path, "456".getBytes(), -1, new IStatCallback(), null);
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent event) {
        if (KeeperState.SyncConnected == event.getState()) {
            if (EventType.None == event.getType() && null == event.getPath()) {
                connectedSemaphore.countDown();
            }
        }
    }
}

class IStatCallback implements AsyncCallback.StatCallback {
    public void processResult(int rc, String path, Object ctx, Stat stat) {
        if (rc == 0) {
            System.out.println("SUCCESS");
        }
    }
}