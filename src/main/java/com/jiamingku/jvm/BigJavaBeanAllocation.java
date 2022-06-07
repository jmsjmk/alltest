package com.jiamingku.jvm;

/**
 * 大对象直接进入老年代
 * <p>
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728 -XX:+UseParNewGC
 * <p>
 * https://blog.csdn.net/ciqingloveless/article/details/81877285
 * <p>
 * parnewGc 与serial old 默认匹配但是会发出警告
 * <p>
 * -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC
 */
@SuppressWarnings("all")
public class BigJavaBeanAllocation {
    public static final int _MB = 1024 * 1024;

    public static void testAllocation() throws Exception {
        byte[] allocation1, allocation2, allocation3, allcation4;

        allcation4 = new byte[4 * _MB];
        // allocation2 = new byte[4 * _MB];
        System.out.println("分配allocation4 over");
        Thread.sleep(1000000000);
    }


    public static void main(String[] args) throws Exception {
        testAllocation();
    }
}
