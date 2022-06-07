package com.jiamingku.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by jiamingku on 16/10/7.
 */
public class Filter1 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(Filter1.class.getName() + ".init...");
        System.out.println("filterConfig.getClass().getName() = " + filterConfig.getClass().getName());
        System.out.println("filterConfig.getServletContext().getClass() = " + filterConfig.getServletContext().getClass());
        String filterName = filterConfig.getFilterName();
        System.out.println("filterName = " + filterName);

        String value = filterConfig.getInitParameter("test");
        System.out.println("value = " + value);
    }

    @Override
    public void doFilter(ServletRequest request1, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) request1;
        System.out.println(" F1 ========================================before" );
        System.out.println("servlet. excute....");
        /**
         * 1.===出现如下结果
         * request.getRemoteAddr():    0:0:0:0:0:0:0:1
         * /etc/host  ::1 localhost ---目的将localhost映射为了ipv6格式的本机ip地址：
         * 解决方法：
         * 1.1通过ip访问
         * 1.2注释掉这个请求
         * 2.===
         */
        // ----获取客户端的的ip地址
        System.out.println("request.getRemoteAddr():    " + request.getRemoteAddr());
        System.out.println("request.getRemoteHost():    " + request.getRemoteHost());


        //----获取项目的项目名称
        System.out.println("request.getContextPath() = " + request.getContextPath());
        //---- 协议
        System.out.println("request.getScheme() = " + request.getScheme());
        //---- 服务的的请求地址
        // http://localhost:8080/aa/     ---- loalhost
        // http://127.0.0.1:8080/aa/     ---- 127.0.0.1
        // http://192.168.22.59:8080/aa/ ---- 192.168.22.59
        // 同样通过域名去获取，结果就是域名
        System.out.println("request.getServerName() = " + request.getServerName());
        //---- 服务的端口号
        System.out.println("request.getServerPort() = " + request.getServerPort());

        // -----------------------------------------------jsp获取路径的代码就如上--------------------------------------
        System.out.println(" -------------------------------------------------------------------------------- ");
        System.out.println("request.getRequestURL().toString():" + request.getRequestURL().toString());
        System.out.println("");
        System.out.println("request.getRequestURI():" + request.getRequestURI());
        System.out.println("");
        /**
         * 这方很怪--只能获取匹配的路径，但是filter是可以获取的
         * https://blog.csdn.net/qq_27770257/article/details/79438987，拦截器是可以的
         *
         */
        System.out.println("request.getServletPath() = " + request.getServletPath());

        System.out.println("");

        System.out.println(" request.getRemotePort():" + request.getRemotePort());
        System.out.println(" request.getContextPath():" + request.getContextPath());
        System.out.println(" request.getScheme():" + request.getScheme());


        // -------------------------获取项目部署的真实路径
        String path = request.getServletContext().getRealPath("/download");
        System.out.println("path = " + path);
        // ----------但是此方法已经过期，就用servletContext去获取项目的真实地址
        String realPath = request.getRealPath("/");
        System.out.println("realPath = " + realPath);

        /**
         * 这个方法有点意识：
         */
        String pathInfo = request.getPathInfo();
        System.out.println("pathInfo = " + pathInfo);
        /**
         * 这个将请求丢到后面的机器去了.
         */
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("Filter1 destroy.");
    }
}
