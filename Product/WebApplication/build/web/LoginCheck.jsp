<%@ page language ="java" import="java.sql.*" %>
<%@ page import ="javax.sql.*" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">
        <title>Please Wait</title>
    </head>
    <body>  
         <style>
            body {background-color: #ffefe0}
            img {
                position: absolute;
                top: 35%;
                right: 0px;
            }
            .navigation{
                margin-bottom: 0px;
                background:activecaption;
            }
            
            #h2{
                background-color: red;
                bottom: 20%;

            }

            p {text-align: left;
            }
            
            #menu1{
                background-color: #BB94A9;
                color: white;
                padding: 10px 10px;
                text-decoration: none;
            }

           
            #menu1:hover{
            }
          
            .top_banner{
                background-color: white
            }
            #logo{
                position: relative;
                background: black;
            }
            #wrapper{
                background: black;
            }

            #section{
                color: white;
                padding-left: 10px;
                padding-right: 10px;
            }
            #table{
                position: relative;
                top: 60%;
                left: 0%;
            }
            .rc-button-submit:hover {
                border: 1px solid #2F5BB7;
                color: #FFF;
                text-shadow: 0px 1px rgba(0, 0, 0, 0.3);
                background-color: #357AE8;
                background-image: -moz-linear-gradient(center top , #4D90FE, #357AE8);

            </style>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Transportation System</title>
           <body>
            <div id="wrapper">
     <img id="logo" src="transsystem.png" alt=""/>
                <img src="bus.train.jpg" alt="bustrain View" style="width:500px;height:400px">
                    

                        <div class="navigation">
                            <ul>
       
   <%!
         public class Actor{
                
               String userName = "sudhir3e14";  
               String password = "s137239";
               String url = "jdbc:postgresql://goonhilly6.eitlab.ihk-edu.dk:5432/sudhir3e14";
               
               Connection connection = null;
               PreparedStatement selectActors = null;
               ResultSet resultSet = null;
               
               public Actor(){
                    try{
                        connection = DriverManager.getConnection(url, userName, password);
                        selectActors = connection.prepareStatement( "SELECT firstname,lastname,email,password,amount "
                                                                     + "FROM customerdbs "
                                                                     + "WHERE firstname =?"
                                                                     + "AND password =?");
                                         
                                                                                                       
                   }catch(Exception e){
                   e.printStackTrace();
                   }
                 }
                public ResultSet getActors(String first, String last){
                
                  try{
                      selectActors.setString(1,first );
                      selectActors.setString(2,last);
                      resultSet=selectActors.executeQuery();
                
                }catch(Exception e){
                    e.printStackTrace();
                
                }
               return resultSet;
                }
                }
                 %>
                 <%
                 String firstName = new String();
                 String Password = new String();
                 
                 if(request.getParameter("first") != null){
                     firstName = request.getParameter("first");
                 }
                 if(request.getParameter("pass") != null){
                     Password = request.getParameter("pass");
                 }
                Actor actor = new Actor();
                ResultSet actors =actor.getActors(firstName, Password);
                
                if(actors.next())
             {
            session.setAttribute("firstname",firstName);
            response.sendRedirect("profile.jsp");      
         }
     else{  
     out.println("<a href=loginForm.jsp ><strong>Invalid details,Please try it Again!</strong></a>");
     }                                
                     %>
                
                    
            
    </body>
</html>
