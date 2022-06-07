package com.jiamingku.jvm.weakreference;

import com.google.common.base.internal.Finalizer;
import org.junit.Test;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * 不包含队列
 */
@SuppressWarnings("all")
public class SoftReferenceDemo1 {

    /**
     * 内存不够直接回收
     * 1)切记就是占用空间的指针,在堆里面存放(不是gc-root)
     * 2)当内存不足时候进行回收
     */
    public static void main(String[] args) throws InterruptedException {
        //将缓存数据用软引用持有
        SoftReference<byte[]> cacheRef = new SoftReference<>(new byte[100 * 1024 * 1024]);
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
        System.out.println("分配后" + cacheRef.get() + " \t" + cacheRef);
        Thread.sleep(10000000L);
        Finalizer finalizer = null;
    }

    @Test
    public void a() {
        Map<String, String> maps = new HashMap<>();
        maps.put(null, "ssss");
        String s = maps.get(null);
        System.out.println(s);
    }
}