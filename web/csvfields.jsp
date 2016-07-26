<%-- 
    Document   : csvfields
    Created on : Jul 15, 2016, 3:26:19 PM
    Author     : Administrator
--%>

<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.FileReader"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <script src="js/bootstrap.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CSV Fields</title>
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
        
        <%
        String name=session.getAttribute("userid").toString();
        FileReader file=new FileReader(name);
        BufferedReader br=new BufferedReader(file);
        String currentLine=br.readLine();
        if(currentLine!=null){
            String[] data=currentLine.split(",");
            int l=data.length;
            String formName="field";
            //option array define krna bacha h.
        %>
            <form class="form-horizontal" style="margin-top:100px;" name="form1" id="form1" action="csvread" method="post" enctype="multipart/form-data">
            <%
            for(int i=0;i<l;i++){
            %>
                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-5 control-label"><%=data[i]%></label>
                    <select name="<%=formName+i%>">
                        <option>Name</option>
                        <option>PAN No</option>
                        <option>Mobile Number</option>
                        <option>Aadhar Card Number/UID Number</option>
                        <option>Voter ID</option>
                        <option>Email ID</option>
                        <option>Address</option>
                    </select>
                </div>
            
        
                
        <%                       
            }
        %>
                <div class="form-group">
                    <div class="col-sm-offset-5 col-sm-1">
                        <button type="submit" class="btn btn-default" name="submit">Submit</button>
                    </div>
                    <div class="col-sm-offset-1 col-sm-5">
                        <button type="submit" class="btn btn-default" name="back">Back</button>
                    </div>
                </div>
                <div class="form-group">
                    
                </div>
            </form>
        <%    
        }else{
        %>
        No content in file.
        <a href="csvupload.jsp">Go back</a>
        <%    
        }
        br.close();
        file.close();
        %>
    </body>
</html>
