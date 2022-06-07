package com.jiamingku.datastructure;

import com.jiamingku.datastructure.bo.Person;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class TreeSetUnit {
    public static void main(String[] args) {
        Set ts = new TreeSet();
        ts.add("bac");
        ts.add("abc");
        ts.add("xyz");
        ts.add("rst");
        Iterator it = ts.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    public static void sortObject() {
        TreeSet<Person> set = new TreeSet<Person>();
        set.add(new Person(20, "Tom"));
        set.add(new Person(20, "Jeff"));
        set.add(new Person(30, "Mary"));
        set.add(new Person(20, "Ada"));
        set.add(new Person(40, "Walton"));
        set.add(new Person(61, "Peter"));
        set.add(new Person(20, "Bush"));
        System.out.println(set);
    }

    /**
     * 有序的hashset
     */
    public static void test() {
        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.add("1");
        treeSet.add("1");
        treeSet.add("3");
        treeSet.add("4");
        treeSet.add("5");
        treeSet.add("2");
        System.out.println(treeSet);
    }
}