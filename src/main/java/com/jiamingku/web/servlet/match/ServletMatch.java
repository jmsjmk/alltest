package com.jiamingku.web.servlet.match;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jiamingku on 16/10/10.
 */
public class ServletMatch extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println(this.getClass().getSimpleName() + "is excute....");

//        try {
//          //  Thread.sleep(5000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println(this.getClass().getSimpleName() + "is excute.... end");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        System.out.println(" match.....................= ");
//        this.getServletConfig().getInitParameter("ddd");
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("1111111111111");
    }
}
