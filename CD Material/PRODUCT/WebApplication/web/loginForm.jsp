<%-- 
    Document   : selecting
    Created on : Dec 3, 2014, 3:31:24 PM
    Author     : sudhir
--%>

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
                                
                                </form>
                            </ul>

                        </div>

                        <title>Please Insert Your Log in Details here</title>
                        </head>
                        <body >
                            <h1>Please Insert Your Log in Details here</h1>
                            <form name="myForm" action="LoginCheck.jsp" method="POST">
                                <table border="0">
                                    <tbody>
                                        <tr>
                                            <td>First Name :</td>
                                            <td><input type="text" name="first" value="" size="50" /></td>
                                        </tr>
                                        <tr>
                                            <td>Password :</td>
                                            <td><input type="password" name="pass" value="" size="50" /></td>
                                        </tr>
                                    </tbody>
                                </table>
                                <input type="reset" value="clear" name="clear" />
                                <input type="submit" value="submit" name="submit" />
                            </form>
                         </body>
                        </html>
