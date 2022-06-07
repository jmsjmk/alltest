package com.jiamingku.thead.apitools.threadloacl;

/**
 * Created by jiamingku on 16/9/19.
 */
public class ThreadLocalTest extends Thread {
    Account account;

    public ThreadLocalTest(Account account) {
        this.account = account;
    }

    public void run() {
        System.out.println("threadLocalTest:" + account.hashCode());
        for (int i = 0; i < 10; i++) {
            if (i == 5) {
                account.setName("线程A ");
            }
            System.out.println(account.getName() + "...." + i + account.getStu());
        }
    }

    public Account getAccount() {
        return account;
    }

    public static void main(String[] args) throws InterruptedException {

        Account at = new Account("init");
        Student stu = new Student();
        stu.setAge("19");
        stu.setName("mbl");
        at.setStu(new Student());
        System.out.println("mainThread" + at.hashCode());
        ThreadLocalTest tt1 = new ThreadLocalTest(at);
        tt1.start();
        Thread.sleep(1000L);
        System.out.println("================");
        ThreadLocalTest1 tt2 = new ThreadLocalTest1(at);
        tt2.start();
        Thread.sleep(1000L);
        System.out.println("################");

        if (at == tt1.getAccount()) {
            System.out.println("is equal tt1");
        }
        if (at == tt2.getAccount()) {
            System.out.println("is equal tt2");
        }
    }
}
