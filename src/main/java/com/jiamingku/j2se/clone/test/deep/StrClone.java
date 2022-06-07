package com.jiamingku.j2se.clone.test.deep;

import com.google.common.base.Objects;

class CloneC implements Cloneable {
    public String str;
    public StringBuffer strBuff;
    public Integer i;

    public Object clone() {
        CloneC o = null;
        try {
            o = (CloneC) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("str", str)
                .add("strBuff", strBuff)
                .add("i", i)
                .toString();
    }
}

public class StrClone {
    public static void main(String[] a) {
        CloneC c1 = new CloneC();
        c1.str = new String("initializeStr");
        c1.i = 100;
        c1.strBuff = new StringBuffer("initializeStrBuff");
        System.out.println("c1.toString() = " + c1.toString());
        CloneC c2 = (CloneC) c1.clone();
        System.out.println("clone 之后的结果...");
        System.out.println("c2.toString =" + c2.toString());
        System.out.println("---------准备修改..------潜copy---");
        c1.str = "1111111";
        c1.strBuff.append("dddddddddddddddddddddddddddddd");
        System.out.println("c1.toString() = " + c1.toString());
        System.out.println("c2.toString =" + c2.toString());
    }
}