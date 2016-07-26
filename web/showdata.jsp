<%-- 
    Document   : showdata
    Created on : Jul 15, 2016, 10:44:12 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="javax.servlet.http.HttpSession" %>

<%@page import="java.sql.Connection" %>
<%@page import="java.sql.ResultSet" %>
<%@page import="java.sql.DriverManager" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/bootstrap.min.css">
       <script src="js/bootstrap.min.js"></script>
        <title>Show data</title>
    </head>
    <body>
        <nav style="border-color:#e7e7e7; background-color:#f8f8f8; border-radius:4px; min-height:50px; margin-bottom: 20px; border:1px solid transparent; display:block; ">
            <div style="padding-right:15px; padding-left:15px; margin-left:auto; margin-right:auto;">
                <div style="border-color:#e7e7e7; margin-right:0; margin-left:0; display:block; height: auto; padding-bottom:0; overflow:visible; width:auto; border-top:0; box-shadow:none; padding-right:15px; padding-left:15px; margin-top:15px;">
                    <ul style="float:right; padding-left:0; list-style:none; box-sizing:border-box;">
                        <li style="display:inline; margin:10px;"><a href="pdfOptions.html" style="text-decoration:none;">Home</a></li>
                 <!--       <li style="display:inline; margin:10px;"><a href="downloadcsv" style="text-decoration:none;">CSV</a></li>
                        <li style="display:inline; margin:10px;"><a href="downloadtxt" style="text-decoration:none;">TXT</a></li>
                        <li style="display:inline; margin:10px;"><a href="downloaddoc" style="text-decoration:none;">DOC</a></li>-->
                        <li style="display:inline; margin:10px;"><a href="logoutServlet" style="text-decoration:none;">Logout</a></li>
                    </ul>
                </div>
            </div>
            
        </nav>
        
        <%
        Connection con;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://10.1.20.240\\sqlexpress";
            String u="";
            String p="";
            con=DriverManager.getConnection(url,u,p);
        
        %>
        <table border="2" style="margin-left: 40px;">
            <tr>
                <td>Name</td>
                <td>CIF</td>
                <td>PAN</td>
                <td>PHONE NUMBERS</td>
                <td>EMAIL ID</td>
                <td>ADDRESS</td>
                <td>HOME BRANCH NO.</td>
                <td>HOME BRANCH NAME</td>
                <td>ACCOUNT NUMBER</td>
            </tr>
            
                <%
                String name=session.getAttribute("userid").toString();
                String query="select * from "+name;
                ResultSet rs=con.prepareStatement(query).executeQuery();
                while(rs.next()){
                    String Name=rs.getString("name1").trim()+" "+rs.getString("mid_name").trim()+" "+rs.getString("name2").trim();
                    String cif=rs.getString("cif").trim().replaceAll(",", "");
                    String pan=rs.getString("pan").trim();
                    String phone=rs.getString("phone_no_res").trim()+", "+rs.getString("phone_no_bus")+", "+rs.getString("telex_no");
                    String email=rs.getString("email_id1").trim()+", "+rs.getString("email_id2").trim();
                    String address=rs.getString("add1").trim()+"\n"+rs.getString("add2").trim()+"\n"+rs.getString("add3")+"\n"+rs.getString("add4");
                    String branchNo=rs.getString("home_branch_no").trim();
                    String brName=rs.getString("br_name").trim();
                    String accNo=rs.getString("key1").trim();
                    int len=accNo.length();
                    String acc=new String();
                    if(len>11){
                        acc=accNo.substring(len-11).replaceAll(",","");
                    }else{
                        acc="  ";
                    }
                %>
          <tr>
                <td><%=Name%></td>
                <td><%=cif%></td>
                <td><%=pan%></td>
                <td><%=phone%></td>
                <td><%=email%></td>
                <td><%=address%></td>
                <td><%=branchNo%></td>
                <td><%=brName%></td>
                <td><%=acc%></td>
                <td><%="   "%></td>
        <%        
                
                }
                con.close();
        }catch(Exception e){
            
        }
                %>
            </tr>
        </table>
          
            
            <h1 class="text-center" style="font-size: 25px; margin-top: 30px;">SELECT DOWNLOAD OPTION:</h1>
        <form class="form-horizontal" style="margin-top:20px;" action="downloadoption" method="post" name="signup">
            
            
            <div class="form-group">
                <div class="col-sm-offset-5 col-sm-7">
                    <input type="submit" class="btn btn-default" value="csv" name ="csv">
                    <input type="submit" class="btn btn-default" value="txt" name ="txt">
                    <input type="submit" class="btn btn-default" value="doc" name ="doc">
                </div>
            </div>
            
         </form>
        
        
    </body>
</html>
