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
public class ServletFormData extends HttpServlet {
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
         *  context-type = multipart/form-data
         *
         *  这种方式api肯定是读取不到的了, 只能用流去读取
         */
        String s = req.getParameter("p");
        System.out.println("s = " + s);


        /**
         * 用下面的方法可以接收到
         */


        BufferedReader br = new BufferedReader(new InputStreamReader(
                (ServletInputStream) req.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
//        @SuppressWarnings("rawtypes")
//        Map map = (Map)JSONObject.fromObject(sb.toString());

        System.out.println("sb.toString() = " + sb.toString());
//        String str = map.get("uuid").toString();

    }
}
