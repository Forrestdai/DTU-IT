<%-- 
    Document   : SignupProcess
    Created on : Nov 3, 2014, 11:37:05 AM
    Author     : asus
--%>

<%@ page import ="java.sql.*" %>
<%
    String pwd = request.getParameter("password");
    String fname = request.getParameter("firstName");
    String lname = request.getParameter("lastName");
    String email = request.getParameter("email");

    Class.forName("org.postgresql.Driver");
    Connection con = DriverManager.getConnection("jdbc:postgresql://goonhilly6.eitlab.ihk-edu.dk:5432/shicheng3e14", "root", "pass");
    Statement st = con.createStatement();
//ResultSet rs;

    if (pwd.length() >= 4 && fname.length() >= 2 && lname.length() >= 2) {
        int a = st.executeUpdate("insert into users(firstName,lastName) values ('" + fname + "' ,'" + lname + "')");
        int i = st.executeUpdate("insert into login(username,password) values ('" + fname + "','" + lname + "'," + pwd + "')");
        if (i > 0 && a > 0) {
//session.setAttribute("userid", user);
            response.sendRedirect("Login.jsp");
// out.print("Registration Successfull!"+"<a href='index.jsp'>Go to Login</a>");
        } else {
            response.sendRedirect("error.jsp");
        }
    } else {
        response.sendRedirect("error.jsp");
    }
%>
