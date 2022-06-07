package com.middleserver.zk.curator;

import com.middleserver.zk.ZkConstance;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import static org.apache.curator.framework.state.ConnectionState.LOST;
import static org.apache.curator.framework.state.ConnectionState.SUSPENDED;

/**
 * 连接事件:CONNECTION_RECONNECTED-->INITIALIZED
 * 断开事件:CONNECTION_SUSPENDED  -->CONNECTION_LOST
 * <p>
 * 立即断开--节点并不会马上消失
 * lost应该说明节点就彻底没了.
 */
public class ConnectionStateListenerTest {

    static String pathA = "/test3-test3/_SQ";


    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString(ZkConstance.CONNECTION_STR)
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();

    public static void main(String[] args) throws Exception {
        client.start();
        System.out.println(" = ");

        client.getConnectionStateListenable().addListener((client, newState) -> {
            System.out.println("new Stat:" + newState);
            try {
                Thread.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (newState == LOST || newState == SUSPENDED) {

            }
        });
        Thread.sleep(Integer.MAX_VALUE);
    }
}
