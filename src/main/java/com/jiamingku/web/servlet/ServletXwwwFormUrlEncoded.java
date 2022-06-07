package com.jiamingku.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by jiamingku on 16/10/7.
 */
@SuppressWarnings("all")
public class ServletXwwwFormUrlEncoded extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(" get 方法被执行= ");
        this.doPost(req, resp);
    }


    /**
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("servlet. post method .excute....");

        /**
         * 流读取完成之后,api就读取不到了
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(
                (ServletInputStream) req.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        System.out.println("sb.toString() = " + sb.toString());

        /**
         *  context-type = application/x-www-form-urlencoded 下面是可以读取到的
         *  但是读取方式只能一种。如果用api去读取的话，流读取不到了
         */
        String s = req.getParameter("p");
        System.out.println("s = " + s);

        s = req.getParameter("testkey");
        System.out.println("s = " + s);
    }
}
