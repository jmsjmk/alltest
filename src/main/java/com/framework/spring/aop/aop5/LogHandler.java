package com.framework.spring.aop.aop5;

public class LogHandler {
    public void LogBefore() {
        System.out.println("拦截器[LogHandler] LogBefore");
    }

    public void LogAfter() {
        System.out.println("拦截器[LogHandler] LogAfter");
    }
}