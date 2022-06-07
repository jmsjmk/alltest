package com.middleserver.zk.curator;

import com.middleserver.zk.ZkConstance;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

public class CreateTest {

    static String path = "/test1-test1/sdf/sdf/dd1";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString(ZkConstance.CONNECTION_STR)
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();

    public static void main(String[] args) throws Exception {
        client.start();
        String result = client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path, "init".getBytes());
        String result1 = client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path, "init".getBytes());
        System.out.println("result = " + result);
        System.out.println("result1 = " + result1);
        Thread.sleep(Integer.MAX_VALUE);
    }
}
