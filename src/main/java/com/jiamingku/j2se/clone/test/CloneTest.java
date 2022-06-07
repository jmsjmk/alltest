package com.jiamingku.j2se.clone.test;
/**
 * 要想clone 必须的实现Cloneable接口
 *
 * Created by jiamingku on 2019/7/20.
 */
public class CloneTest implements Cloneable {

//    @Test
//    public void testClone() {
//        Object o = null;
//        String s = new String();
//
//        s.clone();
//    }

    public static void main(String[] args) throws CloneNotSupportedException {
        CloneTest ss = new CloneTest();
        ss.clone();
    }



}
