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
public class HeapOOM {

    static class OOMObject{
        String a1 = "sdfsdfsdfsdfdddddddddddddddddddddddddddddddddddddddfs";
        String a2 = "sdddddddddddddddddddddddddddddddddddddddf";
        String a3 = "sdddddddddddddddddddddddddddf";
        String a4 = "sddddddddddddddddddddddddddddddddf";
        String a5 = "sdf";
        String a6 = "sdf";
        String a7 = "sdf";
        String a8 = "sdf";
    }


    static class OOMObject1{
        String a1 = "sdfsdfsdfsdfdddddddddddddddddddddddddddddddddddddddfs";
        String a2 = "sdddddddddddddddddddddddddddddddddddddddf";
        String a3 = "sdddddddddddddddddddddddddddf";
        String a4 = "sddddddddddddddddddddddddddddddddf";
        String a5 = "sdf";
        String a6 = "sdf";
        String a7 = "sdf";
        String a8 = "sdf";
    }

    public static void main(String[] args) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>();
        try {
            int c = 0;
            OOMObject1 o = new OOMObject1();
            while(true) {
               list.add(new OOMObject());
                c ++;
                if (c > 360140) {
                    System.out.println(" + c = " + c);
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("list.size() = " + list.size());
        }
    }
}
