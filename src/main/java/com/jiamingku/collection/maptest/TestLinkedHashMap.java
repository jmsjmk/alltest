package com.jiamingku.collection.maptest;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by jiamingku on 16/9/1.
 */
public class TestLinkedHashMap {

    public static void main(String[] args) {

        Map<String, String>  map = new LinkedHashMap<>();

        map.put("one0","v1");
        map.put("one1","v1");
        map.put("one2","v1");
        map.put("one3","v1");

        System.out.println(map.toString());
    }
}
