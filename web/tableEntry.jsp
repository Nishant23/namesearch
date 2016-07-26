<%-- 
    Document   : tableEntry.jsp
    Created on : Jul 7, 2016, 10:46:44 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="javax.servlet.http.HttpSession" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/bootstrap.min.css">
       <script src="js/bootstrap.min.js"></script>
        <title>Enter data</title>
    </head>
    <body>
        <nav style="border-color:#e7e7e7; background-color:#f8f8f8; border-radius:4px; min-height:50px; margin-bottom: 20px; border:1px solid transparent; display:block; ">
            <div style="padding-right:15px; padding-left:15px; margin-left:auto; margin-right:auto;">
                <div style="border-color:#e7e7e7; margin-right:0; margin-left:0; display:block; height: auto; padding-bottom:0; overflow:visible; width:auto; border-top:0; box-shadow:none; padding-right:15px; padding-left:15px; margin-top:15px;">
                    <ul style="float:right; padding-left:0; list-style:none; box-sizing:border-box;">
                        <li style="display:inline; margin:10px;"><a href="logoutServlet" style="text-decoration:none;">Logout</a></li>
                    </ul>
                </div>
            </div>
            
        </nav>
        <h1 class="text-center" style="font-size: 30px; margin-top: 50px;">Enter Data</h1>
        
        <form class="form-horizontal" action="processingServlet" method=POST style="margin-top: 30px;">
            
            <%
            //System.out.println("Success");
            ArrayList mainData=new ArrayList();
            ArrayList mainValue=new ArrayList();
            try{
                mainData=(ArrayList)request.getAttribute("data");
                mainValue=(ArrayList)request.getAttribute("value");
                //System.out.println(mainData);}
            }
            catch(Exception e){
                e.printStackTrace();
            }
            
            int l=mainData.size();
            for(int i=0;i<l;i++){
            
            %>
            <tr>
                <td>
                    
                    <div class="form-group">
                        <label class="col-sm-2 control-label"><%=mainData.get(i).toString()%>:</label>
                        <div class="col-sm-8">
                                <input type=text name="<%=mainData.get(i).toString()%>" value="<%=mainValue.get(i).toString()%>" class="form-control">
                        </div>
                    </div>
                    
                     
                    
                
                    <div class="form-group">
                        
                    </div>
                    
                </td><br><br>
            </tr>
            <%}%>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="submit" name="search" value="Search"  class="btn btn-default">
                    <input type="submit" name="more" value="Enter More Data" class="btn btn-default">
                    <input type="submit" name="back" value="Back" class="btn btn-default">
                </div>
            </div>
        </form>
    </body>
</html>
