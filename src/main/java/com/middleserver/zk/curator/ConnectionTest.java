package com.middleserver.zk.curator;

import com.middleserver.zk.ZkConstance;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;

public class ConnectionTest {

    @Test
    public void testConnection1() {
        try {
            RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
            CuratorFramework client = CuratorFrameworkFactory.newClient(ZkConstance.CONNECTION_STR,
                    5000,
                    3000,
                    retryPolicy);
            client.start();
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConnection2() {
        try {
            RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
            CuratorFramework client = CuratorFrameworkFactory.builder()
                    .connectString(ZkConstance.CONNECTION_STR)
                    .sessionTimeoutMs(5000)
                    .retryPolicy(retryPolicy)
                    .build();
            client.start();
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
