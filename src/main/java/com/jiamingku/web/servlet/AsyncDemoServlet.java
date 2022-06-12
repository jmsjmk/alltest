//package com.jiamingku.web.servlet;
//
//import javax.servlet.AsyncContext;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.Date;
//
///**
// * 异步：当前servlet 如果支持异步，===== 前面的filter 必须支持异步
// *     A filter or servlet of the current chain does not support asynchronous operations.
// *
// *         <filter>
// <filter-name>iniShiroFilter</filter-name>
// <filter-class>org.apache.shiro.web.servlet.IniShiroFilter</filter-class>
// <init-param>
// <param-name>configPath</param-name>
// <param-value>classpath:shiro.ini</param-value>
// </init-param>
//
// </filter>
// <filter-mapping>
// <filter-name>iniShiroFilter</filter-name>
// <url-pattern>/*</url-pattern>
// </filter-mapping>
//
//
//
// ======================================
// 但是filter 如果设置成为异步。。不需要限制了。
//
//
// *
// *
// */
//@WebServlet(urlPatterns = "/demo", asyncSupported = true)
//public class AsyncDemoServlet extends HttpServlet {
//
//    @Override
//
//    public void doGet(HttpServletRequest req, HttpServletResponse resp)
//
//            throws IOException, ServletException {
//
//        resp.setContentType("text/html;charset=UTF-8");
//
//        PrintWriter out = resp.getWriter();
//
//        out.println("进入Servlet的时间：" + new Date() + ".");
//
//        out.flush();
//
//
//        //在子线程中执行业务调用，并由其负责输出响应，主线程退出
//
//        AsyncContext ctx = req.startAsync();
//
//        new Thread(new Executor(ctx)).start();
//
//
//        out.println("结束Servlet的时间：" + new Date() + "." + "Threadid:"  + Thread.currentThread().getId());
//
//        out.flush();
//
//    }
//
//}
//
//
//class Executor implements Runnable {
//
//    private AsyncContext ctx = null;
//
//    public Executor(AsyncContext ctx) {
//
//        this.ctx = ctx;
//
//    }
//
//
//    public void run() {
//
//        try {
//
//            //等待十秒钟，以模拟业务方法的执行
//
//            Thread.sleep(10000);
//
//            PrintWriter out = ctx.getResponse().getWriter();
//
//            out.println("业务处理完毕的时间：" + new Date() + "." + "ThreadId:" + Thread.currentThread().getId());
//
//            out.flush();
//
//            ctx.complete();
//            System.out.println(" ===================================== " );
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//
//        }
//
//    }
//
//}