package com.jiamingku.jvm.runtime.lock;

import com.jiamingku.jvm.runtime.PaddedAtomicLong;
import org.openjdk.jol.info.ClassLayout;

public class LockTest {
    /**
     * 进入同步方法之后头变化.--退出时候头回复--这时候没有竞争.
     * @param args
     */
    public static void main(String[] args) {
        PaddedAtomicLong atomicLong = new PaddedAtomicLong();
        atomicLong.hashCode();
        ClassLayout layout = ClassLayout.parseInstance(atomicLong);
        System.out.println("进入前:::::::"+ layout.toPrintable());
        atomicLong.a();
        System.out.println("退出同步方法:::::::"+ layout.toPrintable());

    }
}
