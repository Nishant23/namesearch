/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
public class adminLoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        
        String userid=request.getParameter("userid").trim();
        String password=request.getParameter("password").trim();
  
        if(userid.compareTo("")!=0 && password.compareTo("")!=0){
            if(adminLoginVerify.validate(userid,password)){
                
                HttpSession session=request.getSession();
                session.setAttribute("userid", userid);
                session.setAttribute("1", out);
                
                RequestDispatcher rd=request.getRequestDispatcher("adminpage.html");
                rd.forward(request, response);
            }else{
                out.println("<p>Invalid username or password.</p>");
                out.println("<p><a href=\"adminLogin.html\">Go back</a></p>");
            }
        }else{
            out.print("<script>alert(\"Please fill in all fields.\");</script>");
                RequestDispatcher rd=request.getRequestDispatcher("adminLogin.html");
                try{
                    rd.wait();
                }catch(Exception e){

                }
                out.print("<p style=\"font-size:30px;\"><a href=\"adminLogin.html\">Go back</a><p>");
        }
        out.close();
    }

}
