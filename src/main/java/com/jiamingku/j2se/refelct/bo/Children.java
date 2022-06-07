package com.jiamingku.j2se.refelct.bo;

import sun.reflect.generics.reflectiveObjects.TypeVariableImpl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class Children<Entity> implements SuperInterface<Entity> {

    public static void main(String[] args) {
        Children children = new Children();
        //System.out.println(children.getEntityClass()); // class Entity

//        Type[] types = children.getClass().getGenericInterfaces();
//        for (Type type : types) {
//            System.out.println(type.getTypeName());
//            if (type.getTypeName().startsWith(SuperInterface.class.getName())) {
//                ParameterizedType pt = (ParameterizedType) type;
//                Object o =  (Class)pt.getActualTypeArguments()[0];
//                System.out.println(o);
//            }
//        }

        // --------------------------------------List为什么？

     Type[] types = children.getClass().getGenericInterfaces();
        for (Type type : types) {
            System.out.println(type.getTypeName());
            if (type.getTypeName().startsWith(SuperInterface.class.getName())) {
                ParameterizedType pt = (ParameterizedType) type;
                TypeVariableImpl o =  (TypeVariableImpl)pt.getActualTypeArguments()[0];

                System.out.println(o.getTypeName());
                System.out.println(o.getName());
            }
        }
    }


}