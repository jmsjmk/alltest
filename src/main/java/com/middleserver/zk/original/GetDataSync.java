package com.middleserver.zk.original;

import com.middleserver.zk.ZkConstance;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

@SuppressWarnings("all")
public class GetDataSync implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zk = null;
    private static Stat stat = new Stat();

    public static void main(String[] args) throws Exception {
        String path = "/test1-test1/t3323323dddddddddddddd";
        System.out.println("path = " + path);
        System.out.println("stat = " + stat);
        zk = new ZooKeeper(ZkConstance.CONNECTION_STR, 5000, new GetDataSync());
        connectedSemaphore.await();
        System.out.println(new String(zk.getData(path, true, stat)));
        System.out.println("==" + stat.getCzxid() + "," + stat.getMzxid() + "," + stat.getVersion());
        Thread.sleep(Integer.MAX_VALUE);
    }

    public void process(WatchedEvent event) {
        if (KeeperState.SyncConnected == event.getState()) {
            if (EventType.None == event.getType() && null == event.getPath()) {
                connectedSemaphore.countDown();
            } else if (event.getType() == EventType.NodeDataChanged) {
                try {
                    /** 反复注册 begin*/
                    System.out.println(new String(zk.getData(event.getPath(), true, stat)));
                    System.out.println(stat.getCzxid() + "," + stat.getMzxid() + "," + stat.getVersion());
                    /** 反复注册 end*/
                    System.out.println(" ====");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}