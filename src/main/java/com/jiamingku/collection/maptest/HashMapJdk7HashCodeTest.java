package com.jiamingku.collection.maptest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mingku.jia on 2017/9/26.
 */
public class HashMapJdk7HashCodeTest {

    public static void main(String[] args) {
        int h = 0xEEEaaaaa;
        int h1 = h >>> 20;
        int h2 = h >>> 12;
        int h3 = h1 ^ h2;
        int h4 = h ^ h3;
        int h5 = h4 >>> 7;
        int h6 = h4 >>> 4;
        int h7 = h5 ^ h6;
        int h8 = h4 ^ h7;
        print(" >>>> >>> >>: ");
        printBin(h);
        print(" h >>> 20=h1: ");
        printBin(h1);
        print(" h >>> 12=h2: ");
        printBin(h2);
        print("  h1 ^ h2=h3: ");
        printBin(h3);
        print("   h ^ h3=h4: ");
        printBin(h4);
        print(" h4 >>> 7=h5: ");
        printBin(h5);
        print(" h4 >>> 4=h6: ");
        printBin(h6);

        print("  h5 ^ h6=h7: ");
        printBin(h7);
        print("  h4 ^ h6=h8: ");
        printBin(h8);
    }

    static void printBin(int h) {
        System.out.println(String.format("%32s",
                Integer.toBinaryString(h)).replace(' ', '0'));
    }

    static void print(String des) {
        System.out.print(des);
    }
}
