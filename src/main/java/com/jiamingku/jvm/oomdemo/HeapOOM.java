package com.jiamingku.jvm.oomdemo;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.jianshu.com/p/1017149fb5f4 ====常见溢出的例子
 * <p>
 * VM args:
 * -Xms20m -Xmx20m  -XX:+HeapDumpOnOutOfMemoryError
 * -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/Users/jiamingku/Desktop/.1hprof -XX:OnOutOfMemoryError ="sh ~/cleanup.sh" MyApp
 * <p>
 * 扩展：
 * HProf is a tool built into JDK for profiling the CPU and heap usage within a JVM.
 * A Java process crash may produce an hprof file containing a heap dump of the process at the time of the failure.
 * This is typically seen in scenarios with "java.lang.OutOfMemoryError"
 * <p>
 */
public class HeapOOM {

    static class OOMObject {
        String a1 = "sdfsdfsdfsdfdddddddddddddddddddddddddddddddddddddddfs";
        String a2 = "sdddddddddddddddddddddddddddddddddddddddf";
        String a3 = "sdddddddddddddddddddddddddddf";
        String a4 = "sddddddddddddddddddddddddddddddddf";
        String a5 = "sdf";
        String a6 = "sdf";
        String a7 = "sdf";
        String a8 = "sdf";
    }


    static class OOMObject1 {
        String a1 = "sdfsdfsdfsdfdddddddddddddddddddddddddddddddddddddddfs";
        String a2 = "sdddddddddddddddddddddddddddddddddddddddf";
        String a3 = "sdddddddddddddddddddddddddddf";
        String a4 = "sddddddddddddddddddddddddddddddddf";
        String a5 = "sdf";
        String a6 = "sdf";
        String a7 = "sdf";
        String a8 = "sdf";
    }

    public static void main(String[] args) {
        System.out.println("System.getProperty(\"user.dir\") = " + System.getProperty("user.dir"));
        List<OOMObject1> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject1());
        }
    }
}
