package com.jiamingku.collection.onehash;

import java.util.TreeMap;

public class StringHashCodeTest {
    public static void main(String[] args) {
        System.out.println("192.168.0.0:111的哈希值：" + "192.168.0.0:1111".hashCode());
        System.out.println("192.168.0.1:111的哈希值：" + "192.168.0.1:1111".hashCode());
        System.out.println("192.168.0.2:111的哈希值：" + "192.168.0.2:1111".hashCode());
        System.out.println("192.168.0.3:111的哈希值：" + "192.168.0.3:1111".hashCode());
        System.out.println("192.168.0.4:111的哈希值：" + "192.168.0.4:1111".hashCode());
        System.out.println("192.168.1.0:111的哈希值：" + "192.168.1.0:1111".hashCode());
        TreeMap<String, String> tree = new TreeMap<>();
    }

    /**
     * 使用FNV1_32_HASH算法计算服务器的Hash值,这里不使用重写hashCode的方法，最终效果没区别
     */
    public static int getHash(String str) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < str.length(); i++)
            hash = (hash ^ str.charAt(i)) * p;
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        // 如果算出来的值为负数则取其绝对值
        if (hash < 0)
            hash = Math.abs(hash);
        return hash;
    }
}