package com.jiamingku.collection.maptest;

/**
 * Created by jiamingku on 16/9/1.
 */
public class TestHash1 {


    public static void main(String[] args) {
        TestHash1 t = new TestHash1();
       int a =  t.hashValue("ttee");
        System.out.println(a);

        System.out.println(" ===== ");

        int a1 = roundUpToPowerOf2(1);
        System.out.println("a1 = " + a1);
//
//        Long a2 = null;
//        long ad = a2;
//        System.out.println("ad = " + ad);

        Long aaaa = Long.valueOf(10);
        Long aaaab =  Long.valueOf(10);
        System.out.println(aaaa == aaaab);



    }


    private int hashValue(String str) {

        long seed = 131;
        long hash = 0;
        char[] charArray = str.toCharArray();
        for (char ch : charArray) {
            hash = hash * seed + ch;
        }
        return (int) ((hash & 0x7FFFFFFF) % 100);
    }

    private static int roundUpToPowerOf2(int number) {
        // assert number >= 0 : "number must be non-negative";
        return number >= Integer.MAX_VALUE
                ? Integer.MAX_VALUE
                : (number > 1) ? Integer.highestOneBit((number - 1) << 1) : 1;
    }
}
