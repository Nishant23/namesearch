/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
public class fileDownloadservletPDF extends HttpServlet {

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
            out.println("<title>Servlet fileDownloadservlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet fileDownloadservlet at " + request.getContextPath() + "</h1>");
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
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession(false);
        if(session!=null){
            try{
                
                Connection con=connect.getConnection();
                
                String name=session.getAttribute("userid").toString();
                String file_name=name+".doc";
                String query="select * from files where file_name='"+file_name+"'";
                ResultSet rs=con.prepareStatement(query).executeQuery();
                if(rs.next()){
                    Clob clob=rs.getClob("file_data");
                    //System.out.println(clob);
                    Reader r=clob.getCharacterStream();
                    long clob_length=(long) clob.length();
                    
                    ServletContext context=getServletContext();
                    String mimeType=context.getMimeType(name);
                    if (mimeType == null) {        
                        mimeType = "application/octet-stream";
                    } 
                    response.setContentType(mimeType);
                    String headerKey = "Content-Disposition";
                    String headerValue = String.format("attachment; filename=\"%s\"", file_name);
                    
                    
                    
                    
                    response.setHeader(headerKey, headerValue);
                    PrintWriter fw=response.getWriter();
                    int i=r.read();
                    while(i!=-1){
                        fw.write((char)i);
                        i=r.read();
                    }
                    fw.close();
                    r.close();
                }else{
                    out.print("Sorry file not present.");
                    request.getRequestDispatcher("downloadType.html").include(request, response);
                }
                con.close();
                
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }
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
        processRequest(request, response);
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
