//package com.jiamingku.web.cookie;
//
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.IncorrectCredentialsException;
//import org.apache.shiro.authc.UnknownAccountException;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.subject.Subject;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * Created by jiamingku on 2020/5/24.
// */
//@SuppressWarnings("all")
//@WebServlet(name = "coo", urlPatterns = "/coo")
//public class CookieServerDomain extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
//            throws ServletException, IOException {
//
//        // ---添加cookie
//        // ----  trackinfo.setDomain( ".90sf.com");  如果不是这个域名的话---直接报错500-域名不能乱写
//        Cookie trackinfo1 = new Cookie("aid", "121313");
//        trackinfo1.setPath("/");
//        resp.addCookie(trackinfo1);
//
//        trackinfo1.setDomain(".baidu.com");
//
//        // ----获取
//        Cookie[] cookies = req.getCookies();
//        int length = cookies.length;
//        for (int i = 0; i < length; i++) {
//            String cname = cookies[i].getName();
//            String cValue = cookies[i].getValue();
//            System.out.println("cname:[" + cname + "] cValue:[" + cValue + "]");
//        }
//
//        // ---添加cookie
//
//        /**
//         cookie.setDomain("value");这个value是不能乱写的，只能放在合法的域名下，低版本的我记得以前报异常了-高版本的现在没这个限制了
//         trackinfo.setDomain( "helloworl.com" );这个东西你不能乱写，你在设置的时候如果不是访问的域名是不可以设置的。
//
//         其他的都不可能设置。如果你域名是80.90sf.com 这样的域名,你设置域名可以设置：
//         90sf.com
//         .90sf.com
//         80.90sf.com
//         .80.90sf.com
//         */
//        Cookie trackinfo = new Cookie("aid1", "121313");
//        trackinfo.setDomain("卡普");
//        trackinfo.setPath("/");
//        resp.addCookie(trackinfo);
//
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
//            throws ServletException, IOException {
//        String error = null;
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//        try {
//            subject.login(token);
//        } catch (UnknownAccountException e) {
//            error = "用户名/密码错误";
//        } catch (IncorrectCredentialsException e) {
//            error = "用户名/密码错误";
//        } catch (AuthenticationException e) {
//            //其他错误，比如锁定，如果想单独处理请单独catch处理
//            error = "其他错误：" + e.getMessage();
//        }
//        if (error != null) {//出错了，返回登录页面
//            req.setAttribute("error", error);
//            req.getRequestDispatcher("/login.jsp").forward(req, resp);
//        } else {//登录成功
//            req.getRequestDispatcher("/loginSuccess.jsp").forward(req, resp);
//        }
//    }
//}