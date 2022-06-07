package com.jiamingku.thead.base;

/**
 * Created by jiamingku on 2019/9/12.
 * 这个方法你可以放弃了，认为没有这个方法，关闭线程关闭的太彻底.可以忘记这个方法
 */
public class ThreadStop extends Thread {

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("true = " + true);
                try {
                    Thread.sleep(10L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            System.out.println("stop 立刻就停止了");
        }
    }

    public static void main(String[] args) {
        ThreadStop t = new ThreadStop();
        t.start();
        try {
            Thread.sleep(1000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("主线成要运行完成了");
        // 不发生异常不过线程立马就停止了，停止的很测地
        t.stop();
    }
}
