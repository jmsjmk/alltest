package com.jiamingku.collection.onehash;

import org.junit.Test;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 不带虚拟节点的一致性Hash算法
 *
 * @author 五月的仓颉http://www.cnblogs.com/xrq730/
 */
public class ConsistentHashingWithoutVirtualNode {
    /**
     * 待添加入Hash环的服务器列表
     */
    private static String[] servers = {
            "192.168.0.0:111",
            "192.168.0.1:111",
            "192.168.0.2:111",
            "192.168.0.3:111",
            "192.168.0.4:111"
    };

    /**
     * key表示服务器的hash值，value表示服务器的名称
     * <p>
     * 为了寻找对应最近的节点
     */
    private static SortedMap<Integer, String> sortedMap = new TreeMap<Integer, String>();

    /**
     * 程序初始化，将所有的服务器放入sortedMap中
     */
    static {
        for (int i = 0; i < servers.length; i++) {
            int hash = StringHashCodeTest.getHash(servers[i]);
            System.out.println("[" + servers[i] + "]加入集合中, 其Hash值为" + hash);
            sortedMap.put(hash, servers[i]);
        }
        System.out.println();
    }


    /**
     * 得到应当路由到的结点
     */
    private static String getServer(String node) {
        // 得到带路由的结点的Hash值
        int hash = StringHashCodeTest.getHash(node);
        // 得到大于该Hash值的所有Map
        SortedMap<Integer, String> subMap = sortedMap.tailMap(hash);
        // 第一个Key就是顺时针过去离node最近的那个结点
        Integer i = subMap.firstKey();
        // 返回对应的服务器名称
        return subMap.get(i);
    }

    public static void main(String[] args) {
        String[] nodes = {"127.0.0.1:1111", "221.226.0.1:2222", "10.211.0.1:3333"};
        for (int i = 0; i < nodes.length; i++)
            System.out.println("[" + nodes[i] + "]的hash值为" +
                    StringHashCodeTest.getHash(nodes[i]) + ", 被路由到结点[" + getServer(nodes[i]) + "]");
    }

    @Test
    public void testTreeMap() {
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(1, "1");
        treeMap.put(2, "2");
        treeMap.put(3, "3");
        treeMap.put(4, "4");
        treeMap.put(5, "5");
        treeMap.put(6, "6");
        SortedMap<Integer, String> map = treeMap.tailMap(1000);
        map.keySet().forEach(System.out::println);
        Integer kkk = map.firstKey();
        System.out.println("kkk = " + kkk);
    }
}