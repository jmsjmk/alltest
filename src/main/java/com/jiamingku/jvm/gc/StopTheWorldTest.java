package com.jiamingku.jvm.gc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class StopTheWorldTest {

    public static class Wt extends Thread {
        List<byte[]> list = new ArrayList<>();

        @Override
        public void run() {
            try {
                while (true) {

                    for (int i = 0; i < 1000; i++) {
                        byte[] bytes = new byte[1024];
                        list.add(bytes);
                    }

                    if (list.size() > 10000) {
                        list.clear();
//                        System.out.println("-gc----");
                        System.gc();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static class Pt extends Thread {
        public final long strartTime = System.currentTimeMillis();

        @Override
        public void run() {
            try {
                while (true) {
                    long t = System.currentTimeMillis() - strartTime;
                    BigDecimal b = new BigDecimal(String.valueOf(t));
                    String s = b.divide(new BigDecimal("1000"),1, RoundingMode.HALF_UP).toString();
                    System.out.println(s);
//                    System.out.println(t + "\t" + t / 1000 + "." + t % 1000);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Pt pt = new Pt();
        pt.start();


        Wt wt = new Wt();
        wt.start();


    }
}
