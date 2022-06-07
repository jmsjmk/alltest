package com.jiamingku.web.servlet.dispatch;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jiamingku on 16/10/7.
 */
public class ServletTestHttpRequestMethod extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("request.getPathInfo() = " + request.getPathInfo());
        request.getRequestDispatcher("/tty/22.jsp").forward(request, response);


        System.out.println("request.getPathInfo() = " + request.getPathInfo());

    }
}
