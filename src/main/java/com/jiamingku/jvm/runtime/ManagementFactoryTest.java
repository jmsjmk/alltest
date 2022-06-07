package com.jiamingku.jvm.runtime;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

public class ManagementFactoryTest {

    final List<String> fullGcName = Lists.newArrayList("ConcurrentMarkSweep", "MarkSweepCompact", "PS MarkSweep", "G1 Old Generation",
            "Garbage collection optimized for short pausetimes Old Collector", "Garbage collection optimized for throughput Old Collector",
            "Garbage collection optimized for deterministic pausetimes Old Collector");

    final List<String> youngGcName = Lists.newArrayList("ParNew", "Copy", "PS Scavenge", "G1 Young Generation", "Garbage collection optimized for short pausetimes Young Collector",
            "Garbage collection optimized for throughput Young Collector", "Garbage collection optimized for deterministic pausetimes Young Collector");


    /**
     * 获取java进程的pid
     */
    @Test
    public void getOwnPid() {
        String name1 = ManagementFactory.getRuntimeMXBean().getName();
        System.out.println(name1);
        Integer id = Integer.valueOf(name1.substring(0, name1.indexOf('@')));
        System.out.println(id);
    }

    /**
     * 获取当前的GC
     */
    @Test
    public void JVMGC() {
        while (true) {
            GarbageCollectorMXBean fullGc = null;

            GarbageCollectorMXBean yongGc = null;

            for (GarbageCollectorMXBean item : ManagementFactory.getGarbageCollectorMXBeans()) {
                String name = item.getName();
                if (youngGcName.contains(name)) {
                    yongGc = item;
                } else if (fullGcName.contains(name)) {
                    fullGc = item;
                }
            }

            System.out.println(yongGc.getCollectionCount());
            System.out.println(fullGc.getCollectionCount());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
