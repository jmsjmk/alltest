<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<%@page import="java.util.*" %>
<%
    String json = "{intData:0,str:'aaa'}";
    // System.out.println(request.getParameter("test"));

    String v = request.getParameter("test");
    System.out.println("============request Header===========================");
    Enumeration<String> es = request.getHeaderNames();

    while (es.hasMoreElements()) {
        String key = es.nextElement();
        String val = request.getHeader(key);
        System.out.println(key + ":" + val);
    }
    Collection<String> collection = response.getHeaderNames();
    System.out.println("==============response Header ========================");
    System.out.println(collection.size());
    for (String s : collection) {
        System.out.println("key:" + s + ":" + response.getHeader(s));
    }
    if (null != v) {
        String vv = new String(v.getBytes("iso8859-1"), "utf-8");
        System.out.println(vv);
    }

    out.println(json);

%>