package com.jiamingku.thead.apitools.threadloacl;

public class TestThreadLocal2 {

    public static Integer integer = new Integer(1000);

    public final String a;

    public TestThreadLocal2(String a) {
        this.a = a;
    }


    private static final ThreadLocal<Integer> value = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return integer;
        }
    };

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new MyThread(i)).start();
        }
    }

    static class MyThread implements Runnable {
        private int index;

        public MyThread(int index) {
            this.index = index;
        }

        public void run() {
            System.out.println("线程" + index + "的初始value-----------:" + value.get());
            for (int i = 0; i < 300; i++) {
                integer = value.get() + i;
            }
            System.out.println("线程" + index + "的累加value:" + value.get());
        }
    }
}