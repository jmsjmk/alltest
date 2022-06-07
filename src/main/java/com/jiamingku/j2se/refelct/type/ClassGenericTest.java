package com.jiamingku.j2se.refelct.type;

import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.io.FileInputStream;
import java.lang.reflect.Type;

public class ClassGenericTest {
    public static void main(String[] args) {
        Class c = Tttt.class.getSuperclass();
        System.out.println("class.getSuperclass() = " + c);
        Type p = Tttt.class.getGenericSuperclass();
        System.out.println("class.getGenericSuperclass = " + p.getTypeName());


        ParameterizedTypeImpl typeVariableBean = (ParameterizedTypeImpl) p;
        for (Object value : typeVariableBean.getActualTypeArguments()) {
            System.out.println("value = " + value);
        }
        System.out.println("----------------------------------------------------");

    }
}


class Tttt extends TypeVariableBean<FileInputStream, Object> {


}