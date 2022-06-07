<%@ page language="java" contentType="application/json;charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%
    String sessionId = session.getId();
    System.out.println("444jsp====sessionId = " + sessionId);
    String v = (String)session.getAttribute("d");
	System.out.println(v);
%>

