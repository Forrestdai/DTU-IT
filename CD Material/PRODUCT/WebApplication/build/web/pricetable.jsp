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

                                </input>

                                </form>
                            </ul>
                            <div class="search-hd">
                                <div id="searchForm" class="searchfirst-info">
                                    <form action="Logout.jsp" method="post">
                                        <input id="menu5" type="submit" value="Log out" style="width:80px;height: 28px">
                                    </form>

                                    <table id="table" border="1" width="0">
                                        <thead>
                                            <tr>
                                                <th>Nordhavn st.</th>
                                                <th>Østerport st.</th>
                                                <th>Nørreport st.</th>
                                                <th>Vesterport st.</th>
                                                <th>København st.</th>
                                                <th>Dybbølsbro st.</th>
                                                <th>Enghave st.</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>07:00:00</td>
                                                <td>07:02:00</td>
                                                <td>07:04:00</td>
                                                <td>07:06:00</td>
                                                <td>07:08:00</td>
                                                <td>07:10:00</td>
                                                <td>07:12:00</td>
                                            </tr>
                                            <tr>
                                                <td>07:30:00</td>
                                                <td>07:32:00</td>
                                                <td>07:34:00</td>
                                                <td>07:36:00</td>
                                                <td>07:38:00</td>
                                                <td>07:40:00</td>
                                                <td>07:42:00</td>
                                            </tr>
                                            <tr>
                                                <td>08:00:00</td>
                                                <td>08:02:00</td>
                                                <td>08:04:00</td>
                                                <td>08:06:00</td>
                                                <td>08:08:00</td>
                                                <td>08:10:00</td>
                                                <td>08:12:00</td>
                                            </tr>
                                            <tr>
                                                <td>08:30:00</td>
                                                <td>08:32:00</td>
                                                <td>08:34:00</td>
                                                <td>08:36:00</td>
                                                <td>08:38:00</td>
                                                <td>08:40:00</td>
                                                <td>08:42:00</td>
                                            </tr>
                                            <tr>
                                                <td>09:00:00</td>
                                                <td>09:02:00</td>
                                                <td>09:04:00</td>
                                                <td>09:06:00</td>
                                                <td>09:08:00</td>
                                                <td>09:10:00</td>
                                                <td>09:12:00</td>
                                            </tr>
                                            <tr>
                                                <td>10:00:00</td>
                                                <td>10:02:00</td>
                                                <td>10:04:00</td>
                                                <td>10:06:00</td>
                                                <td>10:08:00</td>
                                                <td>10:10:00</td>
                                                <td>10:12:00</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
            </body>
        </html>
