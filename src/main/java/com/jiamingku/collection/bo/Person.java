package com.jiamingku.collection.bo;

import java.util.ArrayList;

/**
 * Created by jiamingku on 2017/10/11.
 */
public class Person {
    private String name;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Person{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Integer[] a = {0,1,2,3,4,5,6,7,8};

        ArrayList<Integer> list = new ArrayList<>();

        for (int i :a) {
//            System.out.println(i);
            list.add(i);
        }

//        list.remover

    }
}
