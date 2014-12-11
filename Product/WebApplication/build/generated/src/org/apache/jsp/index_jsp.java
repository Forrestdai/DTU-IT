package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\r\n");
      out.write("    \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <style>\r\n");
      out.write("            body {background-color: #ffefe0}\r\n");
      out.write("            img {\r\n");
      out.write("                position: absolute;\r\n");
      out.write("                top: 35%;\r\n");
      out.write("                right: 0px;\r\n");
      out.write("            }\r\n");
      out.write("            .navigation{\r\n");
      out.write("                margin-bottom: 0px;\r\n");
      out.write("                background:activecaption;\r\n");
      out.write("            }\r\n");
      out.write("            .navigation ul{\r\n");
      out.write("                padding: 8px 0px;\r\n");
      out.write("                background-color: black;\r\n");
      out.write("                height: 27px;\r\n");
      out.write("\r\n");
      out.write("            }\r\n");
      out.write("            #h2{\r\n");
      out.write("                background-color: red;\r\n");
      out.write("                bottom: 20%;\r\n");
      out.write("\r\n");
      out.write("            }\r\n");
      out.write("\r\n");
      out.write("            p {text-align: left;\r\n");
      out.write("            }\r\n");
      out.write("            #contents{\r\n");
      out.write("                background-color: #BB94A9;\r\n");
      out.write("                color: black;\r\n");
      out.write("            }          \r\n");
      out.write("            #menu1{\r\n");
      out.write("                background-color: #BB94A9;\r\n");
      out.write("                color: white;\r\n");
      out.write("                padding: 10px 10px;\r\n");
      out.write("                text-decoration: none;\r\n");
      out.write("            }\r\n");
      out.write("\r\n");
      out.write("            #menu3{\r\n");
      out.write("                background-color: black;\r\n");
      out.write("                color: white;\r\n");
      out.write("                padding: 10px 10px;\r\n");
      out.write("                text-decoration: none;\r\n");
      out.write("            }\r\n");
      out.write("            #menu1:hover{\r\n");
      out.write("            }\r\n");
      out.write("            #menu3:hover{\r\n");
      out.write("                background-color: blue;\r\n");
      out.write("\r\n");
      out.write("            }\r\n");
      out.write("        \r\n");
      out.write("            #Signup{\r\n");
      out.write("                position: absolute;\r\n");
      out.write("                top: 42%;\r\n");
      out.write("                font-size: 30pt;\r\n");
      out.write("                color: #F00;\r\n");
      out.write("                left:30%;\r\n");
      out.write("            }\r\n");
      out.write("            Sign up:hover{\r\n");
      out.write("                background-color: rosybrown;\r\n");
      out.write("            }\r\n");
      out.write("            .top_banner{\r\n");
      out.write("                background-color: white\r\n");
      out.write("            }\r\n");
      out.write("            #logo{\r\n");
      out.write("                position: relative;\r\n");
      out.write("                background: black;\r\n");
      out.write("            }\r\n");
      out.write("            #wrapper{\r\n");
      out.write("                background: black;\r\n");
      out.write("            }\r\n");
      out.write("            #Username, #Password, #login{\r\n");
      out.write("\r\n");
      out.write("                left: 65%;\r\n");
      out.write("                top:10%;\r\n");
      out.write("                position: relative;\r\n");
      out.write("            }\r\n");
      out.write("            #section{\r\n");
      out.write("                color: white;\r\n");
      out.write("                padding-left: 10px;\r\n");
      out.write("                padding-right: 10px;\r\n");
      out.write("            }\r\n");
      out.write("            #login{\r\n");
      out.write("                position: absolute;\r\n");
      out.write("                top: 43%;\r\n");
      out.write("                left:45%;\r\n");
      out.write("                \r\n");
      out.write("              \r\n");
      out.write("            }\r\n");
      out.write("            .rc-button-submit:hover {\r\n");
      out.write("                border: 1px solid #2F5BB7;\r\n");
      out.write("                color: #FFF;\r\n");
      out.write("                text-shadow: 0px 1px rgba(0, 0, 0, 0.3);\r\n");
      out.write("                background-color: #357AE8;\r\n");
      out.write("                background-image: -moz-linear-gradient(center top , #4D90FE, #357AE8);\r\n");
      out.write("\r\n");
      out.write("            </style>\r\n");
      out.write("            <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("            <title>Transportation System</title>\r\n");
      out.write("        </head>\r\n");
      out.write("        <body>\r\n");
      out.write("            <div id=\"wrapper\">\r\n");
      out.write("     <img id=\"logo\" src=\"transsystem.png\" alt=\"\"/>\r\n");
      out.write("                <img src=\"bus.train.jpg\" alt=\"bustrain View\" style=\"width:500px;height:400px\">\r\n");
      out.write("                    <div id=\"contents\">\r\n");
      out.write("\r\n");
      out.write("                        <div class=\"navigation\">\r\n");
      out.write("                            <ul>\r\n");
      out.write("                                <a id=\"menu1\" href=\"index.jsp\">Home</a>\r\n");
      out.write("                                <a id=\"Signup\" href=\"SignupProcess.jsp\">Sign up</a>\r\n");
      out.write("                                <a id=\"login\" href=\"loginForm.jsp\"> <font size=\"6\">Log in</font> </a>\r\n");
      out.write(" \r\n");
      out.write("                               \r\n");
      out.write("                                </form>\r\n");
      out.write("                            </ul>\r\n");
      out.write("\r\n");
      out.write("                        </div>\r\n");
      out.write("\r\n");
      out.write("                        <div class=\"left-section\">\r\n");
      out.write("                            <h1>\r\n");
      out.write("                                Welcome\r\n");
      out.write("                            </h1>\r\n");
      out.write("                            <h2>\r\n");
      out.write("                                Not having account?\r\n");
      out.write("                            </h2>\r\n");
      out.write("                            <hr></hr>\r\n");
      out.write("                            <p>\r\n");
      out.write("                                Welcome to our Transsystem. We will serve you our best\r\n");
      out.write("                                quality of service to you.\r\n");
      out.write("                            </p>\r\n");
      out.write("                            <h2>Contact</h2>\r\n");
      out.write("                            <hr></hr>\r\n");
      out.write("                            <p>Customer Centre</p>\r\n");
      out.write("                               <p>If you have any questions, don't hesitate to call us at +45 91702207.</p>\r\n");
      out.write("                                \r\n");
      out.write("                            <p> Send us a message at daisc93@gmail.com</p>\r\n");
      out.write("\r\n");
      out.write("                        </div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("                    </div>\r\n");
      out.write("            </body>\r\n");
      out.write("        </html>\r\n");
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
