package com.jiamingku.test.temp;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class Tset {

    public volatile int a;

    public Integer a1;

    public Integer getA1() {
        return a1;
    }

    public void setA1(Integer a1) {
        this.a1 = a1;
    }

    public int getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }

    private static final long runnerOffset;

    private static final Unsafe UNSAFE;

    static {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            UNSAFE = (Unsafe) f.get(null);
            //UNSAFE = sun.misc.Unsafe.getUnsafe();
            Class<?> k = Tset.class;
            runnerOffset = UNSAFE.objectFieldOffset
                    (k.getDeclaredField("a"));

        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public void a1() {
        try {

            UNSAFE.putObjectVolatile(this, runnerOffset, 10);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void a11() {
        try {

            UNSAFE.putObjectVolatile(this, runnerOffset, 10);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a12() {
        try {
            UNSAFE.putObject(this, runnerOffset, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Tset t = new Tset();
        // 458209687
        System.out.println("t.hashCode() = " + t.hashCode());
        int ab = System.identityHashCode(t);
        System.out.println(ab);
    }


}
