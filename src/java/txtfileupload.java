/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Administrator
 */
public class txtfileupload extends HttpServlet {

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
            out.println("<title>Servlet csvfileupload</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet csvfileupload at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        
        HttpSession session=request.getSession(false);
        if(session!=null){
            String name=session.getAttribute("userid").toString();
            File f=new File(name);
            f.delete();
            boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
            System.out.println(isMultipartContent);
            if (!isMultipartContent) {
                out.println("Please select a file<br/>");
                request.getRequestDispatcher("txtupload.jsp").include(request, response);
            }else{
                FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
                try {
                    List<FileItem> fields = upload.parseRequest(request);
                    Iterator<FileItem> it = fields.iterator();
                    if (!it.hasNext()) {
			out.println("Please select a file<br/>");
                        request.getRequestDispatcher("txtupload.jsp").include(request, response);
                    }else{
                        FileItem fileItem = it.next();
                        
                        if(fileItem.getName()==null || fileItem.getName().isEmpty()){
                            out.println("Please select a file<br/>");
                            request.getRequestDispatcher("txtupload.jsp").include(request, response);
                        }else{
                            //System.out.println("Eureka");
                            int l=fileItem.getName().length();
                            String ext=fileItem.getName().substring(l-3);
                            System.out.println(ext);
                            if(ext.equals("txt")){
                                String s;
                                s=fileItem.getString();
                                FileWriter file=new FileWriter(name,true);
                                BufferedWriter writeFile=new BufferedWriter(file);
                                file.write(s);
                                writeFile.close();
                                file.close();

                                //redirect to other jsp.
                                request.getRequestDispatcher("txtfields.jsp").forward(request, response);
                            }else{
                                out.println("Please upload a txt file.<br/>");
                                request.getRequestDispatcher("txtupload.jsp").include(request, response);
                            }
                            
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
                
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
