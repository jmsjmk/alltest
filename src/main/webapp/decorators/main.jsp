<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>

<html>
    <head>
        <title>My Site(main.jsp) - <decorator:title default="Welcome!" /></title>
        <decorator:head />
    </head>

    <body>
        <decorator:body />
        <p><small>(<a href="?printable=true">printable version</a>)</small></p>


        <br/>
        <br/>
        <br/>
        main.jsp
    </body>
</html>