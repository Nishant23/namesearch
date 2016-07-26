/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
public class addUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        
        HttpSession session=request.getSession(false);
        if(session!=null){
            String name=session.getAttribute("userid").toString();
            if(name.compareTo("admin")==0){
                String userid=request.getParameter("userid").trim();
                String password=request.getParameter("password").trim();

                if(userid.length()>=4 && userid.length()<=16){

                    boolean flag=true;
                    int l= userid.length();
                    String str1=userid.toLowerCase();
                    for(int i=0;i<l;i++){
                        if(((int)str1.charAt(i)>=97 && (int)str1.charAt(i)<=122) || str1.charAt(i)=='@' || str1.charAt(i)=='!' || str1.charAt(i)=='_'||(str1.charAt(i)>=49 && str1.charAt(i)<=58)){
                            continue;
                        }else{
                            flag=false;
                            break;
                        }
                    }
                    if(flag){
                        if(password.length()>=8 && password.length()<=16){
                            if(newUserValidate.validate(userid)){
                                out.println("<h5>User ID already exists.</h5>");
                                request.getRequestDispatcher("adminpage.html").include(request, response);
                            }else{
                                if(userCreateValidate.validate(userid,password)){
                                    //modify it at home
                                    Connection con=connect.getConnection();
                                    String query;
                                    query="create table "+userid+"("
                                            + "name1 char(40 byte),"
                                            + "mid_name char(40 byte),"
                                            + "name2 char(40 byte),"
                                            + "CIF CHAR(16 BYTE),"
                                            + "PAN CHAR(20 BYTE),"
                                            + "PHONE_NO_RES CHAR(12 BYTE),"
                                            + "PHONE_NO_BUS CHAR(12 BYTE),"
                                            + "TELEX_NO CHAR(12 BYTE),"
                                            + "EMAIL_ID1 CHAR(100 BYTE),"
                                            + "EMAIL_ID2 CHAR(100 BYTE),"
                                            + "ADD1 CHAR(40 BYTE),"
                                            + "ADD2 CHAR(40 BYTE),"
                                            + "ADD3 CHAR(40 BYTE),"
                                            + "ADD4 CHAR(40 BYTE),"
                                            + "HOME_BRANCH_NO CHAR(5 BYTE),"
                                            +"BR_NAME VARCHAR2(40 BYTE)," 
                                            +"KEY1 CHAR(30 BYTE)"
                                            + ")";
                                    try{
                                        con.prepareStatement(query).executeQuery();
                                        RequestDispatcher rd=request.getRequestDispatcher("WelcomeServlet");
                                        rd.forward(request, response);
                                    }
                                    catch(Exception e){
                                       e.printStackTrace();
                                       RequestDispatcher rd=request.getRequestDispatcher("adminpage.html");
                                        rd.forward(request, response);
                                    }
                                    
                                }else{
                                    out.print("<p>Unable to process request right now. Please try after some time</p>");
                                    request.getRequestDispatcher("adminPage.html").include(request, response);
                                }


                            }
                        }else{
                            out.print("<script>alert(\"Password length should be between 8 to 16 characters\");</script>");
                            request.getRequestDispatcher("adminPage.html").include(request, response);
                        }
                    }else{
                        out.print("<script>alert(\"Please enter a valid username. Username must of length between 4 to 16. It should only contain alphanumeric characters, @, _ and !\");</script>");
                        request.getRequestDispatcher("adminPage.html").include(request, response);
                    }

                }else{
                    out.print("<script>alert(\"Please enter a valid username. Username must of length between 4 to 16. It should only contain alphanumeric characters, @, _ and !\");</script>");
                    request.getRequestDispatcher("adminPage.html").include(request, response);
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

}
