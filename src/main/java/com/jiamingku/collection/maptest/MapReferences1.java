package com.jiamingku.collection.maptest;

import com.jiamingku.collection.Person;

import java.util.HashMap;

/**
 * Created by jiamingku on 16/11/4.
 */
public class MapReferences1 {
    public static void main(String[] args) {
        Person p = new Person("name1", "age1");

        HashMap<Person, Person> maps = new HashMap<>();

        p.addMap(maps);
//        maps.put(p,p);

        System.out.println(maps);


        p = null;

        System.out.println(maps);


    }
}
