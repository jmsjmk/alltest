package com.jiamingku.j2se.refelct.type;

import com.jiamingku.j2se.refelct.bo.GenericArrayTypeBean;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class ArrayGenericTest<T> {

    public static void main(String[] args) {
        Method method = GenericArrayTypeBean.class.getDeclaredMethods()[0];
        System.out.println(method);

        // public void test(List<String>[] pTypeArray, T[]
        // vTypeArray,List<String> list, String[] strings, Person[] ints)
        // // 这是 Method 中的方法
        Type[] types = method.getGenericParameterTypes();
        for (Type type : types) {
            System.out.println(type + "     type.getClass().getSimpleName():::" +  type.getClass().getSimpleName()  +" isGenericArrayType:\t\t\t\t"+ (type instanceof GenericArrayType));
        }
        System.out.println("------");
        for (Class<?> parameterType : method.getParameterTypes()) {
            System.out.println(parameterType);
        }
    }
}