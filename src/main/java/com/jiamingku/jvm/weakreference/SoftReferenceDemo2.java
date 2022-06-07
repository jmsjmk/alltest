package com.jiamingku.jvm.weakreference;

import com.google.common.base.internal.Finalizer;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * 包含队列
 */
@SuppressWarnings("all")
public class SoftReferenceDemo2 {

    /**
     * 内存不够直接回收
     * 1)切记就是占用空间的指针,在堆里面存放(不是gc-root)
     * 2)当内存不足时候进行回收
     */
    public static void main(String[] args) throws InterruptedException {
        //将缓存数据用软引用持有
        ReferenceQueue<SoftReference<byte[]>> qu = new ReferenceQueue();
        new Thread(() -> {
            try {
                while (true) {
//                    System.out.println(".");
                    SoftReference<byte[]> s = (SoftReference) qu.poll();
                    if (s == null) {
//                        System.out.println(" soft is null");
                        Thread.sleep(100L);
                        continue;
                    }
                    Object o = s.get();
                    if (o == null) {
                        System.out.println("is null");
                        while (true) {

                            System.out.println("引用对象：是不是空" + (s == null));
                            Thread.sleep(1000L);
                        }
                    } else {
                        System.out.println("is not null");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "-----").start();

        Thread.sleep(3000L);
        SoftReference<byte[]> cacheRef = new SoftReference(new byte[100 * 1024 * 1024], qu);
        //将缓存数据的强引用去除
        System.out.println("第一次GC前" + cacheRef.get());
        //进行一次GC后查看对象的回收情况
        System.gc();
        //等待GC
        Thread.sleep(500);
        System.out.println("第一次GC后" + cacheRef.get());
        System.out.println(" ============================= ");
        //在分配一个120M的对象，看看缓存对象的回收情况
        byte[] newData = new byte[120 * 1024 * 1024];
        System.out.println("分配后" + cacheRef.get());
        Thread.sleep(10000000L);
        Finalizer finalizer = null;


        Thread.sleep(1000000L);
    }


}