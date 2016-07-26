/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author LENOVO
 */
public class checkboxServlets extends HttpServlet {
    static ArrayList data=new ArrayList();
    static ArrayList value=new ArrayList();
    static int num;
    
    public checkboxServlets(){
        num=1;
    }
    
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
            out.println("<title>Servlet checkboxServlets</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet checkboxServlets at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }
    
    static ArrayList get(){
        return data;
    }
    
    static int getNum(){
        return num;
    }
    
    static void setNum(){
        ++num;
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
        data.clear();
        value.clear();
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession(false);
        if(session!=null){
            String[] Values=request.getParameterValues("checkbox");
            
            
            try{
                
                for(String s : Values){
                    //out.print("<p>"+s+"</p>");
                    if(data.contains(s)){
                        continue;
                    }else{
                        data.add(s);
                        value.add("");
                    }
                    
                    
                }
                
                String userid=new String();
                //System.out.println(data);
                String name=session.getAttribute("userid").toString();
                File file=new File(name);
                System.out.println(file.exists());
                System.out.println(file.delete());
                
                
                request.setAttribute("data", data);
                request.setAttribute("value",value);
                request.getRequestDispatcher("tableEntry.jsp").forward(request, response);
                
            }catch (Exception e){
                out.print("<p>Please select any option</p>");  
                request.getRequestDispatcher("pdfOptions.html").include(request, response);
            }
            
        }else{
            out.print("<p>Please login first.</p>");
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
