package com.jiamingku.collection.hashEquals;

import java.util.HashSet;

/**
 * Created by jiamingku on 2017/5/11.
 */
public class Test {
    public static void main(String[] args) {

        HashSet sets = new HashSet();


        sets.add("ttt");

        sets.add("tttt");


        sets.contains("dddd");





    }

//    public void static remove() {
//
//
//    }

    public static int highestOneBit(int i) {
        // HD, Figure 3-1
        i |= (i >> 1);
        System.out.println(i);
        i |= (i >> 2);
        System.out.println(i);
        i |= (i >> 4);
        System.out.println(i);
        i |= (i >> 8);
        System.out.println(i);
        i |= (i >> 16);
        System.out.println(i);
        return i - (i >>> 1);
    }
}
