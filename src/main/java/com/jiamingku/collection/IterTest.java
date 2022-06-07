package com.jiamingku.collection;

import com.google.common.base.Objects;
import org.junit.Test;

import java.util.Iterator;

/**
 * 迭代器--新循环--等.
 * https://www.cnblogs.com/wolf-zt/p/6835012.html
 */
public class IterTest implements Iterable<String> {
    private String s1;
    private String s2;

    @Override
    public Iterator<String> iterator() {
        System.out.println("k");
        return null;
    }

    public static void main(String[] args) {
        IterTest a = new IterTest();
        // 不实现 Iterable 是不能用新循环的.
        for (String s : a) {
            System.out.println(s);
        }

        long aba = 0x1308c7cb94L;
        System.out.println("aba = " + aba);


        IterTest strings1 = new IterTest();
        String s2 = strings1.s1;

    }

    @Test
    public void test1() {
        long aba = 0x1308c7cb94L;
        System.out.println("aba = " + aba);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IterTest strings = (IterTest) o;
        return Objects.equal(s1, strings.s1) && Objects.equal(s2, strings.s2);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(s1, s2);
    }
}
