package com.jiamingku.collection.guava;

import com.alibaba.druid.sql.visitor.SQLASTOutputVisitor;
import com.google.common.collect.BoundType;
import com.google.common.collect.Range;
import com.google.common.primitives.Ints;
import org.junit.Test;

public class GuavaTest {

    @Test
    public void testRange() {
        System.out.println("open:" + Range.open(1, 10));
        System.out.println("closed:" + Range.closed(1, 10));
        System.out.println("closedOpen:" + Range.closedOpen(1, 10));
        System.out.println("openClosed:" + Range.openClosed(1, 10));
        System.out.println("greaterThan:" + Range.greaterThan(10));
        System.out.println("atLeast:" + Range.atLeast(10));
        System.out.println("lessThan:" + Range.lessThan(10));
        System.out.println("atMost:" + Range.atMost(10));
        System.out.println("all:" + Range.all());
        System.out.println("closed:" + Range.closed(10, 10));
        System.out.println("closedOpen:" + Range.closedOpen(10, 10));
        //会抛出异常
        System.out.println("open:" + Range.open(10, 10));
    }

    public static void main(String[] args) {
        char a = 97;
        System.out.println("a = " + a);

        float ab = 3;
        System.out.println("ab = " + ab);
        float c = 300.1117112f;
        System.out.println("c = " + c);
        float d = 3.1117114555555f;
        System.out.println("d = " + d);

        System.out.println("Float.MAX_VALUE = " + Float.MAX_VALUE);

        if (Long.MAX_VALUE > Float.MAX_VALUE) {
            System.out.println("yes");
        } else {
            System.out.println("flase");
        }
    }

    @Test
    public void testRange1() {
        System.out.println("downTo:" + Range.downTo(4, BoundType.OPEN));
        System.out.println("upTo:" + Range.upTo(4, BoundType.CLOSED));
        System.out.println("range:" + Range.range(1, BoundType.CLOSED, 4, BoundType.OPEN));
    }

    @Test
    public void testContains() {
        System.out.println(Range.closed(1, 3).contains(2));
        System.out.println(Range.closed(1, 3).contains(4));
        System.out.println(Range.lessThan(5).contains(5));
        System.out.println(Range.closed(1, 4).containsAll(Ints.asList(1, 2, 3)));
    }

    @Test
    public void testContains1() {
        System.out.println(Range.range(1, BoundType.CLOSED, 3, BoundType.CLOSED).contains(3));
        System.out.println(Range.range(1, BoundType.CLOSED, 3, BoundType.CLOSED).contains(1));
        System.out.println(Range.range(1, BoundType.CLOSED, 3, BoundType.CLOSED).contains(4));
        System.out.println(Range.range(1, BoundType.CLOSED, 3, BoundType.CLOSED).contains(10));

        String s = "{\"activityFloorAmount\":100,\"activityMaxNum\":30000,\"activityType\":1,\"dateExpression\":\"2020-04-17,2020-05-31\",\"dateExpressionType\":1,\"discountRadio\":0.15,\"perUserMaxGiftAmount\":300,\"perUserMaxNum\":5,\"userActivityUnit\":1}";
        String[] aa = s.split("#");
        System.out.println("aa.length = " + aa.length);
    }
}
