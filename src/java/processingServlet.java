/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
public class processingServlet extends HttpServlet {

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
            out.println("<title>Servlet processingServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet processingServlet at " + request.getContextPath() + "</h1>");
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
        System.out.println(checkboxServlets.getNum());
        if(session!=null){
            if(request.getParameter("search")!=null){
                ArrayList data= new ArrayList();
                ArrayList mainValue=new ArrayList();
                data=checkboxServlets.get();
                Enumeration parameters=request.getParameterNames();
                while(parameters.hasMoreElements()){
                    String name=(String)parameters.nextElement();
                    String value=request.getParameter(name).trim();
                    if(value.compareTo("Search")!=0){
                        mainValue.add(value);
                        
                    }
                }
                //System.out.println(checkboxServlets.getNum());
                
                if(checkboxServlets.getNum()>1){
                    ArrayList temp=new ArrayList();
                    int len=data.size();
                    for(int j=0;j<len;j++){
                        temp.add("");
                    }
                    
                    if (mainValue.equals(temp)){
                        
                               //Redirect it bitch
                               String Name=session.getAttribute("userid").toString();
                               ArrayList option=(ArrayList) checkboxServlets.get().clone();
                               search.insert(Name);
                               System.out.println("Okay");
                               request.getRequestDispatcher("showdata.jsp").forward(request, response);
                        
                        
                    }else{
                        if(mainValue.contains("")){
                            request.setAttribute("value",mainValue);
                            request.setAttribute("data", data);
                            out.print("<script>alert(\"Please fill in all fields\");</script>");
                            request.getRequestDispatcher("tableEntry.jsp").include(request, response);

                        }else{
                            boolean flag=true;
                            request.setAttribute("value",mainValue);
                            request.setAttribute("data", data);
                            if(data.contains("PAN No")){
                                int index=data.indexOf("PAN No");
                                String pan=mainValue.get(index).toString();
                                flag=panNumberValidation.isValid(pan);
                                if(!flag){
                                    out.print("<script>alert(\"Invalid PAN Number\");</script>");
                                    //request.getRequestDispatcher("tableEntry.jsp").include(request, response);
                                }
                            }
                            if(flag){
                                if(data.contains("Mobile Number")){
                                    int index=data.indexOf("Mobile Number");
                                    String phone=mainValue.get(index).toString();
                                    flag=phoneValidation.isValid(phone);
                                    if(!flag){
                                        out.print("<script>alert(\"Invalid Phone Number\");</script>");
                                        //request.getRequestDispatcher("tableEntry.jsp").include(request, response);
                                    }
                                }
                            }
                            if(flag){
                                if(data.contains("Aadhar Card Number/UID Number")){
                                    int index=data.indexOf("Aadhar Card Number/UID Number");
                                    String uid=mainValue.get(index).toString();
                                    flag=VerhoeffAlgorithm.validateAadharNumber(uid);
                                    if(!flag){
                                        out.print("<script>alert(\"Invalid Aadhar Card Number/UID Number\");</script>");
                                        //request.getRequestDispatcher("tableEntry.jsp").include(request, response); 
                                    }
                                }
                            }
                            
                            if(flag){
                                if(data.contains("Email ID")){
                                    int index=data.indexOf("Email ID");
                                    String email=mainValue.get(index).toString();
                                    flag=emailValidate.isvalid(email);
                                    if(!flag){
                                        out.print("<script>alert(\"Invalid Email ID\");</script>");
                                    }
                                }
                            }
                            if(flag){
                                String sessName=session.getAttribute("userid").toString();
                                FileWriter file=new FileWriter(sessName,true);
                                BufferedWriter writeFile=new BufferedWriter(file);
                                String writeData=new String();

                                int i;

                                for(i=0;i<len-1;i++){
                                    writeData=writeData+mainValue.get(i).toString().trim()+"~";
                                }
                                writeData=writeData+mainValue.get(i).toString().trim()+"\n";
                                String updateData=writeData.replace("\n",System.lineSeparator());
                                writeFile.write(updateData);
                                writeFile.close();
                                file.close();
                                checkboxServlets.setNum();
                                //redirect it bitch
                                
                                String Name=session.getAttribute("userid").toString();
                                search.insert(Name);
                                System.out.println("Okay");
                                request.getRequestDispatcher("showdata.jsp").forward(request, response);
                            }
                            else{
                                request.setAttribute("value",mainValue);
                                request.setAttribute("data", data);
                                request.getRequestDispatcher("tableEntry.jsp").include(request, response);
                            }
                            
                        }
                    }
                }else{
                    if(mainValue.contains("")){
                        request.setAttribute("value",mainValue);
                        request.setAttribute("data", data);
                        out.print("<script>alert(\"Please fill in all fields\");</script>");
                        request.getRequestDispatcher("tableEntry.jsp").include(request, response);

                    }else{
                        
                        boolean flag=true;
                        request.setAttribute("value",mainValue);
                        request.setAttribute("data", data);
                        if(data.contains("PAN No")){
                            int index=data.indexOf("PAN No");
                            String pan=mainValue.get(index).toString();
                            flag=panNumberValidation.isValid(pan);
                            if(!flag){
                                out.print("<script>alert(\"Invalid PAN Number\");</script>");
                                //request.getRequestDispatcher("tableEntry.jsp").include(request, response);
                            }
                        }
                        if(flag){
                            if(data.contains("Mobile Number")){
                                int index=data.indexOf("Mobile Number");
                                String phone=mainValue.get(index).toString();
                                flag=phoneValidation.isValid(phone);
                                if(!flag){
                                    out.print("<script>alert(\"Invalid Phone Number\");</script>");
                                    //request.getRequestDispatcher("tableEntry.jsp").include(request, response);
                                }
                            }
                        }
                        if(flag){
                            if(data.contains("Aadhar Card Number/UID Number")){
                                int index=data.indexOf("Aadhar Card Number/UID Number");
                                String uid=mainValue.get(index).toString();
                                flag=VerhoeffAlgorithm.validateAadharNumber(uid);
                                if(!flag){
                                    out.print("<script>alert(\"Invalid Aadhar Card Number/UID Number\");</script>");
                                    //request.getRequestDispatcher("tableEntry.jsp").include(request, response); 
                                }
                            }
                        }
                        if(flag){
                            if(data.contains("Email ID")){
                                int index=data.indexOf("Email ID");
                                String email=mainValue.get(index).toString();
                                flag=emailValidate.isvalid(email);
                                if(!flag){
                                    out.print("<script>alert(\"Invalid Email ID\");</script>");
                                }
                            }
                        }
                        if(flag){
                            String sessName=session.getAttribute("userid").toString();
                            FileWriter file=new FileWriter(sessName,true);
                            BufferedWriter writeFile=new BufferedWriter(file);
                            String writeData=new String();

                            int i;
                            int len=data.size();
                            for(i=0;i<len-1;i++){
                                writeData=writeData+mainValue.get(i).toString().trim()+"~";
                            }
                            writeData=writeData+mainValue.get(i).toString().trim()+"\n";
                            String updateData=writeData.replace("\n",System.lineSeparator());
                            writeFile.write(updateData);
                            writeFile.close();
                            file.close();
                            checkboxServlets.setNum();
                            //Redirect it bitch
                            String Name=session.getAttribute("userid").toString();
                            search.insert(Name);
                            System.out.println("Okay");
                            request.getRequestDispatcher("showdata.jsp").forward(request, response);
                            
                        }else{
                            request.setAttribute("value",mainValue);
                            request.setAttribute("data", data);
                            request.getRequestDispatcher("tableEntry.jsp").include(request, response);
                        }
                    
                        

                        
                    }
                }
                //out.print(session.getAttribute("userid"));
                //request.setAttribute("data", data);
                //out.print("<p><b>Search button pressed.</b></p>");
                //request.getRequestDispatcher("tableEntry.jsp").forward(request, response);
            }
            else if(request.getParameter("more")!=null){
                ArrayList data= new ArrayList();
                data=checkboxServlets.get();
                ArrayList value=new ArrayList();
                Enumeration parameters=request.getParameterNames();
                while(parameters.hasMoreElements()){
                    String name=(String)parameters.nextElement();
                    value.add(request.getParameter(name).toString().trim());
                    
                }
                if(value.contains("")){
                    request.setAttribute("value",value);
                    request.setAttribute("data", data);
                    out.print("<script>alert(\"Please fill in all fields\");</script>");
                    request.getRequestDispatcher("tableEntry.jsp").include(request, response);
                }else{
                    boolean flag=true;
                    request.setAttribute("value",value);
                    request.setAttribute("data", data);
                    if(data.contains("PAN No")){
                        int index=data.indexOf("PAN No");
                        String pan=value.get(index).toString();
                        flag=panNumberValidation.isValid(pan);
                        if(!flag){
                            out.print("<script>alert(\"Invalid PAN Number\");</script>");
                            //request.getRequestDispatcher("tableEntry.jsp").include(request, response);
                        }
                    }
                    if(flag){
                        if(data.contains("Mobile Number")){
                            int index=data.indexOf("Mobile Number");
                            String phone=value.get(index).toString();
                            flag=phoneValidation.isValid(phone);
                            if(!flag){
                                out.print("<script>alert(\"Invalid Phone Number\");</script>");
                                //request.getRequestDispatcher("tableEntry.jsp").include(request, response);
                            }
                        }
                    }
                    if(flag){
                        if(data.contains("Aadhar Card Number/UID Number")){
                            int index=data.indexOf("Aadhar Card Number/UID Number");
                            String uid=value.get(index).toString();
                            flag=VerhoeffAlgorithm.validateAadharNumber(uid);
                            if(!flag){
                                out.print("<script>alert(\"Invalid Aadhar Card Number/UID Number\");</script>");
                                //request.getRequestDispatcher("tableEntry.jsp").include(request, response); 
                            }
                        }
                    }
                    if(flag){
                         if(data.contains("Email ID")){
                             int index=data.indexOf("Email ID");
                             String email=value.get(index).toString();
                             flag=emailValidate.isvalid(email);
                             if(!flag){
                                 out.print("<script>alert(\"Invalid Email ID\");</script>");
                             }
                        }
                    }
                    if(flag){
                        String sessName=session.getAttribute("userid").toString();
                        FileWriter file=new FileWriter(sessName,true);
                        BufferedWriter writeFile=new BufferedWriter(file);
                        String writeData=new String();

                        int i;
                        int len=data.size();
                        for(i=0;i<len-1;i++){
                            writeData=writeData+value.get(i).toString().trim()+"~";
                        }
                        writeData=writeData+value.get(i).toString().trim()+"\n";
                        String updateData=writeData.replace("\n",System.lineSeparator());
                        writeFile.write(updateData);
                        writeFile.close();
                        file.close();
                        checkboxServlets.setNum();
                        for(i=0;i<len;i++){
                            value.set(i, "");
                        }
                        /*String currentLine;
                        BufferedReader br=new BufferedReader(new FileReader(name+".txt"));
                        currentLine=br.readLine();
                        while(currentLine!=null){
                            System.out.println(currentLine);
                            currentLine=br.readLine();
                        }*/
                        request.setAttribute("value",value);
                        request.setAttribute("data", data);
                        request.getRequestDispatcher("tableEntry.jsp").include(request, response);
                    }else{
                        request.setAttribute("value",value);
                        request.setAttribute("data", data);
                        //out.print("<script>alert(\"There is some problem. Please try again later.\");</script>");
                        request.getRequestDispatcher("tableEntry.jsp").include(request, response);
                    }
                    
                }
                
                
                //request.getRequestDispatcher("tableEntry.jsp").include(request, response);
            }
            else if(request.getParameter("back")!=null){
                request.getRequestDispatcher("pdfOptions.html").forward(request, response);
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
