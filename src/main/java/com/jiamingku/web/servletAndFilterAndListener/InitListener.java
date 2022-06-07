package com.jiamingku.web.servletAndFilterAndListener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println(this.getClass().getName() + " init...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println(this.getClass().getName() + " destroy...");
    }
}