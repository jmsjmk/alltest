package com.framework.log.spped4jtest;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class S {

    public static void main(String[] args) {
        HashMap objectObjectHashMap = new HashMap<>();

//        HashMap<String, String> stringStringHashMap = (HashMap<String, String>)objectObjectHashMap;


        HashMap<? super String, String> stringStringHashMap = null;

        HashMap<String, String> sss = (HashMap<String, String>) stringStringHashMap;

        long l1 = MILLISECONDS.toMicros(1);
        System.out.println(l1);


        long l11 = Duration.of(11, ChronoUnit.MICROS).toNanos();
        System.out.println(l11);

        TreeMap<Long, Long> treeMap = new TreeMap<>();
        treeMap.put(100L, 1000L);
        treeMap.put(101L, 1000L);
        treeMap.put(102L, 1000L);
        treeMap.put(103L, 1000L);
        treeMap.put(19L, 1000L);
        for (Map.Entry<Long, Long> entry : treeMap.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("-------");
        }
    }
}
