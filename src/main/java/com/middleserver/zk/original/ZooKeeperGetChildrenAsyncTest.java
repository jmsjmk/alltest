package com.middleserver.zk.original;

import com.middleserver.zk.ZkConstance;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.CountDownLatch;

@SuppressWarnings("all")
public class ZooKeeperGetChildrenAsyncTest implements Watcher {
    private static String path = "/test1-test1";
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zk = null;

    public static void main(String[] args) throws Exception {
        zk = new ZooKeeper(ZkConstance.CONNECTION_STR, 5000, new ZooKeeperGetChildrenAsyncTest());
        connectedSemaphore.await();
        zk.getChildren(path, true, new IChildren2Callback(), null);
        Thread.sleep(Integer.MAX_VALUE);
    }

    /**
     * 事件处理器:
     *
     * @param event
     */
    public void process(WatchedEvent event) {
        if (KeeperState.SyncConnected == event.getState()) {
            if (EventType.None == event.getType() && null == event.getPath()) {
                connectedSemaphore.countDown();
            } else if (event.getType() == EventType.NodeChildrenChanged) {
                try {
                    zk.getChildren(event.getPath(), true, new IChildren2Callback(), null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class IChildren2Callback implements AsyncCallback.Children2Callback {
    public void processResult(int rc, String path, Object ctx, List<String> children, Stat stat) {
        System.out.println("Get Children znode result: [response code: " + rc + ", param path: " + path
                + ", ctx: " + ctx + ", children list: " + children + ", stat: " + stat);
    }
}