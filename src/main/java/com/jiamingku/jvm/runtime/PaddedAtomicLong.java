package com.jiamingku.jvm.runtime;

import org.openjdk.jol.info.ClassLayout;

public class PaddedAtomicLong {
    public volatile long value = 0L;
    public long p1, p2, p3, p4, p5; // comment out


//    @Override
//    public int hashCode() {
//        System.out.println("value = " + value);
//        return 10000000;
//    }


    public synchronized void a() {
        System.out.println("线程：" + Thread.currentThread().getId() + " 进入同步方法:观察对象头变化 ");
        ClassLayout layout = ClassLayout.parseInstance(this);
        System.out.println(layout.toPrintable());

        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}