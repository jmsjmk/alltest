package com.jiamingku.j2se.refelct.type;

import com.jiamingku.j2se.refelct.bo.SuperInterface1;
import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BaseGenericReflectTest {

    /**
     * 残酷的现实,在泛型代码内部,无法获取任何有关泛型参数类型的信息
     */
    @Test
    public void test1() {
        TypeVariable[] c = SuperInterface1.class.getTypeParameters();
        Arrays.stream(c).forEach(System.out::println);
    }

    @Test
    public void test2() {
//        List<String> list = new ArrayList<String>() {
//        };

        List<String> list = new ArrayList<String>();

        Type clazz = list.getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) clazz;
        String s = pt.getActualTypeArguments()[0].toString();
        System.out.println(s);

    }
}
