package com.jiamingku.web.servletAndFilterAndListener;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * Created by zhanpeng on 14-12-29.
 */
public class InitSerlvetTestStop extends HttpServlet {

    //@Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println(this.getClass().getName() + "init...");

        while (true) {
            System.out.println(" 用一个servlert进行测试关闭" );
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //@Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println(this.getClass().getName() + " service...");

        res.getWriter().println(this.getClass().getName() + " running...");
    }

    //@Override
    public void destroy() {
        System.out.println(this.getClass().getName() + " destroy...");
    }
}