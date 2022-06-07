package com.middleserver.zk.curator;

import com.google.common.base.Strings;
import com.middleserver.zk.ZkConstance;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.List;

/**
 * curator监听器(TreeCache)其nodeCache, Child那个可以忘记了
 * <p>
 * 1.event.getData()=null,说明节点不存在
 * 2.并且读节点的时候会缓存数据--不用等到事件触发在获取
 * 3.就是第一次连接上节点注册监听的时候，就会读取数据.****,使用cache时候必须start
 */
public class TreeCacheSampleListenerPath {

    static String pathA = "/test3-test3/_SQ/sfsdfsdfsdfs";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString(ZkConstance.CONNECTION_STR)
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();

    public static void main(String[] args) throws Exception {
        client.start();
        TreeCache treeCache = new TreeCache(client, pathA);
        TreeCacheListener treeCacheListener = new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
                System.out.println("event = " + event);
                ChildData childData = event.getData();
                if (null == childData) {
                    System.out.println(" childData is null ");
                    return;
                }
                String path = childData.getPath();
                System.out.println("path = " + path);
                if (path.isEmpty()) {
                    System.out.println(" path is empty ");
                    return;
                }
                Object o = null;

                if (Strings.isNullOrEmpty(path)) {
                    return;
                }
                TreeCacheEvent.Type eventType = event.getType();
                System.out.println("binlog server path:{} change" + eventType);
                if (true && (eventType == TreeCacheEvent.Type.NODE_ADDED
                        || eventType == TreeCacheEvent.Type.NODE_REMOVED
                        || eventType == TreeCacheEvent.Type.NODE_UPDATED)) {
                    List<String> instanceIds = client.getChildren().forPath(path);
                    System.out.println("instanceIds = " + instanceIds);
                    System.out.println();
                    System.out.println();
                }
            }
        };
        treeCache.getListenable().addListener(treeCacheListener);
        treeCache.start();
        Thread.sleep(Integer.MAX_VALUE);
    }
}