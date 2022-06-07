package com.jiamingku.web.request;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 常用的httprequest方法
 * Created by jiamingku on 2018/12/12.
 */
@SuppressWarnings("all")
public class HttpReqMethods extends HttpServlet {

    /**
     * http请求 --请求行---请求首部---请求体
     * <p>
     * cookie header 内容都是在请求首部中=对应的api后去到了就可以了
     * <p>
     * <a>http://localhost:8080/what/api/sdfsdfs?name=123&name=3333&name=678&age=666</a>
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("anme");
        System.out.println("name = " + name);


        String strs = req.getQueryString();
        System.out.println("strs = " + strs);



        super.doGet(req, resp);
    }

    /**
     * <a>http://localhost:8080/what/api/sdfsdfs?name=123&anme=3344433&name=678&age=666</a>
     *
     * 虽然是post 请求，你在url中传递的参数，也是可以获取到的。 其实道理很简单啊，最终都是要变化成为java对象么
     *
     * 如果请求中有相同的变量名字：会取第一个变量名的值
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(" post method is invoke " );
        String name = req.getParameter("anme");
        System.out.println("name = " + name);


        String strs = req.getQueryString();
        System.out.println("strs = " + strs);



        super.doGet(req, resp);
    }
}
