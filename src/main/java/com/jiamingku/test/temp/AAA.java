package com.jiamingku.test.temp;

import com.google.common.base.Objects;

import java.lang.reflect.Constructor;

public class AAA {
    public static void main(String[] args) {
        String a = "100";
        T t = new T(10+"");
        t1 t1 = new t1(t);

        System.out.println("t1.getT() = " + t1.getT());

        t.setA("ddddddddddddddddddddddddddd");

        System.out.println("t1.getT() = " + t1.getT());

    }

    static class T {
        private String a;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public T(String a) {
            this.a = a;
        }

        @Override
        public String toString() {
            return Objects.toStringHelper(this)
                    .add("a", a)
                    .toString();
        }
    }

    static class t1 {
        private T t;

        public T getT() {
            return t;
        }

        public void setT(T t) {
            this.t = t;
        }

        public t1(T t) {
            this.t = t;
        }
    }
}
