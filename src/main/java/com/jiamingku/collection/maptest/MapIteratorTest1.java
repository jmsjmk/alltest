package com.jiamingku.collection.maptest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiamingku on 16/11/5.
 */
public class MapIteratorTest1 {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();

        map.put("key1", "v1");
        map.put("key2", "v1");
        map.put("key3", "v1");

        String key;
        String value;
        for (Map.Entry<String, String> entry: map.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();

            System.out.println(key + "\t" + value);


        }


        map.get(new Integer(1));

        map.put("","d");

//        map.put(new Integer(1), new Object());


    }
}
