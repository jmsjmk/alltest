package com.jiamingku.j2se.refelct.bo;

import org.springframework.stereotype.Service;

import java.io.Serializable;
@SuppressWarnings("ALL")
@Service
public class Stu extends Peo implements Serializable , Comparable{
    @Override
    public int compareTo(Object o) {
        return 0;
    }

    private String sname;
    private String sage;

    public static void main(String[] args) {


        String s = "SELECT * from policy_emptyrun_order_%2d";
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0;i < 50; i++) {
           String s1 =  String.format(s, i);
//            System.out.println("s1 = " + s1);
            stringBuffer.append(s1);
            stringBuffer.append("\n");
            stringBuffer.append("union all \n");

        }

        System.out.println("stringBuffer.TO = " + stringBuffer.toString());
    }
}