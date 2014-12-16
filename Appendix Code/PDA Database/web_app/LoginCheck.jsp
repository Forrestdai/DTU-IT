<%-- 
    Document   : LoginCheck
    Created on : Nov 1, 2014, 1:57:34 PM
    Author     : asus
--%>

<%@ page language ="java" import="java.sql.*" %>
<%@ page import ="javax.sql.*" %>


<html>
    <head>
        
        <title>Please Wait</title>
    </head>
    <body>
	
        <%
        String user=request.getParameter("username");
        String pass=request.getParameter("password");

    Class.forName("org.postgresql.Driver");
    java.sql.Connection c = DriverManager.getConnection("jdbc:postgresql://goonhilly6.eitlab.ihk-edu.dk:5432/shicheng3e14", "root", "pass");
     Statement st=c.createStatement();
     ResultSet rs=st.executeQuery("select * from login where username='"+user+"' and password='"+pass+"'");
     if(rs.next())
         {
            session.setAttribute("username",user);
            response.sendRedirect("index.jsp");      
         }
     else{  
     out.println("Invalid user <a href=Login.jsp>Try Again!</a>");
     }
       
        %>
    </body>
</html>





