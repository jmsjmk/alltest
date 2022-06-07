package com.jiamingku.collection.maptest;

import com.jiamingku.collection.Person;

import java.lang.ref.WeakReference;
import java.util.HashMap;

/**
 * Created by jiamingku on 16/11/4.
 */
public class MapReferences2 {
    public static void main(String[] args) {
        Person p = new Person("name1", "age1");

        Person p2 = p;
        System.out.println(p2 == p);
        System.out.println("===");

        WeakReference<Person> pwr = new WeakReference<Person>(p);

        Person p3 = pwr.get();
        System.out.println(p3 == p2);
        System.out.println("===");


        HashMap<Person, Person> maps = new HashMap<>();

        p3.addMap(maps);
        maps.put(p3, p3);
        p3 = null;
//        maps.put(p1,p);

        System.out.println(maps.size());

        System.out.println(maps);


    }
}
