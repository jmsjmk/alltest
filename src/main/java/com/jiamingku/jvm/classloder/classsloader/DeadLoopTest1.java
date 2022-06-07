package com.jiamingku.jvm.classloder.classsloader;

/**
 * 类加载器死锁：但是状态是runable。跟线程的稍微有点区别
 * <p>
 * 类是按需加载,并且并且线程安全的
 */
public class DeadLoopTest1 {

    static class A {
        static {
            System.out.println(Thread.currentThread().getId() + " a ");
            new B();
        }
    }

    static class B {
        static {
            System.out.println(Thread.currentThread().getId() + " B ");
            new A();
        }
    }

    public static void main(String[] args) {
        Thread thread2 = new Thread(() -> new A());
        thread2.start();
//        try {
//            Thread.sleep(1000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        Thread thread1 = new Thread(() -> new B());

        thread1.start();
    }
}
