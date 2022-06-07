package com.jiamingku.jvm.weakreference;

import java.lang.ref.WeakReference;

/**
 * 不管内存够不够直接回收就完了
 *
 * @author wison
 */
public class TestWeakReference1 {
    public static void main(String[] args) throws InterruptedException {
        WeakReference<MM> m = new WeakReference<>(new MM());
        // MM s = m.get(); 这句话很重要---- 直接到垃圾回收的问题
        System.out.println(m.get());
        System.gc();
        Thread.sleep(400L);
        System.out.println(m.get());
        System.out.println("==");
        System.out.println("==");
        System.out.println("==");

        MM m1 = new MM();
        WeakReference<MM> md = new WeakReference<>(m1);
        m1 =null;
        System.gc();
        Thread.sleep(400L);
        while(true) {
            if (md.get() != null) {
                System.out.println("1");
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
class MM {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize MM");
    }
}
