package com.middleserver.zk.curator;

import com.middleserver.zk.ZkConstance;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;

@SuppressWarnings("ALL")
public class ReadTest {
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


            client.getData().inBackground(new BackgroundCallback() {
                                              @Override
                                              public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
//                                         System.out.println("curatorFramework = " + curatorFramework);
//                                         System.out.println("curatorEvent = " + curatorEvent);
                                                  byte[] data = curatorEvent.getData();
                                                  System.out.println(new String(data));
                                              }
                                          }
            ).forPath(path);
            Stat stat = new Stat();
            byte[] bytes = client.getData().storingStatIn(stat).forPath(path);
            System.out.println(new String(bytes));
            System.out.println("stat = " + stat);
            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
