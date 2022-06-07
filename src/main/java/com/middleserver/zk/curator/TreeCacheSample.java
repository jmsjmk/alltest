package com.middleserver.zk.curator;

import com.middleserver.zk.ZkConstance;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * curator监听器使用的话用这个TreeCache
 * <p>
 * 1.data=null,说明节点不存在
 * 2.
 */
public class TreeCacheSample {

    static String path = "/test1-test1/curator/t2";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString(ZkConstance.CONNECTION_STR)
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();

    public static void main(String[] args) throws Exception {
        client.start();
        TreeCache treeCache = new TreeCache(client, path);
        TreeCacheListener treeCacheListener = new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
                ChildData data = event.getData();
                System.out.println("data = " + data);
                if (data != null) {
                    switch (event.getType()) {
                        case NODE_ADDED:
                            System.out.println(" add ");
                            break;
                        case NODE_UPDATED:
                            System.out.println(" NODE_UPDATED ");
                            break;
                        case NODE_REMOVED:
                            System.out.println(" NODE_REMOVED ");
                            break;
                        default:
                            break;
                    }
                } else {
                    System.out.println(" ........................ ");
                }
            }
        };
        treeCache.getListenable().addListener(treeCacheListener);
        treeCache.start();
        Thread.sleep(Integer.MAX_VALUE);


//        TreeCacheListener treeCacheListener1 = (CuratorFramework client, TreeCacheEvent event) -> {
//            ChildData data = event.getData();
//            System.out.println("data = " + data);
//            if (data != null) {
//                switch (event.getType()) {
//                    case NODE_ADDED:
//                        System.out.println(" add ");
//                        break;
//                    case NODE_UPDATED:
//                        System.out.println(" NODE_UPDATED ");
//                        break;
//                    case NODE_REMOVED:
//                        System.out.println(" NODE_REMOVED ");
//                        break;
//                    default:
//                        break;
//                }
//            } else {
//                System.out.println(" ........................ ");
//            }
//
//        };
    }
}