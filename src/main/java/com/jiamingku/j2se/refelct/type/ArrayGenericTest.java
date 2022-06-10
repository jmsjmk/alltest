package com.jiamingku.j2se.refelct.type;

import com.jiamingku.j2se.refelct.bo.GenericArrayTypeBean;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * ------------------------------------------------
 * // 属于 GenericArrayType
 * List<String>[] pTypeArray;
 * // 属于 GenericArrayType
 * T[] vTypeArray;
 * // 不属于 GenericArrayType
 * List<String> list;
 * // 不属于 GenericArrayType
 * String[] strings;
 * // 不属于 GenericArrayType
 * Person[] ints;
 * -------------------------------------------------
 */
public class ArrayGenericTest<T> {

    public static void main(String[] args) {
        Method method = GenericArrayTypeBean.class.getDeclaredMethods()[0];
        System.out.println(method+"\n\n\n\n\n");

        // public void test(List<String>[] pTypeArray, T[]
        // vTypeArray,List<String> list, String[] strings, Person[] ints)
        // // 这是 Method 中的方法
        // ------获取参数类型的更高级别的抽象
        Type[] types = method.getGenericParameterTypes();
        for (Type type : types) {
            System.out.println(type + "\ntype.getClass().getSimpleName():" + type.getClass().getSimpleName()
                    + "\nisGenericArrayType:" + (type instanceof GenericArrayType));
            System.out.println();
        }
        System.out.println("------");
        // ------这个就是直接获取class信息
        for (Class<?> parameterType : method.getParameterTypes()) {
            System.out.println(parameterType);
        }
    }
}