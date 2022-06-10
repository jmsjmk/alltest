package com.jiamingku.j2se.refelct.type;

import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

public class PrintUtils {

    static void print(Object msg) {
        System.out.println(msg);

    }

    public static void printTypeArr(Type[] lowerTypes) {

        for (Type lowerType : lowerTypes) {

            System.out.println(lowerType.toString());
        }

        System.out.println("----");
    }


    public static void printTypeArr(String msg, Type[] lowerTypes) {
        System.out.println("长度:" + lowerTypes.length );
        for (Type lowerType : lowerTypes) {
            System.out.println(lowerType.toString());
        }

        System.out.println("----\n");
    }

    // WildcardType

    public static void printTypeArr(WildcardType wildcardType) {
        Type[] lowerBounds = wildcardType.getLowerBounds();
        Type[] upperBounds = wildcardType.getUpperBounds();

        System.out.println("下限长度:" + lowerBounds.length );
        for (Type lowerType : lowerBounds) {
            System.out.println(lowerType.toString());
        }

        System.out.println("上限长度:" + upperBounds.length );
        for (Type lowerType : upperBounds) {
            System.out.println(lowerType.toString());
        }

        System.out.println("----\n");
    }
}
