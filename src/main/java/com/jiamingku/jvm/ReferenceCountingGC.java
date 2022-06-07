package com.jiamingku.jvm;


/**
 * Created by jiamingku on 2017/4/22.
 * <p>
 * -XX:+PrintGCDetails 输出GC的详细日志
 * <p>
 * <p>
 * -XX:+PrintGC 输出GC日志
 * -XX:+PrintGCDetails 输出GC的详细日志
 * -XX:+PrintGCTimeStamps 输出GC的时间戳（以基准时间的形式）
 * -XX:+PrintGCDateStamps 输出GC的时间戳（以日期的形式，如 2013-05-04T21:53:59.234+0800）
 * -XX:+PrintHeapAtGC 在进行GC的前后打印出堆的信息
 * -Xloggc:../logs/gc.log 日志文件的输出路径
 */
public class ReferenceCountingGC {
    public Object instance = null;

    public static final int _1MB = 1024 * 1024;

    private byte[] bigSize = new byte[2 * _1MB];

    public static void testGC() {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();

        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;

        System.out.println("ddd");
        int count = -12;
        while (true) {
            System.gc();
            if (count == 10) {
                break;
            }
            count++;
            if (count > 5) {
                break;
            }
            try {
                Thread.sleep(1000L);
                System.out.println("ddd");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        ReferenceCountingGC.testGC();
    }

}
