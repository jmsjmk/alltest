package com.jiamingku.collection.compare;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class PersonSort {
    private int age;
    private String name;

    public void setAge(int age) {
        this.age = age;
    }

    public PersonSort(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return (age + "{" + name + "}");
    }


    public static void main(String[] args) {

    }
}

/**
 * 测试方法
 * <p>
 * Created by mingku.jia on 2017/7/31.
 */
public class PersonSortTest {

    public static void main(String[] args) {
        PersonSort[] ps = {new PersonSort(20, "Tom"), new PersonSort(20, "Jeff"),
                new PersonSort(30, "Mary"), new PersonSort(20, "Ada"),
                new PersonSort(40, "Walton"), new PersonSort(61, "Peter"),
                new PersonSort(20, "Bush")};

        List<PersonSort> personList = Arrays.asList(ps);

        /**
         * 注意:o1,o2的顺序,
         */
        Collections.sort(personList, new ComparatorOne());

        System.out.println(personList);

        /**
         * 注意:o1,o2的顺序,
         */
        Collections.sort(personList, new ComparatorTwo());
        System.out.println(personList);
    }


    private static class ComparatorOne implements Comparator<PersonSort> {
        @Override
        public int compare(PersonSort o1, PersonSort o2) {
            return o2.getAge() - o1.getAge();
        }
    }

    private static class ComparatorTwo implements Comparator<PersonSort> {
        @Override
        public int compare(PersonSort o1, PersonSort o2) {
            return o1.getAge() - o2.getAge();
        }
    }
}
