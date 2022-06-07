package com.middleserver.zk.curator;

import com.middleserver.zk.ZkConstance;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;

public class DelTest {
    static String path = "/test1-test1/sdf";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString(ZkConstance.CONNECTION_STR)
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();

    public static void main(String[] args) throws Exception {
        client.start();
        Stat stat = new Stat();
        byte[] buff = client.getData().storingStatIn(stat).forPath(path);
        String result = new String(buff);
        System.out.println("result = " + result);
        client.delete().deletingChildrenIfNeeded().withVersion(stat.getVersion()).forPath(path);
    }
}
