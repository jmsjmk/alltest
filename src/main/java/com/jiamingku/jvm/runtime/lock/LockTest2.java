package com.jiamingku.jvm.runtime.lock;

import com.jiamingku.jvm.runtime.PaddedAtomicLong;
import org.openjdk.jol.info.ClassLayout;

public class LockTest2 {
    /**
     * 有争抢之后--对象头里面设置成为了重量级锁
     *
     * @param args
     */
    public static void main(String[] args) {
        PaddedAtomicLong atomicLong = new PaddedAtomicLong();
//        atomicLong.hashCode();
        ClassLayout layout = ClassLayout.parseInstance(atomicLong);
        System.out.println("进入前:::::::" + layout.toPrintable());
        new Thread(() -> atomicLong.a()).start();
        new Thread(() -> atomicLong.a()).start();
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("退出同步方法:::::::" + layout.toPrintable());

    }
}
