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
                left: 10%;
                    bottom:6%;
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
                left: 0%;
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
        </head>
        <body>
            <div id="wrapper">


                <img id="logo" src="transsystem.png" alt=""/>
               
                 <img src="map.jpg" alt="Map_copenhagen" style="width:600px;height:400px">   
                    <div id="contents">

                        <div class="navigation">
                            <ul>

                                <a id="menu1" href="searchAccount.jsp">Home</a>
                                <a id="menu2" href="pricetable.jsp">Price</a>
                                <a id="menu3" href="Map.jsp">Map</a>
                                <a id="menu4" href="profile.jsp">Profile</a>  
                                <a id="menu5" href="manage.jsp">Manage</a>  
                            </ul>

                        </div>

                        <form action="Logout.jsp" method="post">
                            <input id="menu6" type="submit" value="Log out" style="width:80px;height: 28px">
                        </form>
                        
                       
                


                    </div>
            </body>
        </html>
