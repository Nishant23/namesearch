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
public class changeDBServlet extends HttpServlet {

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
            out.println("<title>Servlet changeDBServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet changeDBServlet at " + request.getContextPath() + "</h1>");
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
            String name=session.getAttribute("userid").toString();
            if(name.compareTo("admin")==0){
                String ip=request.getParameter("ip").trim();
                String port=request.getParameter("port").trim();
                String username=request.getParameter("username").trim();
                String password=request.getParameter("password").trim();
                if(ip.compareTo("")!=0 && port.compareTo("")!=0 && username.compareTo("")!=0 && password.compareTo("")!=0){
                    if(changeDBValidate.validate(ip,port,username,password)){
                        RequestDispatcher rd=request.getRequestDispatcher("resetDBMessage");
                        rd.forward(request, response);
                    }else{
                        out.println("<p>There is some error. Please try agin later.</p>");
                        request.getRequestDispatcher("changeDatabase.html").include(request, response);
                    }
                }else{
                    out.print("Please fill in all fields.");  
                    request.getRequestDispatcher("changeDatabase.html").include(request, response);
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
