package com.jiamingku.j2se.clone;

import org.junit.Test;

import java.util.Enumeration;
import java.util.Hashtable;

public class ObjRef implements Cloneable {
    Obj aObj = new Obj();
    int aInt = 11;

    //修改引用类型
    public void changeObj(Obj inObj) {
        inObj.str = "changed value";
    }

    //修改基本类型
    public void changePri(int inInt) {
        aInt = 22;
    }

    public static void main(String[] args) throws Exception {
        ObjRef oRef = new ObjRef();


        System.out.println("==================value transmit==========================");
        System.out.println("Before call changeObj() method: " + oRef.aObj);
        oRef.changeObj(oRef.aObj);
        System.out.println("After call changeObj() method: " + oRef.aObj);

        System.out.println("==================object transmit===========================");
        System.out.println("Before call changePri() method: " + oRef.aInt);
        oRef.changePri(oRef.aInt);
        System.out.println("After call changePri() method: " + oRef.aInt);

        System.out.println("==================object.clone() transmit==========================");
        System.out.println("Before call changeObj() method: " + oRef.aObj);
        oRef.changeObj(((ObjRef) oRef.clone()).aObj);
        System.out.println("After call changeObj() method: " + oRef.aObj);

    }

    @Test
    public void testHashTable() {
        Hashtable ht = new Hashtable();
        StringBuffer sb = new StringBuffer();
        sb.append("abc,");
        ht.put("1", sb);
        sb.append("def,");
        ht.put("2", sb);
        sb.append("mno,");
        ht.put("3", sb);
        sb.append("xyz.");
        ht.put("4", sb);

        int numObj = 0;
        Enumeration it = ht.elements();
        while (it.hasMoreElements()) {
            System.out.print("get StringBufffer " + (++numObj) + " from Hashtable: ");
            System.out.println(it.nextElement());
        }
    }

}