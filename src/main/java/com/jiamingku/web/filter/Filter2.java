package com.jiamingku.web.filter;

import javax.servlet.*;
import java.io.IOException;


/**
 * 1。filter 开启异步支持AsyncContext ctx = request.startAsync();  开起来了。整个访问链条都应该开启异步支持
 *
 * 2。 并且链条在执行的时候，后面的 必须都的支持异步/  eg
 *   f1 -->  f2 ---->servlet
 *    f1 开启异步 ， f2 , servlet 必须开启
 *    f1 不开启 ----->f2 开启 ---- sevlet必须开启。(如果你使用异步的情况下)
 */

/**
 * Created by jiamingku on 16/10/7.
 */
public class Filter2 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(Filter2.class.getName() + ".init...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("F2 invoken before");
        chain.doFilter(request, response);

//        AsyncContext ctx = request.startAsync();

        System.out.println("F2 invoken after");
    }

    @Override
    public void destroy() {
        System.out.println("Filter2 destroy. ");
    }
}
