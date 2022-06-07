package com.jiamingku.jvm.classloder;

/**
 * Created by jiamingku on 2020/3/18.
 */
public class FieldResolution {
    interface interface0 {
        int A = 0;
    }

    interface Interface1 extends interface0 {
        int A = 1;
    }

    interface Interface2 {
        int A = 2;
    }

    static class P implements Interface1 {
        static {
            System.out.println(" p init ");
        }

        public static int A = 11;
    }

    static class S extends P implements Interface2 {
        static {
            System.out.println(" s init ");
        }

        public static int A = 4;
    }

    public static void main(String[] args) {
        System.out.println("S.A = " + S.A);
    }
}
