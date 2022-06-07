package com.jiamingku.jvm.weakreference;

import java.lang.ref.WeakReference;

/**
 * 弱引用关联对象何时被回收
 */
public class WeakReferenceDemo {
    public static void main(String[] args) throws InterruptedException {
        /**
         * 弱引用或者软引用(包含的引用被强引用指向的话) 永远不会回收
         *
         * 100M的缓存数据
         */
        byte[] cacheData = new byte[100 * 1024 * 1024];
        byte[] back1 = cacheData;
        // 将缓存数据用软引用持有
        WeakReference<byte[]> cacheRef = new WeakReference<>(back1);

        // FinalReference f = new FinalReference();
        System.out.println("第一次GC前----直接引用获取" + cacheData);
        System.out.println("第一次GC前---弱引用获取" + cacheRef.get());
        //进行一次GC后查看对象的回收情况
        System.gc();
        //等待GC
        Thread.sleep(500);

        System.out.println("第一次GC后----直接引用获取" + cacheData);
        System.out.println("第一次GC后---弱引用获取" + cacheRef.get());

        //将缓存数据的强引用去除
        cacheData = null;
        System.gc();
        //等待GC

        System.out.println("第二次GC后" + cacheData);
        System.out.println("第二次GC后" + cacheRef.get());
        Thread.sleep(50000000);
    }
}