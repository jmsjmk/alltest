package com.jiamingku.web.servletAndFilterAndListener;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class InitFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(this.getClass().getName() + " init...");
    }
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println(this.getClass().getName() + " doFilter...");
 
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        System.out.println("URI="+httpServletRequest.getRequestURI());
        chain.doFilter(request, response);
    }
 
    @Override
    public void destroy() {
        System.out.println(this.getClass().getName() + " destroy...");
    }
}