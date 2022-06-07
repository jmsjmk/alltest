package com.jiamingku.test;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class BCD<T> extends Abc<T> {
   public  static Abc<String> abc = new BCD<>();
    public static void main(String[] args) {
        Abc<String> abc1 = new BCD<>();
//        String s = abc1.getClass().ac

        /*
        Type t = abc1.getClass().getGenericSuperclass();
        System.out.println("t.getTypeName() = " + t.getTypeName());
        for (Field field : abc1.getClass().getFields()) {
            Type genericType = field.getGenericType();
            if (genericType instanceof ParameterizedType) {
                String typeName = ParameterizedType.class.cast(genericType).getTypeName();
                System.out.println("typeName = " + typeName);
                Type[] actualTypeArguments = ParameterizedType.class.cast(genericType).getActualTypeArguments();
                for (Type actualTypeArgument : actualTypeArguments) {
                    System.out.println("actualTypeArgument.getTypeName() = " + actualTypeArgument.getTypeName());
                }
            }
        }

         */
    }
}
