
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
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
            .navigation ul{
                padding: 8px 0px;
                background-color: black;
                height: 27px;

            }
            #h2{
                background-color: red;
                bottom: 20%;

            }

            p {text-align: left;
            }
            #contents{
                background-color: #BB94A9;
                color: black;
            }          
            #menu1{
                background-color: #BB94A9;
                color: white;
                padding: 10px 10px;
                text-decoration: none;
            }

            #menu3{
                background-color: black;
                color: white;
                padding: 10px 10px;
                text-decoration: none;
            }
            #menu4{
                background-color: black;
                color: white;
                padding: 10px 10px;
                text-decoration: none;
            }
            #menu1:hover{

            }



            #menu3:hover{
                background-color: blue;

            }
            #menu4:hover{
                background-color: blue;

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
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History</title>
    </head>
    <body>
        <div id="wrapper">


            <img id="logo" src="transsystem.png" alt=""/>
            <img src="bus.train.jpg" alt="bustrain View" style="width:500px;height:400px">
            <div id="contents">

                <div class="navigation">
                    <ul>
                        <a id="menu1" href="index.jsp">Home</a>
                        <a id="menu3" href="http://www.dsb.dk/trafikinformation/">Info</a>
                        <a id="menu4" href="">Profile</a>
                        <table border="3" width="5" cellspacing="4" cellpadding="6">
                            <thead>
                                <tr>
                                    <th>Customer ID</th>
                                    <th>Star Zone</th>
                                    <th>End Zone</th>
                                    <th>Starting Time</th>
                                    <th>Ending TIme</th>

                                </tr>
                            </thead>
                            <div id="page_content">
                                <div class="left_side_bar">
                                    <div class="col_1">
                                        <h1>Customer</h1>
                                        <div class="box">
                                            <p><strong><%
                                                String user = session.getAttribute("username").toString();
                                                out.println(user);
                                                    %>
                                                </strong></p>
                                                <%
                                                    String usern = session.getAttribute("username").toString();
                                                    Class.forName("org.postgresql.Driver");
                                                    Connection con = DriverManager.getConnection("jdbc:postgresql://goonhilly6.eitlab.ihk-edu.dk:5432/shicheng3e14", "root", "pass");
                                                    Statement st = con.createStatement();
                                                    ResultSet rs = st.executeQuery("SELECT * from login where username='" + usern + "'");
                                                    while (rs.next()) {
                                                        int usrid = rs.getInt("userID");

                                                %>
                                            <br> <p><i>User ID : </i><strong><%=usrid%></strong></p> <% }%>
                                            <br>
                                            <p><strong><a href="logout.jsp">Logout</a></strong></p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="right_section">

                                <table border="1" style="width:100%">
                                    <tr>
                                        <th>Date</th>
                                        <th>Time</th>
                                        <th>Cost</th>
                                        <th>Checked out Zone</th> 
                                        
                                    </tr>
                                    <tr>
                                        <td>6/11/2014</td>
                                        <td>10:32:22</td>
                                        <td>15DKK</td> 
                                        <td>31,32</td>
                                    </tr>
                                    <tr>
                                        <td>7/11/2014</td>
                                        <td>10:32:22</td>
                                        <td>15DKK</td> 
                                        <td>41,31</td>
                                    </tr>
                                   
                                </table>
                                <br>
                                <br>
                            </div>

                            </tr>
                            </tbody>
                        </table>

                        </body>
                        </html>
