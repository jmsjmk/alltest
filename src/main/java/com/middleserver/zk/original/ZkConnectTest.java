package com.middleserver.zk.original;

import java.util.concurrent.CountDownLatch;

import com.middleserver.zk.ZkConstance;
import org.apache.zookeeper.*;
import org.apache.zookeeper.Watcher.Event.KeeperState;

import static com.middleserver.zk.ZkConstance.*;

/**
 * ZK版本: 客户端版本与服务端版本要匹配才可以.
 */
public class ZkConnectTest implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        /**
         * 1.原生态的连接是异步的,连接时候阻塞,接收到通知之后进行处理
         * 2.并且状态是CONNECTING状态
         */
        ZooKeeper zookeeper = new ZooKeeper(CONNECTION_STR, 5000, new ZkConnectTest());
        /**
         * 连接完zookeeper马上就变成了CONNECTING状态
         */
        System.out.println(zookeeper.getState());
        try {
            connectedSemaphore.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ZkConstance.getZkConnectionStatus(zookeeper);
        System.out.println("ZooKeeper session established.");
        String s = zookeeper.create("/zk-test-ephemeral-12", "11".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("s = " + s);
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("Receive watched event：" + event);
        if (KeeperState.SyncConnected == event.getState()) {
            connectedSemaphore.countDown();
        }
    }
}