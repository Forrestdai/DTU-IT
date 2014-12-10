
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
            #menu1:hover{
            }
            #menu3:hover{
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
                                <a id="menu1" href="index.jsp">Home</a>
                                <a id="menu3" href="http://www.dsb.dk/trafikinformation/">Info</a>
                                



                                </form>
                            </ul>

                        </div>
                        <title>Please Insert All The Details Below! </title>
                        </head>
                        <body onLoad="displayResults()">
                            <h1>Please Insert All The Details Below!</h1>


                            <%!
                                public class Actor
                                {

                                    String userName = "sudhir3e14";
                                    String password = "s137239";
                                    String url = "jdbc:postgresql://goonhilly6.eitlab.ihk-edu.dk:5432/sudhir3e14";

                                    Connection connection = null;
                                    PreparedStatement insertActors = null;
                                    ResultSet resultSet = null;

                                    public Actor()
                                    {
                                        try
                                        {
                                            connection = DriverManager.getConnection(url, userName, password);
                                            insertActors = connection.prepareStatement(
                                                    "INSERT INTO customerdbs(firstname,lastname,email,password,amount)"
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
                                            insertActors.setString(1, firstname);
                                            insertActors.setString(2, lastname);
                                            insertActors.setString(3, email);
                                            insertActors.setString(4, password);
                                            insertActors.setString(5, amount);

                                            result = insertActors.executeUpdate();

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
                                        <tr>
                                            <td>Amount:</td>
                                            <td><input type="text" name="amount" value="" size="50" /></td>
                                        </tr>
                                </table>
                                </tbody>
                                <input type="hidden" name="hidden" value="<%= result%>" />
                                <input type="reset" value="Clear" name="clear"  />
                                <input type="submit" value="Submit" name="submit"  />
                            </form>
                                <button onclick="location.href='index.jsp';"id="float-left submit-button"> Please go home page and log in</button>
                            <SCRIPT LANGUAGE ="JavaScript">
                                <!--
                                     function displayResults() {                                                   
                                    if (document.myForm.hidden.value == 1) {
                                        alert("Account has been Successfully Created!");
                                    }
                                }

                                //  -->
                              
                            </SCRIPT>
                        </body>
                        </html>
