package com.jiamingku.jvm.oomdemo;

/**
 * 这个主要是查询最大对象---的类型是什么
 */
public class HeapOOM2 {

    /**
     * 通过工具是可以查询到具体的代码行数出现异常的
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        byte[] bytes0 = new byte[1024 * 102];
        byte[] bytes1 = new byte[1024 * 1024 * 19];
        byte[] bytes2 = new byte[1024 * 1024 * 19];
        byte[] bytes3 = new byte[1024 * 1024 * 19];
        byte[] bytes4 = new byte[1024 * 1024 * 19];
        byte[] bytes5 = new byte[1024 * 1024 * 19];
        while (true) {
            System.out.println("bytes = " + bytes0);
            Thread.sleep(1000L);
        }
    }
}
