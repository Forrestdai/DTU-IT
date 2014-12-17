

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Log out</title>
    </head>
    <body>
        <%

        session.removeAttribute("username");
        session.removeAttribute("password");
        session.invalidate();
		response.sendRedirect("index.jsp");
        %>
        
        
    </body>
</html>

