package com.jiamingku.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jiamingku on 16/10/10.
 */
public class ServletOnload2 extends HttpServlet {

    @Override
    public void init() throws ServletException {
        String name = this.getServletConfig().getInitParameter("name");
        String age = this.getServletConfig().getInitParameter("age");
        System.out.println(name);
        System.out.println(age);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("get method is excute:");
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(date);
        System.out.println("init..........." +dateStr);
    }
}
