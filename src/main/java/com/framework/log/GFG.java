package com.framework.log;

import java.lang.reflect.Constructor;

public class GFG {

    public static void main(String[] args) {
        // create a class object 
        Class classObj = shape.class;

        // get Constructor object 
        // array from class object 
        Constructor[] cons = classObj.getConstructors();

        // check isSynthetic or not 
        boolean answer = cons[0].isSynthetic();

        // print result 
        System.out.println("isSynthetic : " + answer);
    }

    @CustomAnnotation(createValues = "GFG")
    public class shape {

        @CustomAnnotation(createValues = "GFG")
        public shape() {
        }
    }

    // create a custom annotation 
    public @interface CustomAnnotation {
        public String createValues();
    }
}