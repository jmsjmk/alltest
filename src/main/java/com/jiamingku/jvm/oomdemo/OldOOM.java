package com.jiamingku.jvm.oomdemo;

import java.util.ArrayList;
import java.util.List;

/**
 * 如何老年代溢出，设置阀门值之后不断的创建大对象老年代就溢出了
 * <p>
 * 这个老年代溢出：你dump出来文件。
 * 该要中可以看到内存溢出
 * OutOfMemoryError.java
 * <p>
 * 点击类：可以查看出来那个类型的占用的空间大。默认是byte。
 * 点击进去就可以看到这个类型对应实例。+在点击进去如果是数组可以看到清晰的数组元素。记住有对象头的概念在里面
 */
public class OldOOM {
    private static final int _1MB = 1024 * 1024 * 2;

    /**
     * VM参数：
     * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=1048576
     */
    public static byte[] testPretenureSizeThreshold() {
        byte[] allocation;
        allocation = new byte[4 * _1MB];  //直接分配在老年代中
        return allocation;
    }

    public static void main(String[] args) throws Exception {
        List<byte[]> list = new ArrayList<>();
        byte[] a;
        for (int i = 0; i < 100; i++) {
            a = testPretenureSizeThreshold();
            list.add(a);
            Thread.sleep(10l);
        }
    }
}
