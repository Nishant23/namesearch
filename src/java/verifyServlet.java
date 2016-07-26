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
public class verifyServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        
        String userid=request.getParameter("userid").trim();
        String password=request.getParameter("password").trim();
        
        if(userid.compareTo("")!=0 && password.compareTo("")!=0){
        
            if(LoginDao.validate(userid,password)){
                
                if(userid.compareTo("admin")==0){
                    HttpSession session=request.getSession();
                    session.setAttribute("userid", userid);
                    RequestDispatcher rd=request.getRequestDispatcher("adminpage.html");
                    rd.forward(request, response);
                }else{
                    HttpSession session=request.getSession();
                    session.setAttribute("userid", userid);
                    RequestDispatcher rd=request.getRequestDispatcher("pdfOptions.html");
                    rd.forward(request, response);
                }
                
                
                
            }else{


                out.print("<script>alert(\"Inavlid userid or password.\");</script>");
                request.getRequestDispatcher("index.html").include(request, response);
            }
        }else{
            out.print("<script>alert(\"Please fill in all fields.\");</script>");
            request.getRequestDispatcher("index.html").include(request, response);
        }
        out.close();
    }

}
