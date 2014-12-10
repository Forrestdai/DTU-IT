<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
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
            #login{
                position: absolute;
                top: 43%;
                left:45%;
                
              
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
                                <a id="Signup" href="SignupProcess.jsp">Sign up</a>
                                <a id="login" href="loginForm.jsp"> <font size="6">Log in</font> </a>
 
                                  

                                </form>
                            </ul>

                        </div>



                        <div class="left-section">
                            <h1>
                                Welcome
                            </h1>
                            <h2>
                                Not having account?
                            </h2>
                            <hr></hr>
                            <p>
                                Welcome to our Transsystem. We will serve you our best
                                quality of service to you.
                            </p>
                            <h2>Contact</h2>
                            <hr></hr>
                            <p>Customer Centre</p>
                               <p>If you have any questions, don't hesitate to call us at +45 91702207.</p>
                                
                            <p> Send us a message at daisc93@gmail.com</p>

                        </div>


                    </div>
            </body>
        </html>
