package com.jiamingku.j2se.basetype.box;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 引用传递 : 基本类型的封装类和String类型，因为他们是值是private final
 * 值传递 : 即 基本类型, 因为修改的仅是副本本身
 */
public class IntegerSwapTest {

    public final static void main(String... args) {
        Integer a = 5;
        Integer b = 7;
        changeValue(a, b);
        System.out.println("a: " + a.intValue() + " b: " + b.intValue());
        System.out.println("a: " + a + " b: " + b);
    }

    public static void changeValue(Integer i, Integer j) {

        /**
         * observer Integer Field
         */
        Field[] fs = Integer.class.getDeclaredFields();
        for (Field f : fs) {
            System.out.println(f.toString());
        }

        try {
            /**
             * access private
             * eg: private final int java.lang.Integer.value
             */
            Field field = Integer.class.getDeclaredField("value");
            field.setAccessible(true);

            /**
             * observer Field Self
             */
            Field[] ff = Field.class.getDeclaredFields();
            for (Field m : ff) {
                System.out.println(m.toString());
            }

            /**
             * access final
             */
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            /**
             * swap
             */
            int temp = j;
            field.set(j, i);
            field.set(i, new Integer(temp));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
