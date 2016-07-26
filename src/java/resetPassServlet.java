/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
 * @author LENOVO
 */
public class resetPassServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet resetPassServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet resetPassServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        
        HttpSession session=request.getSession(false);
        if(session!=null){
            
            if("admin".compareTo(session.getAttribute("userid").toString())==0){
        
                String userid=request.getParameter("userid").trim();
                String password=request.getParameter("password").trim();
                String repassword=request.getParameter("repassword").trim();

                if(userid.compareTo("")!=0 && password.compareTo("")!=0 && repassword.compareTo("")!=0){

                    if(newUserValidate.validate(userid)){

                        if(password.length()>=8 && password.length()<=16){
                            if(password.compareTo(repassword)==0){
                                if(resetPasswordValidate.validate(userid,password)){
                                    out.print("PASSWORD SUCCESSFULLY CHANGED.");
                                    request.getRequestDispatcher("resetPassword.html").include(request, response);
                                }else{
                                    out.print("<script>alert(\"An error occured. Please try again later\");</script>");
                                    
                                    request.getRequestDispatcher("resetPassword.html").include(request, response);
                                }
                            }else{
                                out.print("<script>alert(\"Passwords donot match.\");</script>");
                                
                                request.getRequestDispatcher("resetPassword.html").include(request, response);
                            }
                        }else{
                            out.print("<script>alert(\"Password length must be between 8 to 16 characters.\");</script>");
                            
                            request.getRequestDispatcher("resetPassword.html").include(request, response);
                        }

                    }else{
                        out.print("User "+userid+" invalid.");
                        
                        request.getRequestDispatcher("resetPassword.html").include(request, response);
                    }

                }else{
                    out.print("<script>alert(\"Please fill in all fields.\");</script>");
                    
                   request.getRequestDispatcher("resetPassword.html").include(request, response);
                }
            }else{
                out.print("Please login first");  
                request.getRequestDispatcher("index.html").include(request, response);
            }
        }else{
            out.print("Please login first");  
            request.getRequestDispatcher("index.html").include(request, response);
        }
        
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
