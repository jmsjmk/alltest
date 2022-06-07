package com.jiamingku.jvm.gc;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * VM args: -Xms20m -Xmx20m  -XX:+HeapDumpOnOutOfMemoryError
 *
 * 可恨的是在dump时候看不到jvm参数
 *
 * Created by jiamingku on 2017/4/21.
 *
 *  -XX:HeapDumpPath=${目录}。
 *
 *  -XX:+UseParallelGC -XX:+UseConcMarkSweepGC
 *  Conflicting collector combinations in option list; please refer to the release notes for the combinations allowed
 *
 *  垃圾收集器就出现冲突的。
 */
public class HeapOOMByte {

    byte[] buffer = new byte[1024 * 1024];

    public static void main(String[] args) throws InterruptedException {

        List<HeapOOMByte> list = new ArrayList<>();
        int count = 0;
        try {
            while(true) {
                list.add(new HeapOOMByte());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
