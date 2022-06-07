package com.jiamingku.web.servlet;

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
        /**
         *
         request.getPathInfo();这个方法返回请求的实际URL相对于-----请求的servlet的url的路径。（个人理解）
         有一个Servlet的映射是这样配置的：

         <servlet-mapping>
         <servlet-name>TestServlet</servlet-name>
         <url-pattern>/tty/*</url-pattern>
         </servlet-mapping>

         我只要访问：http://localhost:8080/aa/tty/testewsfsf(tty后面的字符串任意)
         例如访问：http://localhost:8080/aa/tty/joejoe1991/a.html

         这个实际的URL，相对于那个servlet 的url （"/tty/*"）的路径是：/joejoe1991/a.html
         所以 request.getPathInfo() 方法返回的就是："/joejoe1991/a.html"
         (注意如果存在filter与servlet并存的情况下,如果servlet不命中这个值是获取不到的)

         如果你的URL里有查询字符串，getPathInfo() 方法并不返回这些查询字符串。

         例如：

         http://localhost:8080/aa/tty/joejoe1991/a.html?name=test

         getPathInfo() 返回的仍然是：

         "/joejoe1991/a.html" ，而并不包括后面的"?name=test"（感觉有点想reset风格的）
         我们可以利用这个方法去做类似于多用户博客系统的那种URL。

         都是http://www.xxx.com/blog/ 开头
         后面跟的是用户名，
         比如我要访问joejoe1991的博客：

         http://www.xxx.com/blog/joejoe1991

         这个joejoe1991并不是一个真实存在的目录。

         建一个servlet，配置路径为：/blog/*

         然后在这个servlet里调用request.getPathInfo() 方法。

         比如：http://www.xxx.com/blog/jjx

         那request.getPathInfo() 方法返回的就是jjx ，表示要访问jjx的博客。

         这时再去数据库里查相应的数据就好。

         pathInfo 是必须匹配上之后才能显示出来---一般对于通配符的情况来说是比较有用

         */
        System.out.println("request.getPathInfo() = " + request.getPathInfo());

    }
}
