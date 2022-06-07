package com.jiamingku.collection.maptest;

import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by jiamingku on 16/9/14.
 */
public class TestMap1 {

    public static void main(String[] args) {
        TreeMap<String, String> tmp = init();
        Iterator<String> iterator_2 = tmp.keySet().iterator();
        while (iterator_2.hasNext()) {
            Object key = iterator_2.next();
            System.out.println("tmp.get(key) is :" + tmp.get(key));
        }

        SortedMap<String, String> tmp1 = tmp.tailMap("6");
        System.out.println("=========================");
        Iterator<String> iterator_3 = tmp1.keySet().iterator();
        while (iterator_3.hasNext()) {
            Object key = iterator_3.next();
            System.out.println("tmp.get(key) is :" + tmp1.get(key));
        }

        System.out.println(" ============================== ");

        Map<String, String> temp1 = tmp.descendingMap();


        iterator_3 = temp1.keySet().iterator();
        while (iterator_3.hasNext()) {
            Object key = iterator_3.next();
            System.out.println("tmp.get(key) is :" + temp1.get(key));
        }

    }


    public static TreeMap<String, String> init() {
        TreeMap<String, String> tmp = new TreeMap<String, String>();
        tmp.put("1", "aaa1");
        tmp.put("2", "bbb2");
        tmp.put("3", "ccc3");
        tmp.put("4", "cdc4");
        tmp.put("5", "cdc5");
        tmp.put("6", "cdc6");
        tmp.put("7", "cdc7");
        tmp.put("8", "cdc8");
        tmp.put("9", "cdc9");
        tmp.put("0", "cdc0");

        return tmp;
    }
}
