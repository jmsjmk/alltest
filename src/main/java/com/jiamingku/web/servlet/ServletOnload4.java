package com.jiamingku.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jiamingku on 16/10/10.
 */
public class ServletOnload4 extends HttpServlet {

    @Override
    public void init() throws ServletException {

        System.out.println(this.getClass().getSimpleName() + "is excute.... end");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("postaaa");
        System.out.println(this.getClass().getSimpleName());

        String servletPath = req.getServletPath();
        String contextPath = req.getContextPath();
        String pathInfo = req.getPathInfo();
        System.out.println("servletPath:" + servletPath);
        System.out.println("contextPath:" + contextPath);
        System.out.println("pathInfo:" + pathInfo);
    }
}
