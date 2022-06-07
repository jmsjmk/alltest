package com.middleserver.zk.curator;

import com.middleserver.zk.ZkConstance;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;

/**
 * https://www.cnblogs.com/wwjj4811/p/12957091.html
 */
@SuppressWarnings("ALL")
public class TranscanTest {
    public static void main(String[] args) {
        try {
            RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
            CuratorFramework client = CuratorFrameworkFactory.newClient(ZkConstance.CONNECTION_STR,
                    5000,
                    3000,
                    retryPolicy);
            client.start();
            String path = "/test1-test1";

            String result = new String(client.getData().forPath(path));
            System.out.println("result = " + result);

            /**
             * 一次性的提交多条记录
             */
            client.inTransaction()
                    .create().forPath("/w", "w".getBytes())
                    .and()
                    .delete().forPath("/zxc")
                    //提交事务
                    .and().commit();
            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
