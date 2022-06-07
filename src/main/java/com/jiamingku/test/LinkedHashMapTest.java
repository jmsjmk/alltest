package com.jiamingku.test;

import java.util.LinkedHashMap;

/**
 * Created by jiamingku on 16/7/20.
 */
public class LinkedHashMapTest {
    public static void main(String[] args) {

        LinkedHashMap<String, String> lm = new LinkedHashMap<>();

        lm.put("key1", "val1");
        lm.put("key2", "val2");
        lm.put("key3", "val3");
        lm.put("key4", "val4");
        lm.put("key5", "val5");
        lm.put("key6", "val6");

        lm.keySet().stream().forEach(System.out::println);
    }
}
