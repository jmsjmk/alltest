package com.middleserver.zk.original;

import com.middleserver.zk.ZkConstance;
import org.apache.zookeeper.*;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class DeleteApi implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zk;

    public static void main(String[] args) throws Exception {
        String path = "/zk-book";
        zk = new ZooKeeper(ZkConstance.CONNECTION_STR, 5000, new DeleteApi());
        connectedSemaphore.await();
        System.out.println("------------连接成功了-----------------");
        String pathResoult = zk.create(path, "test1111".getBytes(),
                Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("path = " + pathResoult);
        zk.delete(path + "dsdfsfsfsdfs", -1);
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("WatchedEvent = " + event.toString());
        if (KeeperState.SyncConnected == event.getState()) {
            if (EventType.None == event.getType() && null == event.getPath()) {
                connectedSemaphore.countDown();
            }
        }
    }

    /**
     * 同步删除:如果节点不存在的话跑出异常
     * 异步删除:直接返回错误代码
     */
    // -----------------------异步删除-----------------------------------------------------------
    @Test
    public void testAsync() {
        try {
            ZooKeeper zk1 = new ZooKeeper(ZkConstance.CONNECTION_STR, 5000, new DeleteApi());
            connectedSemaphore.await();
            zk1.delete("/test1-test1/t5", -1, new AsyncCallback.VoidCallback() {
                @Override
                public void processResult(int i, String s, Object o) {
                    System.out.println("i = " + i);
                    System.out.println("s = " + s);
                    System.out.println("o = " + o);
                }
            }, "TEST1--");
            Thread.sleep(1000000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}