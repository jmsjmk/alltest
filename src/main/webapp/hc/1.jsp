<%@ page import="java.util.Map" %>
<%@ page import="java.util.Enumeration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<%
    /**
    response.addHeader("ddd", "ddd");
    Map<String, String[]> map = request.getParameterMap();
    for (Map.Entry<String, String[]> e : map.entrySet()) {
        String key  = e.getKey();
        String[] arrgy = e.getValue();
        System.out.println(key + "\t" + arrgy);
    }
    String[] a = {"33"};
    // map.put("t1",a);
    System.out.println(request.getParameter("t1"));
    System.out.println(request.getClass());

    */


    Enumeration<String> headerKey = request.getHeaderNames();
    String key = null;
    while (headerKey.hasMoreElements()) {
        key = headerKey.nextElement();
        if (key != null)
            System.out.println(key + ":" + request.getHeader(key));
    }

    System.out.println("result::::" + request.getHeader("ddsd"));

%>
<head>
    <title></title>
</head>
<body>
hello
</body>
</html>