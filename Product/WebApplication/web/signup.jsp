<%-- 
    Document   : signup.jsp
    Created on : Oct 29, 2014, 12:42:17 PM
    Author     : asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            #confirm{
                position:absolute;
                top: 65%;
                left:50%;
            }
           
            #Register{
                position: absolute;
                top:70%;
                left: 46%;
            }
         
            
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign up</title>
  
    </head>
    <body>
        <div id="wrapper">
               <h1>
                   <center><strong>TransSystem Sign up</strong></center>

        </h1> 
            <hr></hr>
           

              <div id="contents">
                  <form action="SignupProcess.jsp" method="post"> 
                   
        <label class="hidden-label" for="Enter your first name"></label>
        <input id="Firstname" class="" type="text" spellcheck="false" value="" placeholder="Enter your first name" name="firstname" style="position: absolute; top: 25%; left: 44%;" size="25">
     
        <label class="hidden-label" for="Enter your last name"></label>
        <input id="Lastname" class="" type="lastname" spellcheck="false" value="" placeholder="Enter your last name" name="lastname" style="position: absolute; top: 35%; left: 44%;" size="25">
        
        <label class="hidden-label" for="Enter your email address"></label>
        <input id="email" class="" type="email" spellcheck="false" value="" placeholder="Enter your Email address" name="email" style="position: absolute; top: 45%; left: 44%;" size="25">
        
        <label class="hidden-label" for="Enter your password"></label>
        <input id="Password" class="" type="password" spellcheck="false" value="" placeholder="Enter your password" name="password" style="position: absolute; top: 55%; left: 44%;" size="25">
        <input type="submit" value="Register">
         </form>
        <a id="confirm" href="SignupProcess.jsp"><center>Confirm</center></a>
        <td>
            <a id="Register" href="index.jsp">
                
                 
                <strong>
                    Already Registered?
                </strong>
            </a>
        </td>
       
              </body>
</html>
