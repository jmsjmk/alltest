package com.framework.log;

import java.lang.reflect.Constructor;

public class GFG1 {

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

    public class shape {

        public shape() {
        }
    }


}