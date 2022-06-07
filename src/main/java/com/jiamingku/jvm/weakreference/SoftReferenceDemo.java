package com.jiamingku.jvm.weakreference;

import java.lang.ref.SoftReference;

/**
 * 软引用何时被收集
 * 运行参数 -Xmx200m -XX:+PrintGC
 * <p>
 * -XX:+PrintGCDetails ---显示详细的
 */
@SuppressWarnings("all")
public class SoftReferenceDemo {

    /**
     * 软引用包含一个硬引用,永远不会回收,永远不回收,哪怕内存爆炸也不会回收
     */
    public static void main(String[] args) throws InterruptedException {
        //100M的缓存数据
        byte[] cacheData = new byte[100 * 1024 * 1024];
        //将缓存数据用软引用持有
        SoftReference<byte[]> cacheRef = new SoftReference<>(cacheData);
        //将缓存数据的强引用去除
        System.out.println("第一次GC前强引用" + cacheData);
        System.out.println("第一次GC前" + cacheRef.get());
        //进行一次GC后查看对象的回收情况
        System.gc();
        //等待GC
        Thread.sleep(500);
        System.out.println("第一次GC后" + cacheData);
        System.out.println("第一次GC后" + cacheRef.get());
        System.out.println(" ============================= ");
        //在分配一个120M的对象，看看缓存对象的回收情况
        byte[] newData = new byte[120 * 1024 * 1024];
        System.out.println("分配后" + cacheData);
        System.out.println("分配后" + cacheRef.get());
        Thread.sleep(10000000L);
    }
}