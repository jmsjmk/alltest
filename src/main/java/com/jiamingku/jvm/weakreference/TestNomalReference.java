package com.jiamingku.jvm.weakreference;

import java.io.IOException;

public class TestNomalReference {

    public static void main(String[] args) throws IOException {
        M m = new M();
        m = null;
        System.gc();
        System.out.println(m);
        System.in.read();
    }
}

class M {
    /**
     * 垃圾回收会回掉对象的finalize方法
     */
    @Override
    protected void finalize() throws Throwable {
        System.out.println(Thread.currentThread().getName() + " exe finalize" );
    }
}
