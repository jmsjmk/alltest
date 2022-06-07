<%@page import="java.net.URLDecoder,java.util.*"%>
<%
    List<String> keys = new ArrayList<String>();
    keys.add("<");
    keys.add(">");
    keys.add("select");
    //keys.add("update");
    keys.add("delete");
    keys.add("from");
    keys.add("where");
    keys.add("script");
    keys.add("mouseover");
    keys.add("alert");
    keys.add("onclick");
    keys.add("ondbclick");
    keys.add("onmousedown");
    keys.add("onmouseup");
    keys.add("onmouseover");
    keys.add("onmousemove");
    keys.add("onmouseout");
    keys.add("onkeypress");
    keys.add("onkeydown");
    keys.add("onkeyup");
    keys.add(" and ");
    keys.add(" or ");
    keys.add("%0d%0a");
    keys.add("%0a%20");
    keys.add("%0d%0a%20");


    String method = request.getMethod();
    if (method.equals("POST")) {
        Enumeration   enus=request.getParameterNames();
        while(enus.hasMoreElements()){
            String   paraName=(String)enus.nextElement();
            String paraNameValue = request.getParameter(paraName);
            for (String key : keys) {
                if (paraNameValue.contains(key)) {
                    response.setStatus(500);
                    return;
                }
            }
        }
    } else {
        String jqString = request.getQueryString() == null ? "":request.getQueryString();
        String exString = request.getRequestURI() == null ? "":request.getRequestURI();
        String jqCodeStr = URLDecoder.decode(jqString).toLowerCase()
                + URLDecoder.decode(exString).toLowerCase();
        jqString = jqString.toLowerCase() + exString.toLowerCase();
        for (String key : keys) {
            if (jqString.contains(key) || jqCodeStr.contains(key)) {
                response.setStatus(500);
                return;
            }
        }
    }
%>