package com.middleserver.zk.original;

import java.util.concurrent.CountDownLatch;

import com.middleserver.zk.ZkConstance;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

@SuppressWarnings("all")
public class GetDataASync implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zk;

    public static void main(String[] args) throws Exception {

        String path = "/test1-test1/t3323323dddddddddd";
        zk = new ZooKeeper(ZkConstance.CONNECTION_STR, 5000, new GetDataASync());
        connectedSemaphore.await();
        zk.getData(path, true, new IDataCallback(), null);

        Thread.sleep(Integer.MAX_VALUE);
    }

    public void process(WatchedEvent event) {
        if (KeeperState.SyncConnected == event.getState()) {
            if (EventType.None == event.getType() && null == event.getPath()) {
                connectedSemaphore.countDown();
            } else if (event.getType() == EventType.NodeDataChanged) {
                try {
                    zk.getData(event.getPath(), true, new IDataCallback(), null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class IDataCallback implements AsyncCallback.DataCallback {
    public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
//        System.out.println(rc + ", " + path + ", " + new String(data));
//        System.out.println(stat.getCzxid() + "," +
//                stat.getMzxid() + "," +
//                stat.getVersion());
        System.out.println(" = ");
        System.out.println("rc = " + rc);
        System.out.println("path = " + path);
        System.out.println("ctx = " + ctx);
        System.out.println("new String(data) = " + new String(data));

    }
}

