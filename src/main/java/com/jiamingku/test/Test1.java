package com.jiamingku.test;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiamingku on 2019/6/5.
 */
public class Test1 {

    List stringList = new ArrayList();

    List integerList = new ArrayList();

    public static void main(String... args) throws Exception {
        Field stringListField = Test1.class.getDeclaredField("stringList");

        ParameterizedType stringListType = (ParameterizedType) stringListField.getGenericType();

        Class  stringListClass = (Class ) stringListType.getActualTypeArguments()[0];

        System.out.println(stringListClass); // class java.lang.String.

        Field integerListField = Test1.class.getDeclaredField("integerList");

        ParameterizedType integerListType = (ParameterizedType) integerListField.getGenericType();

        Class  integerListClass = (Class ) integerListType.getActualTypeArguments()[0];

        System.out.println(integerListClass); // class java.lang.Integer.

    }
}
