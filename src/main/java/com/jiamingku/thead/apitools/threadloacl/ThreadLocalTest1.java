package com.jiamingku.thead.apitools.threadloacl;

/**
 * Created by jiamingku on 16/9/19.
 */
public class ThreadLocalTest1 extends Thread {
    Account account ;

    public ThreadLocalTest1(Account account) {
        this.account = account;
    }

    public void run() {
        System.out.println("ThreadLocalTest1:" + account.hashCode());
        for (int i = 0; i < 10; i++) {
            if (i == 5) {
                account.setName("线程b ");
            }
            System.out.println(account.getName() + "...." + i+ account.getStu());
        }
    }

    public Account getAccount() {
        return account;
    }
}
