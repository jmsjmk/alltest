package com.jiamingku.collection.maptest;

import com.jiamingku.collection.Person;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiamingku on 16/11/4.
 */
public class MapReferences {
    public static void main(String[] args) {
        Person p = new Person("name1", "age1");
//        Person p1 = new Person("name2", "age2");

        HashMap<Person, Person> maps = new HashMap<>();

        p.addMap(maps);
        p = null;
        maps.put(p,p);
//        maps.put(p1,p);

        System.out.println(maps.size());

        System.out.println(maps.toString());
        System.out.println("======");
//        System.out.println(maps);

//        Map<String, Person> personMap = new HashMap<>();
//
//        personMap.put("p1", p);
//        personMap.put("p2", p);
//
//        System.out.println(personMap);
//        p = null;
//
////        System.out.println(maps);
//
//
//        //Map<String, Person> personMap = new HashMap<>();
//
//        personMap.put("p3", p);
//        personMap.put("p4", p);
//
//        System.out.println(personMap);

    }
}
