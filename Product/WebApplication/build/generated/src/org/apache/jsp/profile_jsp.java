package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;
import javax.sql.*;

public final class profile_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html;charset=ISO-8859-1\">\n");
      out.write("        <title>Please Wait</title>\n");
      out.write("    </head>\n");
      out.write("    <body>  \n");
      out.write("        <style>\n");
      out.write("            body {background-color: #ffefe0}\n");
      out.write("            img {\n");
      out.write("                position: absolute;\n");
      out.write("                top: 35%;\n");
      out.write("                right: 0px;\n");
      out.write("            }\n");
      out.write("            .navigation{\n");
      out.write("                margin-bottom: 0px;\n");
      out.write("                background:activecaption;\n");
      out.write("            }\n");
      out.write("            .navigation ul{\n");
      out.write("                padding: 8px 0px;\n");
      out.write("                background-color: black;\n");
      out.write("                height: 27px;\n");
      out.write("\n");
      out.write("            }\n");
      out.write("            #h2{\n");
      out.write("                background-color: red;\n");
      out.write("                bottom: 20%;\n");
      out.write("\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            p {text-align: left;\n");
      out.write("            }\n");
      out.write("            #contents{\n");
      out.write("                background-color: #BB94A9;\n");
      out.write("                color: black;\n");
      out.write("            }          \n");
      out.write("            #menu1{\n");
      out.write("                background-color: #BB94A9;\n");
      out.write("                color: white;\n");
      out.write("                padding: 10px 10px;\n");
      out.write("                text-decoration: none;\n");
      out.write("            }\n");
      out.write("            #menu2{\n");
      out.write("                background-color: black;\n");
      out.write("                color: white;\n");
      out.write("                padding: 10px 10px;\n");
      out.write("                text-decoration: none;\n");
      out.write("\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            #menu3{\n");
      out.write("                background-color: black;\n");
      out.write("                color: white;\n");
      out.write("                padding: 10px 10px;\n");
      out.write("                text-decoration: none;\n");
      out.write("            }\n");
      out.write("            #menu4{\n");
      out.write("                background-color: black;\n");
      out.write("                color: white;\n");
      out.write("                padding: 10px 10px;\n");
      out.write("                text-decoration: none;\n");
      out.write("            }\n");
      out.write("            #menu1:hover{\n");
      out.write("\n");
      out.write("            }\n");
      out.write("            #menu3:hover{\n");
      out.write("                background-color: blue;\n");
      out.write("\n");
      out.write("            }\n");
      out.write("            #menu4:hover{\n");
      out.write("                background-color: blue;\n");
      out.write("\n");
      out.write("            }\n");
      out.write("            #Signup{\n");
      out.write("                position: absolute;\n");
      out.write("                top: 42%;\n");
      out.write("                font-size: 30pt;\n");
      out.write("                color: #F00;\n");
      out.write("                left:30%;\n");
      out.write("            }\n");
      out.write("            Sign up:hover{\n");
      out.write("                background-color: rosybrown;\n");
      out.write("            }\n");
      out.write("            .top_banner{\n");
      out.write("                background-color: white\n");
      out.write("            }\n");
      out.write("            #logo{\n");
      out.write("                position: relative;\n");
      out.write("                background: black;\n");
      out.write("            }\n");
      out.write("            #wrapper{\n");
      out.write("                background: black;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("\n");
      out.write("            #section{\n");
      out.write("                color: white;\n");
      out.write("                padding-left: 10px;\n");
      out.write("                padding-right: 10px;\n");
      out.write("            }\n");
      out.write("            .rc-button-submit:hover {\n");
      out.write("                border: 1px solid #2F5BB7;\n");
      out.write("                color: #FFF;\n");
      out.write("                text-shadow: 0px 1px rgba(0, 0, 0, 0.3);\n");
      out.write("                background-color: #357AE8;\n");
      out.write("                background-image: -moz-linear-gradient(center top , #4D90FE, #357AE8);\n");
      out.write("\n");
      out.write("                #menu5{\n");
      out.write("                    position: absolute;\n");
      out.write("                    right: 20%;\n");
      out.write("                    top:10%;\n");
      out.write("                }\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("            </style>\n");
      out.write("            <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("            <title>Transportation System</title>\n");
      out.write("\n");
      out.write("        <body>\n");
      out.write("            <div id=\"wrapper\">\n");
      out.write("                <img id=\"logo\" src=\"transsystem.png\" alt=\"\"/>\n");
      out.write("                <img src=\"bus.train.jpg\" alt=\"bustrain View\" style=\"width:500px;height:400px\">\n");
      out.write("                    <div id=\"contents\">\n");
      out.write("\n");
      out.write("                        <div class=\"navigation\">\n");
      out.write("                            <ul>\n");
      out.write("                                <div class=\"navigation\">\n");
      out.write("                                    <ul>\n");
      out.write("\n");
      out.write("                                        <a id=\"menu1\" href=\"searchAccount.jsp\">Home</a>\n");
      out.write("                                        <a id=\"menu2\" href=\"pricetable.jsp\">Price</a>\n");
      out.write("                                        <a id=\"menu3\" href=\"\">Info</a>\n");
      out.write("                                        <a id=\"menu4\" href=\"profile.jsp\">Profile</a>  \n");
      out.write("                                    </ul>\n");
      out.write("\n");
      out.write("\n");
      out.write("                                    <div id=\"page_content\"> \n");
      out.write("\n");
      out.write("                                        <div class=\"left_side_bar\"> \n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("                                            <div class=\"col_1\">\n");
      out.write("                                                <h1>User</h1>\n");
      out.write("                                                <div class=\"box\">\n");
      out.write("                                                    <p><strong>");

                                                        String user = session.getAttribute("firstname").toString();
                                                        out.println(user);
                                                            
      out.write("\n");
      out.write("                                                        </strong></p>\n");
      out.write("                                                        ");

                                                            String fns = session.getAttribute("firstname").toString();
                                                            Class.forName("org.postgresql.Driver");
                                                            Connection con = DriverManager.getConnection("jdbc:postgresql://goonhilly6.eitlab.ihk-edu.dk:5432/sudhir3e14", "sudhir3e14", "s137239");
                                                            Statement st = con.createStatement();
                                                            ResultSet rs = st.executeQuery("SELECT * from customerdbs where firstname='" + fns + "'");
                                                            while (rs.next())
                                                            {
                                                                String fn = rs.getString("firstname");

                                                        
      out.write("\n");
      out.write("                                                    <br> <p><i>First name: </i><strong>");
      out.print(fn);
      out.write("</strong></p> ");
 }  
      out.write("\n");
      out.write("                                                    <br>\n");
      out.write("                                                    <p><strong><a href=\"logout.jsp\">Logout</a></strong></p>\n");
      out.write("                                                </div>\n");
      out.write("                                            </div>\n");
      out.write("\n");
      out.write("                                        </div>\n");
      out.write("\n");
      out.write("                                        <div class=\"right_section\">\n");
      out.write("                                            ");

                                                ResultSet rs2 = st.executeQuery("SELECT * from customerdbs where firstname='" + fns + "'");
                                                while (rs2.next())
                                                {
                                                    String fn = rs2.getString("firstname");
                                                    String ln = rs2.getString("lastname");
                                                    String em = rs2.getString("email");
                                                    String pd = rs2.getString("password");
                                                    String at = rs2.getString("amount");
                                                    
                                            
      out.write("\n");
      out.write("                                            <center><table>\n");
      out.write("                                                    <tr>\n");
      out.write("                                                        <td><strong>First Name: </strong></td>\n");
      out.write("                                                        <td>");
 out.println(fn); 
      out.write("</td>\n");
      out.write("                                                    </tr>\n");
      out.write("                                                    <tr>\n");
      out.write("                                                        <td><strong>Last Name: </strong></td>\n");
      out.write("                                                        <td>");
 out.println(ln); 
      out.write("</td>\n");
      out.write("                                                    </tr>\n");
      out.write("                                                    <tr>\n");
      out.write("                                                        <td><strong>Email: </strong></td>\n");
      out.write("                                                        <td>");
 out.println(em); 
      out.write("</td>\n");
      out.write("                                                    </tr>\n");
      out.write("                                                    <tr>\n");
      out.write("                                                        <td><strong>Password: </strong></td>\n");
      out.write("                                                        <td>");
 out.println(pd); 
      out.write("</td>\n");
      out.write("                                                    </tr>\n");
      out.write("                                                    <tr>\n");
      out.write("                                                        <td><strong>Amount: </strong></td>\n");
      out.write("                                                        <td>");
 out.println(at); 
      out.write("</td>\n");
      out.write("                                                    </tr>\n");
      out.write("                                                </table></center>\n");
      out.write("                                                ");
 }
      out.write("\n");
      out.write("                                        </div>\n");
      out.write("                                        </body>\n");
      out.write("                                        </html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
