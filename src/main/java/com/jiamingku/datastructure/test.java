package com.jiamingku.datastructure;

import com.jiamingku.datastructure.bo.Person;

import java.lang.reflect.Field;

/**
 * Created by jiamingku on 2017/6/17.
 */
@SuppressWarnings("all")
public class test {
    public static class P {
        @Override
        public int hashCode() {
          return  100;
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }


    }
    public static void main(String[] args) throws Exception {
        Person person = new Person(111,"1");
        Field field =  person.getClass().getDeclaredField("age");
        field.setAccessible(true);
        String s = field.get(person).toString();
        System.out.println(s);
    }


    public Integer[] mp(Integer[] array) {
        int temp;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] < array[j + 1]) {
                    temp = array[j];

                    array[j] = array[j + 1];

                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }

    public static Integer[] xz(Integer[] array) {
        int index;
        int temp;
        for (int i = 0; i < array.length - 1; i++) {
            index = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] > array[index]) {
                    index = j;
                }
            }
            if (i != index) {
                temp = array[i];
                array[i] = array[index];
                array[index] = temp;
            }
        }
        return array;
    }


    public static Integer[] is(Integer[] array) {
        int temp;
        for (int i = 1; i < array.length; i++) {

            for (int j = i; j > 0; j--) {

                if (array[j] < array[j - 1]) {
                    temp = array[j];

                    array[j] = array[j - 1];

                    array[j - 1] = temp;
                } else {
                    break;
                }
            }
        }
        return array;
    }


    public static Integer[] quicklySort(Integer[] integers, int s, int e) {
        int value = integers[s];
        int i = s;
        int j = e;
        while (i < j) {
            while (i < j && integers[i] < value) {
                i++;
            }
            while (i < j && integers[j] > value) {
                j--;
            }
            if (integers[i].intValue() == integers[j].intValue() && i < j) {
                j--;
            } else {
                int temp;
                temp = integers[i];
                integers[i] = integers[j];
                integers[j] = temp;
            }
        }
        if (i - 1 > s) {
            quicklySort(integers, s, i - 1);
        }
        if (j + 1 < e) {
            quicklySort(integers, j + 1, e);
        }
        return integers;
    }
}
