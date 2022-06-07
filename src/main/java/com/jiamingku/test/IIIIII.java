package com.jiamingku.test;

import com.jiamingku.datastructure.jzoffer.TwoArray;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by jiamingku on 2018/1/2.
 */
public class IIIIII extends TwoArray {

    public static void main(String[] args) {

        LinkedList<String> list = new LinkedList<>();

        for (int i = 0; i < 10; i++) {
            list.add(i + "");
        }
        ListIterator<String> iterator = list.listIterator();
        while (iterator.hasNext()) {
            String s = iterator.next();

            if (s.equals("4")) {
                System.out.println("s = " + s);

                if (iterator.hasPrevious()) {
                    s = iterator.previous();
                    System.out.println("s = " + s);
                }
                break;
            }
        }
    }
}
