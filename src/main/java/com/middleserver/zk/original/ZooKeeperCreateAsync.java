package com.middleserver.zk.original;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

import static com.middleserver.zk.ZkConstance.*;

/**
 * 异步创建父亲节点不存在的情况下, 不会发生异常
 */
public class ZooKeeperCreateAsync implements Watcher {
    public static final String path = "/test1-test5";
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {

        ZooKeeper zookeeper = new ZooKeeper(CONNECTION_STR, 5000, new ZooKeeperCreateAsync());
        connectedSemaphore.await();

        zookeeper.create(path + "/zk-test-ephemeral-", "".getBytes(),
                Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL,
                new IStringCallback(), "I am context.");

        zookeeper.create(path + "/zk-test-ephemeral-1-", "".getBytes(),
                Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL,
                new IStringCallback(), "I am context.");

        zookeeper.create(path + "/zk-test-ephemeral-", "".getBytes(),
                Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL,
                new IStringCallback(), "I am context.");

        zookeeper.create(path + "/zk-test-ephemeral-1/2/2/", "".getBytes(),
                Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL,
                new IStringCallback(), "I am context.");
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent event) {
        if (KeeperState.SyncConnected == event.getState()) {
            connectedSemaphore.countDown();
        }
    }
}

class IStringCallback implements AsyncCallback.StringCallback {
    public void processResult(int rc, String path, Object ctx, String name) {
        /**
         * 临时节点如果存在的 rt 返回的<0的数字
         */
        System.out.println(" 异步.............回掉 ");
        System.out.println("Create path result: [" + rc + ", " + path + ", " + ctx + ", real path name: " + name);
    }
}