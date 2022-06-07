package com.jiamingku.lambda.sgg.com.atguigu.java8;

import org.junit.Test;

import java.util.Objects;

/**
 * Created by jiamingku on 2020/7/22.
 */
public class ObjectsTest {

    @Test
    public void t1() {
        boolean b = Objects.isNull(null);
        System.out.println("b = " + b);

        b = Objects.nonNull(null);
        System.out.println("b = " + b);
        Object o = null;
    }

    @Test
    public void t2() {
        Objects.requireNonNull(null);
    }
}
