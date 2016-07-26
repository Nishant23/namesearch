/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author LENOVO
 */
public class deleteUserServlet extends HttpServlet {

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
            out.println("<title>Servlet deleteUserServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet deleteUserServlet at " + request.getContextPath() + "</h1>");
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

                if(newUserValidate.validate(userid)){
                    if(deleteUserValidate.validate(userid)){
                        Connection con=connect.getConnection();
                        String query="DROP TABLE "+userid;
                        try{
                            con.prepareStatement(query).executeQuery();
                            out.print("USER SUCCESSFULLY DELETD.");
                            request.getRequestDispatcher("deleteUser.html").include(request, response);
                        }catch(Exception e){
                            e.printStackTrace();
                            request.getRequestDispatcher("deleteUser.html").include(request, response);
                        }
                        ;
                        
                    }else{
                        out.println("<p>There is some error. Please try agin later.</p>");
                        request.getRequestDispatcher("deleteUser.html").include(request, response);
                    }
                }else{
                    out.println("<h5>User ID doesnot exists.</h5>");
                    request.getRequestDispatcher("deleteUser.html").include(request, response);
                }
            }else{
                out.print("Please login first");  
                request.getRequestDispatcher("index.html").include(request, response);
            }
        }else{
            out.print("Please login first");  
            request.getRequestDispatcher("index.html").include(request, response);
        }
        out.close();
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
