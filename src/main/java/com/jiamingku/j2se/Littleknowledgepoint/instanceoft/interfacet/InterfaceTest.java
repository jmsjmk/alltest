package com.jiamingku.j2se.Littleknowledgepoint.instanceoft.interfacet;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiamingku on 16/8/3.
 */
public class InterfaceTest {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();

        if (map instanceof Map) {
            System.out.println("is Map");

        }
        if (map instanceof HashMap) {
            System.out.println("is hashMap");
        }
        System.out.println("=======");
        HashMap hm = new HashMap();

        if (hm instanceof Map) {
            System.out.println("is Map");
        }

        if (hm instanceof HashMap) {
            System.out.println("is hashMap");
        }

        if (map instanceof Object) {
            System.out.println("is Object.");
        }
    }
}
