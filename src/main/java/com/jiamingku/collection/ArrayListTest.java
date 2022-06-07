package com.jiamingku.collection;

import com.google.common.base.Objects;
import com.jiamingku.collection.bo.Person;
import org.apache.commons.collections.comparators.NullComparator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiamingku on 2017/10/11.
 */
public class ArrayListTest {
    private String age;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayListTest that = (ArrayListTest) o;
        return Objects.equal(age, that.age) && Objects.equal(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(age, name);
    }

    public static void main(String[] args) {
        ArrayList<Person> list = new ArrayList<>();

        Person person = new Person("fist");
        list.add(person);
        ArrayList<Person> list1 = (ArrayList<Person>) list.clone();

        List<Object> objects = new ArrayList<>();
        objects.add("3");
        for (Person p : list1) {
            System.out.println(p);
        }


        person.setName("second");

        for (Person p : list1) {
            System.out.println(p);
        }
        System.out.println(" = ============");

        testaddAll();

        list.sort(new NullComparator(null));
        ;
    }

    public static void testaddAll() {
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        ArrayList<String> list2 = new ArrayList<>();
        list2.add("3");
        list2.add("4");
        list.addAll(1, list2);

        list.addAll(list2);
        System.out.println("list = " + list);

    }
}
