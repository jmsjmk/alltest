package com.jiamingku.thead.apitools;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * <p>
 * AtomicBoolean
 * AtomicInteger
 * AtomicLong
 * ====================================
 * 初始值:
 * <p>
 * 使用方式一样.具体的看如何使用,就是无锁编程.并发的操作.
 */
public class AtomicIntegerDemo {

    @Test
    public void test1() {
        AtomicInteger atomicInteger = new AtomicInteger(10);
        atomicInteger.lazySet(100);
        AtomicLong atomicLong = null;
    }

    public static void main(String[] args) {
        AtomicInteger ai = new AtomicInteger(0);
        int i1 = ai.get();
        v(i1);
        int i2 = ai.getAndSet(5);
        v(i2);
        int i3 = ai.get();
        v(i3);
        int i4 = ai.getAndIncrement();
        v(i4);
        v(ai.get());
        // 增加-->获得
        int i33 = ai.addAndGet(100);
        v(i33);
        // false 比较有人修改
        // true 比较原子操作成功
        boolean b = ai.compareAndSet(107, 108);
        System.out.println("b = " + b);
        ai.lazySet(100);
        System.out.println("(-1 ^ 1 = " + (-2 & 1));
        int bbb = ~-2;
        System.out.println("bbb = " + bbb);
        System.out.println("Integer.toBinaryString(bbb) = " + Integer.toBinaryString(bbb));
    }

    static void v(int i) {
        System.out.println("i : " + i);
    }

    public void test() {
        java.util.concurrent.atomic.LongAdder longAdder = new LongAdder();
    }
}
