package com.jiamingku.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by jiamingku on 16/10/7.
 */
@SuppressWarnings("all")
public class Filter3 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(Filter3.class.getName() + ".init...");
    }

    @Override
    public void doFilter(ServletRequest request1, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) request1;

        System.out.println("this. 第二个filter过滤器.....");
        /**
         * 这个将请求丢到后面的机器去了.
         */
        chain.doFilter(request, response);


        System.out.println("this. 第二个filter过滤器.....");
    }

    @Override
    public void destroy() {
        System.out.println("Filter1 destroy.");
    }
}
