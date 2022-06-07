package com.jiamingku.test;

import sun.reflect.generics.reflectiveObjects.TypeVariableImpl;

import java.lang.reflect.Method;

public class Tds<T>   {
    public static void main(String[] args) throws Exception {
        Tds<String> ttt = new Tds<>();
        Method get = ttt.getClass().getMethod("get");
        System.out.println("get.getReturnType() = " + get.getReturnType());

        String simpleName = get.getGenericReturnType().getClass().getSimpleName();
        System.out.println("simpleName = " + simpleName);

        TypeVariableImpl  t = (TypeVariableImpl)get.getGenericReturnType();
        String typeName = t.getTypeName();
        System.out.println("typeName = " + typeName);

    }



}

interface A extends B {
    int a();
}

interface B {
    int b();
}