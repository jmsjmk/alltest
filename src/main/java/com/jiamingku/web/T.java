package com.jiamingku.web;

public class T {

    int x = 0;

    public void test() {
        if (x == 0) {
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }

    public static void main(String[] args) {

        int a = 0B11100000000000000000000000000001;
        int b = 0B11111111111111111111111111111111;
        System.out.println(a);
        System.out.println(b);
        System.out.println("d");
    }

    static class lock {

        public synchronized void a() {
            while (true) {
                try {
                    System.out.println("准备wait();");
                    this.wait();
                } catch (Exception e) {
                    System.out.println(" interrupt--导致 ");
                }
            }
        }


        public synchronized void b() {
            boolean b = true;
            while (b) {
                try {
                    System.out.println(" 一个线程直接把持锁头不释放 ");
                    Thread.sleep(5000);
                    b =false;
                } catch (Exception e) {
                    System.out.println(" =--bbbbbbbbbbbbbbbbb ");
                }
            }
        }
    }
}