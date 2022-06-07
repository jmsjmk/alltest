package com.middleserver.zk.original;

import java.util.concurrent.CountDownLatch;

import com.middleserver.zk.ZkConstance;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

/**
 * 同步方式创建节点4种方式
 * 1.持久
 * 2.持久顺序
 * 3.临时
 * 4.临时顺序
 */
public class ZooKeeperCreateSync implements Watcher {

    public static final String path = "/test1-test4";
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        ZooKeeper zookeeper = new ZooKeeper(ZkConstance.CONNECTION_STR, 5000, new ZooKeeperCreateSync());
        connectedSemaphore.await();
        String path1 = zookeeper.create(path + "/zk-test-ephemeral-",
                "".getBytes(),
                Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL);
        System.out.println("Success create znode: " + path1);

        String path2 = zookeeper.create(path + "/zk-test-ephemeral-",
                "".getBytes(),
                Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("Success create znode: " + path2);

        path1 = zookeeper.create(path + "/zk-test-persistent-",
                "".getBytes(),
                Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);
        System.out.println("Success create znode: " + path1);

        path2 = zookeeper.create(path + "/zk-test-persistent-",
                "".getBytes(),
                Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT_SEQUENTIAL);
        System.out.println("Success create znode: " + path2);
        Thread.sleep(Integer.MAX_VALUE);
    }

    /**
     * 这个在连接时候注册的事件--只处理连接的事情
     *
     * @param event
     */
    public void process(WatchedEvent event) {
        System.out.println("event = " + event);
        if (KeeperState.SyncConnected == event.getState()) {
            connectedSemaphore.countDown();
        }
    }
}
