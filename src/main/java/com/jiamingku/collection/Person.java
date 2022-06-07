package com.jiamingku.collection;

import com.google.common.base.Objects;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by jiamingku on 16/11/4.
 */
public class Person {

    private String name;

    private String age;

    public Person(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public static void main(String[] args) {
        HashMap<String, String> maps = new HashMap<>();
//        String v = maps.putIfAbsent("1", "1");
//        for (Map.Entry<String, String> entry : maps.entrySet()) {
//            System.out.println("key:" + entry.getKey());
//            System.out.println("value:" + entry.getValue());
//        }
//
//        System.out.println(v);
//        System.out.println("---------------------------------------------");
//        maps.clear();
//        v = maps.put("1", "1111");
//        v = maps.put("1", "222222");
//        System.out.println(v);
//        maps.putIfAbsent("1", "12");
        maps.putIfAbsent(null, null);
        maps.putIfAbsent(null, null);
        for (Map.Entry<String, String> entry : maps.entrySet()) {
            System.out.println("key:" + entry.getKey());
            System.out.println("value:" + entry.getValue());
        }
    }


    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Person{");
        sb.append("name='").append(name).append('\'');
        sb.append(", age='").append(age).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public void addMap(HashMap<Person, Person> map) {
        map.put(this, this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equal(name, person.name) && Objects.equal(age, person.age);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, age);
    }
}
