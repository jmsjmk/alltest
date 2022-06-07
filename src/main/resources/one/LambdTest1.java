package com.jiamingku.lambda.one;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by jiamingku on 2018/6/2.
 */
public class LambdTest1 {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();

        Person p1 = new Person();
        p1.setAge(11);
        p1.setName("name1");

        Person p3 = new Person();
        p3.setAge(12);
        p3.setName("super1");

        Person p2 = new Person();
        p2.setAge(20);
        p2.setName("name2");


        persons.add(p1);
        persons.add(p2);
        persons.add(p3);

//        persons.add(null);
//        persons.add(null);
//        persons.add(null);
        System.out.println(persons);
//        iterator1(persons);
        System.out.println(persons);
        System.out.println(" ======== ");
        test(persons);
    }


    static void iterator1(List<Person> personList) {
        personList.forEach(p -> p.setAge(1000));
    }

    static void iterator2(List<Person> personList) {
        personList.stream().filter(p -> p.getAge() == 1).forEach(p -> p.setAge(10));
    }

    static void iterator3(List<Person> personList) {
        personList.stream().filter(p -> p.getAge() == 1).filter(p -> p.getName().equals("super")).forEach(p -> p
                .setAge(10));
    }
    static void iterator4(List<Person> personList) {
        personList.stream().filter(p -> p != null).filter(p -> p.getName().equals("super")).forEach(p -> p
                .setAge(10));
    }

    // ========================处理结果集合

    static void collect1(List<Person> persons) {
        persons.stream().filter(p -> p.getAge() == 1).collect(Collectors.toList());

    }
//    static void collect1(List<Person> persons) {
//        persons.stream().filter(p -> p.getAge() == 1).collect(Collectors.toList());
//
//    }

    // ======================map映射函数操作
    static void map1(List<Person> persons) {
        Set<String> list = persons.stream().filter(p -> p.getAge() == 1).map(s -> s.getName()).
                collect(Collectors.toSet());
        System.out.println("list = " + list);

    }

    public static void test(List<Person> p ) {
        Person person = p.stream().filter(p1 -> p1.getAge() ==10).findFirst().get();
        System.out.println("person = " + person);
    }

}
