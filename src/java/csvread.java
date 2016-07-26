/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
public class csvread extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    static ArrayList value=new ArrayList();
    
    public csvread(){
        value.clear();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /*
             * TODO output your page here. You may use following sample code.
             */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet csvread</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet csvread at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession(false);
        if(session!=null){
            if(request.getParameter("submit")!=null){
                String formname="field";
                try{
                    String name=session.getAttribute("userid").toString();
                    FileReader file=new FileReader(name);
                    BufferedReader br=new BufferedReader(file);
                    String currentLine=br.readLine();
                    if(currentLine!=null){
                        boolean flag=true;
                        String[] data=currentLine.split(",");
                        int l=data.length;
                        value.clear();
                        for(int i=0;i<l;i++){
                            String temp=request.getParameter(formname+i);
                            System.out.println(temp);
                            if(value.contains(temp)){
                                flag=false;
                                break;
                            }else{
                                value.add(temp);
                            }
                            
                        }
                        if(flag){
                            deleteTable.delete(name);
                            while(currentLine!=null){
                               
                                csvdatasearch.search(currentLine,value,name);
                                currentLine=br.readLine();
                            }
                            request.getRequestDispatcher("showdata.jsp").forward(request, response);
                        }else{
                            out.println("<b>Two or more than two fields have same option. Plaese select different options.</b>");
                            request.getRequestDispatcher("csvfields.jsp").include(request, response);
                        }
                        
                    }
                    br.close();
                    file.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }else if(request.getParameter("back")!=null){
                request.getRequestDispatcher("csvupload.jsp").forward(request, response);
            }
        }else{
            out.print("<p>Please login first.</p>");
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
