package com.jiamingku.collection.maptest;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

public class MapTest {
    static Map wMap = new WeakHashMap();
    static Map map = new HashMap();

    public static void main(String[] args) {
        init();
        testWeakHashMap();
        testHashMap();
    }


    public static void init() {
        String ref1 = new String("obejct1");
        String ref2 = new String("obejct2");
        String ref3 = new String("obejct3");
        String ref4 = new String("obejct4");
        wMap.put(ref1, "chaheObject1");
        wMap.put(ref2, "chaheObject2");
        map.put(ref3, "chaheObject3");
        map.put(ref4, "chaheObject4");
        System.out.println("String引用ref1，ref2，ref3，ref4 消失");

    }

    public static void testWeakHashMap() {

        System.out.println("WeakHashMap GC之前");
        for (Object o : wMap.entrySet()) {
            System.out.println(o);
        }
        try {
            System.gc();
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("WeakHashMap GC之后");
        for (Object o : wMap.entrySet()) {
            System.out.println(o);
        }
    }

    public static void testHashMap() {
        System.out.println("HashMap GC之前");
        for (Object o : map.entrySet()) {
            System.out.println(o);
        }
        try {
            System.gc();
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("HashMap GC之后");
        for (Object o : map.entrySet()) {
            System.out.println(o);
        }
    }

}
/**
 * 结果
 * String引用ref1，ref2，ref3，ref4 消失
 * WeakHashMap GC之前
 * obejct2=chaheObject2
 * obejct1=chaheObject1
 * WeakHashMap GC之后
 * HashMap GC之前
 * obejct4=chaheObject4
 * obejct3=chaheObject3
 * Disconnected from the target VM, address: '127.0.0.1:51628', transport: 'socket'
 * HashMap GC之后
 * obejct4=chaheObject4
 * obejct3=chaheObject3
 **/
