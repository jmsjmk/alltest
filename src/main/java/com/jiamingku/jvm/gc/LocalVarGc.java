package com.jiamingku.jvm.gc;

/**
 * -XX+Printgcdetails
 */
public class LocalVarGc {

    /**
     * 不回收
     */
    public void l1() {
        byte[] buf = new byte[1024 * 1024 * 10];
        System.gc();
    }

    /**
     * 回收
     */
    public void l2() {
        byte[] buf = new byte[1024 * 1024 * 10];
        buf = null;
        System.gc();
    }

    /**
     * 不回收 是因为最大的局部变量表中有数据
     */
    public void l3() {
        {
            byte[] buf = new byte[1024 * 1024 * 10];
        }
        System.gc();
    }

    /**
     * 这个能回收是最大2，但是 a替换了buf
     */
    public void l4() {
        {
            byte[] buf = new byte[1024 * 1024 * 10];
        }
        int a = 10;
        System.gc();
    }

    public void l5() {
        l1();
        System.gc();
    }

    public static void main(String[] args) {
        LocalVarGc localVarGc = new LocalVarGc();
        localVarGc.l1();
    }
}
