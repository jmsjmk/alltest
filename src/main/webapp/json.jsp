<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.alibaba.fastjson.*" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%
    String servletPath = request.getServletPath();
    String contextPath = request.getContextPath();

    String pathInfo = request.getPathInfo();

    System.out.println("servletPath:" + servletPath);
    System.out.println("contextPath:" + contextPath);
    System.out.println("pathInfo:" + pathInfo);

//    out.println("dd");
%>