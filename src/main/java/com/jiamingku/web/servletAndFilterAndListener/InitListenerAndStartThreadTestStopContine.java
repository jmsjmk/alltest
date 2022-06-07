package com.jiamingku.web.servletAndFilterAndListener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitListenerAndStartThreadTestStopContine implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println(this.getClass().getName() + " init...");


        Thread t = new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(" 监听器启动了一个线程================= 关闭容易看看那能关闭掉她么");
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println(this.getClass().getName() + " destroy...");
    }
}
