package com.middleserver.zk.original;

import java.util.concurrent.CountDownLatch;

import com.middleserver.zk.ZkConstance;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;

public class ZkConnectTestWithSidPasswd implements Watcher {
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        /**
         *  sessionId、SessionPassword -- 会话编号 会话密码，用来实现会话恢复。
         */
        ZooKeeper zookeeper = new ZooKeeper(ZkConstance.CONNECTION_STR, 5000, new ZkConnectTestWithSidPasswd(), 1l, "test".getBytes());
        ZkConstance.getZkConnectionStatus(zookeeper);
        Thread.sleep(Integer.MAX_VALUE);
    }

    public void process(WatchedEvent event) {
        System.out.println("Receive watched event：" + event);
        if (KeeperState.SyncConnected == event.getState()) {
            connectedSemaphore.countDown();
            System.out.println("---成功----");
        } else {
            System.out.println("---失败----");
        }
    }
}