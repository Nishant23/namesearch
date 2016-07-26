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
public class downlaodtypeServlet extends HttpServlet {

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
            out.println("<title>Servlet downlaodtypeServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet downlaodtypeServlet at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html");
        
        HttpSession session=request.getSession(false);
        
        if(session!=null){
            String radioSelect=request.getParameter("inlineRadioOptions");
            System.out.println(radioSelect);
            String name=session.getAttribute("userid").toString();
            if("pdf".equals(radioSelect)){
                out.print("PDF selected");
                pdf.fileUpload(name);
                RequestDispatcher rd=request.getRequestDispatcher("pdfDownload.html");
                rd.forward(request, response);
            }else if("csv".equals(radioSelect)){
                out.print("CSV selected");
                csv.fileUpload(name);
                RequestDispatcher rd=request.getRequestDispatcher("csvDownload.html");
                rd.forward(request, response);
            }else if("txt".equals(radioSelect)){
                out.print("Text selected");
                txt.fileUpload(name);
                RequestDispatcher rd=request.getRequestDispatcher("txtDownload.html");
                rd.forward(request, response);
            }else{
                out.print("Select an option");
                request.getRequestDispatcher("downloadType.html").include(request, response);
            }
        }else{
            out.print("Please login first.");
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
