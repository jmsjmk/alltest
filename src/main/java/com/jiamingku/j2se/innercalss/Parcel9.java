package com.jiamingku.j2se.innercalss;

import java.util.Objects;

public class Parcel9 {
    private String tt = "dd";
//    public Destination dest(final String dest, final float price) {
//        return new Destination(12) {
//            private int cost;
//            private String label = dest;
//
//            public String readLabel() {
//                tt = "1222";
//                return label;
//            }
//
//            @Override
//            public void a() {
//                super.a();
//            }
//        };
//    }

    public Destination dest1( final String dest, final float price) {
        return new Destination(12) {
            private int cost;
            private String label = dest;

            public String readLabel() {
                tt = "1222";
                return label;
            }

            @Override
            public void a() {
                super.a();
            }
        };
    }

    class K {
        public String name;
        private String privateString;

        private void d() {
            System.out.println("name = " + name);
        }

    }

    public void k() {
        K k = new K();
        k.d();
        k.privateString = 33 + "";
        System.out.println("k.privateString = " + k.privateString);
    }

    public static void main(String[] args) {
        Parcel9 p = new Parcel9();
//        Destination d = p.dest("Tanzania", 101.395F);
//        d.readLabel();
        System.out.println("p.tt = " + p.tt);
    }
}
