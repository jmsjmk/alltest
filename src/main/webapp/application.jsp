<%--
  Created by IntelliJ IDEA.
  User: jiamingku
  Date: 2020/7/21
  Time: 8:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--通过request对象可以得到application--%>
<%--request.getSession().getServletContext()；--%>
<%--ServletContext.getRequestDispatcher(String url)中的url只能使用绝对路径；--%>
<%--而ServletRequest.getRequestDispatcher(String url)中的url可以使用相对路径。--%>
<%--因为ServletRequest具有相对路径的概念；而ServletContext对象无此概念。--%>


<%
    System.out.println(" ============================ " );
    //转向到当前页面加后缀_temp的页面
    String url = request.getRequestURI();
    String newUrl = url.replaceFirst("\\.jsp", "_temp.jsp");
    RequestDispatcher dispatcher = application.getRequestDispatcher("/22.jsp");
    dispatcher.forward(request, response);

%>

</body>
</html>
