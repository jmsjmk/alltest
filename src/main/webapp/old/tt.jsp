<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%
	String sessionId = request.getSession().getId();	
    System.out.println("sessionId:" + sessionId);
    request.getSession().setAttribute("d", "dd3333333333333333333333");
    System.out.println("d:" +  request.getSession().getAttribute("d"));
    System.out.println("--------tt.jsp--------");
%>