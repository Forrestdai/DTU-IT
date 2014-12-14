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
            #menu2{
                background-color: black;
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
             #menu5{
                background-color: black;
                color: white;
                padding: 10px 10px;
                text-decoration: none;
            }
            #menu1:hover{
background-color: blue;
            }
            #menu2:hover{
                background-color: blue;

            }
            #menu3:hover{
                background-color: blue;

            }
            #menu4:hover{
                background-color: blue;

            }
            #menu5:hover{
                background-color: blue;

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
            .rc-button-submit:hover {
                border: 1px solid #2F5BB7;
                color: #FFF;
                text-shadow: 0px 1px rgba(0, 0, 0, 0.3);
                background-color: #357AE8;
                background-image: -moz-linear-gradient(center top , #4D90FE, #357AE8);

                #menu6{
                    position: absolute;
                    right: 20%;
                    top:10%;
                }




            </style>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Transportation System</title>

        <body>
            <div id="wrapper">
                <img id="logo" src="transsystem.png" alt=""/>
                <img src="bus.train.jpg" alt="bustrain View" style="width:500px;height:400px">
                    <div id="contents">

                        <div class="navigation">
                            <ul>
                                <div class="navigation">
                                    <ul>

                                        <a id="menu1" href="searchAccount.jsp">Home</a>
                                        <a id="menu2" href="pricetable.jsp">Price</a>
                                        <a id="menu3" href="Map.jsp">Map</a>
                                        <a id="menu4" href="profile.jsp">Profile</a>  
                                        <a id="menu5" href="manage.jsp">Manage</a>  
                                    </ul>


                                    <div id="page_content"> 

                                        <div class="left_side_bar"> 



                                            <div class="col_1">
                                                <div class="box">
                                                    <form action="Logout.jsp" method="post">
                                                        <input id="menu6" type="submit" value="Log out" style="width:80px;height: 28px">
                                                    </form>
                                                    <p><strong>Welcome <%
                                                        String user = session.getAttribute("firstname").toString();
                                                        out.println(user);
                                                            %>
                                                        </strong></p>
                                                        <%
                                                            String fns = session.getAttribute("firstname").toString();
                                                            Class.forName("org.postgresql.Driver");
                                                            Connection con = DriverManager.getConnection("jdbc:postgresql://goonhilly6.eitlab.ihk-edu.dk:5432/sudhir3e14", "sudhir3e14", "s137239");
                                                            Statement st = con.createStatement();
                                                            ResultSet rs = st.executeQuery("SELECT * from customerdbs where firstname='" + fns + "'");
                                                            while (rs.next())
                                                            {
                                                                String fn = rs.getString("firstname");

                                                        %>
                                                    <br> <p><i>First name: </i><strong><%=fn%></strong></p> <% }  %>
                                                    <br>

                                                </div>
                                            </div>

                                        </div>

                                        <div class="right_section">
                                            <%
                                                ResultSet rs2 = st.executeQuery("SELECT * from customerdbs where firstname='" + fns + "'");
                                                while (rs2.next())
                                                {
                                                    String fn = rs2.getString("firstname");
                                                    String ln = rs2.getString("lastname");
                                                    String em = rs2.getString("email");
                                                    String pd = rs2.getString("password");
                                                    String at = rs2.getString("amount");

                                            %>
                                            <table>
                                                <tboday>
                                                    <tr>
                                                        <td><strong>First Name: </strong></td>
                                                        <td><% out.println(fn); %></td>
                                                    </tr>
                                                    <tr>
                                                        <td><strong>Last Name: </strong></td>
                                                        <td><% out.println(ln); %></td>
                                                    </tr>
                                                    <tr>
                                                        <td><strong>Email: </strong></td>
                                                        <td><% out.println(em); %></td>
                                                    </tr>
                                                    <tr>
                                                        <td><strong>Password: </strong></td>
                                                        <td><% out.println(pd); %></td>
                                                    </tr>
                                                    <tr>
                                                        <td><strong>Amount: </strong></td>
                                                        <td><% out.println(at); %></td>
                                                    </tr>
                                                </tboday>
                                            </table>
                                            <% }%>
                                        </div>
                                        </body>
                                        </html>
