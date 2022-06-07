<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<%@page import="java.util.*" %>
<%
    // ?????cookie?????????
    String sessionId = request.getSession().getId();
    System.out.println("----sessionId:" + sessionId);
    request.getSession().setAttribute("d", "dd");
    System.out.println("--------333.jsp --------");
    if (false) {
        request.getRequestDispatcher("444.jsp").forward(request, response);
    } else {
        // response.sendRedirect("444.jsp");
        String a = response.encodeURL("444.jsp");
        System.out.println("a = " + a);
        response.sendRedirect(response.encodeURL("444.jsp"));

    }
%>