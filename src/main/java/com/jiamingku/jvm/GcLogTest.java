package com.jiamingku.jvm;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 根据方法运行去分析gc日志,就是多操作就看出来了
 * <p>
 * -Xms100m -Xmx100m -XX:+PrintGCDetails
 * <p>
 * -Xms100m -Xmx100m -XX:+PrintGC
 * <p>
 * -Xms100m -Xmx100m -XX:+PrintGCTimeStamps
 * <p>
 * -Xms100m -Xmx100m -XX:+PrintGCDateStamps
 * <p>
 * -Xms100m -Xmx100m -XX:+PrintHeapAtGC
 */
@SuppressWarnings("all")
public class GcLogTest {

    private byte[] b = new byte[2 * 1024 * 1024];

    public static final int _MB = 1024 * 1024;

    public static void main(String[] args) {
        List<GcLogTest> list = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            GcLogTest gcLogTest = new GcLogTest();
            list.add(gcLogTest);
        }

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * -Xms10m -Xmx10m -XX:+PrintGCDetails
     * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintGCDateStamps
     * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintGCTimeStamps
     *
     * @throws Exception
     */
    @Test
    public void testGc1() throws Exception {
        byte[] allocation1, allocation2, allocation3, allcation4;
        allocation2 = new byte[4 * _MB];
        allocation3 = new byte[4 * _MB];
        System.gc();

        Thread.sleep(100000000L);
    }


    /**
     * 1.-XX:+UseConcMarkSweepGC
     *   -XX:+UseParNewGC
     *   -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC
     *
     * 2.日志信息如下7步:https://www.jianshu.com/p/86e358afdf17
     * [GC (CMS Initial Mark) [1 CMS-initial-mark: 8200K(10240K)] 12671K(19456K), 0.0009307 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * [CMS-concurrent-mark-start]
     * [CMS-concurrent-mark: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * [CMS-concurrent-preclean-start]
     * [CMS-concurrent-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * [CMS-concurrent-abortable-preclean-start]
     *  CMS: abort preclean due to time [CMS-concurrent-abortable-preclean: 0.009/5.066 secs] [Times: user=0.01 sys=0.01, real=5.07 secs]
     * [GC (CMS Final Remark) [YG occupancy: 4470 K (9216 K)][Rescan (parallel) , 0.0017114 secs][weak refs processing, 0.0000096 secs][class unloading, 0.0006379 secs][scrub symbol table, 0.0008921 secs][scrub string table, 0.0002164 secs][1 CMS-remark: 8200K(10240K)] 12671K(19456K), 0.0036177 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
     * [CMS-concurrent-sweep-start]
     * [CMS-concurrent-sweep: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * [CMS-concurrent-reset-start]
     * [CMS-concurrent-reset: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     *
     * @param args
     */
    @Test
    public void testGC2() throws Exception {
        byte[] allocation1, allocation2, allocation3, allcation4;
        allocation1 = new byte[_MB / 4];
        allocation2 = new byte[4 * _MB];
        allocation3 = new byte[4 * _MB];
        System.gc();

        Thread.sleep(100000000L);

    }

    /**
     * <p> 串行的gc
     * -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC  -XX:MaxTenuringThreshold=1
     * 一般串行的才会出现 tenured
     * +UseSerialGC 默认会开始 串行的老年代版本
     *
     * @param args
     */
    @Test
    public void testGC3() throws Exception {
        byte[] allocation1, allocation2, allocation3, allcation4;
        allocation1 = new byte[_MB / 4];
        allocation2 = new byte[4 * _MB];
        allocation3 = new byte[4 * _MB];
        System.gc();

        Thread.sleep(100000000L);

    }
}
