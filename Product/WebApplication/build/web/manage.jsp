
<%@page import="java.sql.*"%>
<%@page import="java.util.Date"%>
<% Class.forName("org.postgresql.Driver");%>
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
            #find-journey__trip__from{
                position: relative;
                left:20%;
                top:50%;
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
            #menu6{
                background-color: black;
                color: white;
                padding: 10px 10px;
                text-decoration: none;
            }
            #menu1:hover{

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
            #menu6:hover{
                background-color: blue;

            }
            #Signup{
                position: absolute;
                top: 42%;
                font-size: 30pt;
                color: #F00;
                left:30%;
            }
            Sign up:hover{
                background-color: rosybrown;
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
            #Username, #Password, #login{
                left: 65%;
                top:10%;
                position: relative;
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
            }
            .search-bd .tab-info .label {
                float: left;
                width: 64px;
                height: 32px;
                font-size: 14px;
                font-weight: 700;
                line-height: 32px;
                text-align: right;

                #table{
                    border: 1px solid black;
                }
                #submitButton{
                    font-family: "Glyphicons Halflings";
                    font-style: normal;
                    font-weight: normal;
                    line-height: 1;
                    position: relative;
                    content: "";
                    color: #8D9C3F;
                    padding-left: 6px;
                }


            </style>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Transportation System</title>
        </head>
        <body>
            <div id="wrapper">
                <img id="logo" src="transsystem.png" alt=""/>
                <img src="bus.train.jpg" alt="bustrain View" style="width:500px;height:400px">
                    <div id="contents">

                        <div class="navigation">
                            <ul>
                                <a id="menu1" href="searchAccount.jsp">Home</a>
                                <a id="menu2" href="pricetable.jsp">Price</a>
                                <a id="menu3" href="Map.jsp">Map</a>
                                <a id="menu4" href="profile.jsp">Profile</a>  
                                <a id="menu6" href="manage.jsp">Manage</a>                    
                                </form>
                            </ul>

                        </div>
                        <title>Please Edit Your Information Here </title>
                        </head>
                        <body onLoad="displayResults()">
                            <h1>Please Update Your Current Information</h1>


                            <%!
                                public class Actor
                                {

                                    String userName = "sudhir3e14";
                                    String password = "s137239";
                                    String url = "jdbc:postgresql://goonhilly6.eitlab.ihk-edu.dk:5432/sudhir3e14";

                                    Connection connection = null;
                                    PreparedStatement updateActors = null;
                                    ResultSet resultSet = null;

                                    public Actor()
                                    {
                                        try
                                        {
                                            connection = DriverManager.getConnection(url, userName, password);
                                            updateActors = connection.prepareStatement(
                                                    "UPDATE INTO customerdbs(firstname,lastname,email,password,amount)"
                                                    + "VALUES(?,?,?,?,?)");

                                        } catch (Exception e)
                                        {
                                            e.printStackTrace();
                                        }
                                    }

                                    public int setActors(String firstname, String lastname, String email, String password, String amount)
                                    {

                                        int result = 0;
                                        try
                                        {
                                            updateActors.setString(1, firstname);
                                            updateActors.setString(2, lastname);
                                            updateActors.setString(3, email);
                                            updateActors.setString(4, password);
                                            updateActors.setString(5, amount);

                                            result = updateActors.executeUpdate();

                                        } catch (Exception e)
                                        {
                                            e.printStackTrace();

                                        }
                                        return result;
                                    }
                                }

                            %>
                            <%

                                int result = 0;
                                if (request.getParameter("submit") != null)
                                {

                                    String firstName = new String();
                                    String lastName = new String();
                                    String Email = new String();
                                    String Password = new String();
                                    String Amount = new String();

                                    if (request.getParameter("firstname") != null)
                                    {
                                        firstName = request.getParameter("firstname");
                                    }
                                    if (request.getParameter("lastname") != null)
                                    {
                                        lastName = request.getParameter("lastname");
                                    }
                                    if (request.getParameter("email") != null)
                                    {
                                        Email = request.getParameter("email");
                                    }

                                    if (request.getParameter("password") != null)
                                    {
                                        Password = request.getParameter("password");
                                    }
                                    if (request.getParameter("amount") != null)
                                    {
                                        Amount = request.getParameter("amount");
                                    }

                                    Actor actor = new Actor();
                                    result = actor.setActors(firstName, lastName, Email, Password, Amount);
                                }
                            %>

                            <form name="myForm" action="SignupProcess.jsp" onsubmit="return validateForm()" method="POST">
                                <table border="0">
                                    <tbody>

                                        <tr>
                                            <td>First Name:</td>
                                            <td><input type="text" name="firstname" value="" size="50" /></td>
                                        </tr>
                                        <tr>
                                            <td>Last Name:</td>
                                            <td><input type="text" name="lastname" value="" size="50" /></td>
                                        </tr>
                                        <tr>
                                            <td>Email:</td>
                                            <td><input type="text" name="email" value="" size="50" /></td>
                                        </tr>
                                        <tr>
                                            <td>Password:</td>
                                            <td><input type="password" name="password" value="" size="50" /></td>
                                        </tr>

                                </table>
                                </tbody>
                                <input type="hidden" name="hidden" value="<%= result%>" />
                                <input type="reset" value="Clear" name="clear"  />
                                <input type="submit" value="Submit" name="submit"  />
                            </form>
                            <button onclick="location.href = 'index.jsp';"id="float-left submit-button"> Please go home page and log in</button>
                            <SCRIPT LANGUAGE ="JavaScript">
                                <!--
                                   function displayResults() {



                                    if (document.myForm.hidden.value == 1) {
                                        alert("Account has been Successfully Updated!");
                                    }

                                }

                                //  -->
                              
                            </SCRIPT>
                        </body>
                        </html>
