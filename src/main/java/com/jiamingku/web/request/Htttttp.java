package com.jiamingku.web.request;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * Created by jiamingku on 2020/7/21.
 */
@SuppressWarnings("all")
public class Htttttp extends HttpServlet {

    /**
     *
     *  http请求 --请求行---请求首部---请求体
     *
     *  cookie header 内容都是在请求首部中=对应的api后去到了就可以了
     *
     *
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(" ========获取请求头 ");
        Enumeration<String> enumeration = req.getHeaderNames();

        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String valeu = req.getHeader(name);
            System.out.println("name =  value |" + name + "\t" + valeu);

        }
        System.out.println(" ====================================== ");

        Cookie[] co = req.getCookies();
        Arrays.stream(co).forEach(e -> {
            System.out.println("e.getValue() = " + e.getValue());
            System.out.println("e.getName() = " + e.getName());
            System.out.println("e.getPath() = " + e.getPath());
            System.out.println("e.getDomain() = " + e.getDomain());
            System.out.println("e.getVersion() = " + e.getVersion());
        });

        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(" ========获取请求头 ");
        Enumeration<String> enumeration = req.getHeaderNames();

        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String valeu = req.getHeader(name);
            System.out.println("name =  value |" + name + "\t" + valeu);

        }
        System.out.println(" ====================================== ");

        Cookie[] co = req.getCookies();
        Arrays.stream(co).forEach(e -> {
            System.out.println("e.getValue() = " + e.getValue());
            System.out.println("e.getName() = " + e.getName());
            System.out.println("e.getPath() = " + e.getPath());
            System.out.println("e.getDomain() = " + e.getDomain());
            System.out.println("e.getVersion() = " + e.getVersion());
        });

        super.doGet(req, resp);
    }
}
