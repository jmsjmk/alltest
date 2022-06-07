package com.jiamingku.j2se.clone.test.deep;

import com.google.common.base.Objects;

class CloneA implements Cloneable {
    private int i;
    private long l;
    private String cloneAName;

    public CloneA() {
    }

    public CloneA(int i, long l, String cloneAName) {
        this.i = i;
        this.l = l;
        this.cloneAName = cloneAName;
    }

    public Object clone() {
        CloneA o = null;
        try {
            o = (CloneA) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("i", i)
                .add("l", l)
                .add("cloneAName", cloneAName)
                .toString();
    }
}

class CloneB implements Cloneable {
    public int i;
    public CloneA cloneA = new CloneA(1, 1L, "cloneA");

    public Object clone() {
        CloneB o = null;
        try {
            o = (CloneB) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        o.cloneA = (CloneA) cloneA.clone();
        return o;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("i", i)
                .add("cloneA", cloneA)
                .toString();
    }
}

/**
 *
 */
@SuppressWarnings("all")
public class CloneMain {
    public static void main(String[] a) {
        CloneB b1 = new CloneB();
        CloneB b2 = (CloneB) b1.clone();
        System.out.println("=================================");
        System.out.println(b1);
        System.out.println("=================================");
        System.out.println(b2);

        if (b1 == b2) {
            System.out.println("相等");
        } else {
            System.out.println("不相等.");
        }
    }
}
