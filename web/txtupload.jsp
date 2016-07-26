<%-- 
    Document   : csvupload
    Created on : Jul 15, 2016, 1:37:15 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <script src="js/bootstrap.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CSV Upload</title>
    </head>
    <body>
        
        <nav style="border-color:#e7e7e7; background-color:#f8f8f8; border-radius:4px; min-height:50px; margin-bottom: 20px; border:1px solid transparent; display:block; ">
            <div style="padding-right:15px; padding-left:15px; margin-left:auto; margin-right:auto;">
                <div style="border-color:#e7e7e7; margin-right:0; margin-left:0; display:block; height: auto; padding-bottom:0; overflow:visible; width:auto; border-top:0; box-shadow:none; padding-right:15px; padding-left:15px; margin-top:15px;">
                    <ul style="float:right; padding-left:0; list-style:none; box-sizing:border-box;">
                        <li style="display:inline; margin:10px; background:#e7efff;"><a href="pdfOptions.html" style="text-decoration:none;">PDF</a></li>
                        <li style="display:inline; margin:10px;"><a href="csvupload.jsp" style="text-decoration:none;">CSV</a></li>
                        <li style="display:inline; margin:10px;"><a href="txtupload.jsp" style="text-decoration:none;">TXT</a></li>
                        <li style="display:inline; margin:10px;"><a href="logoutServlet" style="text-decoration:none;">Logout</a></li>
                    </ul>
                </div>
            </div>
            
        </nav>
        <h1 class="text-center" style="font-size: 30px; margin-top: 50px;">Upload File</h1>
	<form class="form-horizontal" style="margin-top:100px;" name="form1" id="form1" action="txtfileupload" method="post" enctype="multipart/form-data">
            
            <br/>
            <div class="form-group">
                <div class="col-sm-offset-5 col-sm-7">
                    <input type="file" size="50" name="file1">
            
            </div>
            </div>
            <br/>
             <div class="form-group">
                <div class="col-sm-offset-5 col-sm-7">
                <button type="submit" class="btn btn-default" value="Upload">Upload</button>
                </div>
            </div>
	</form>
    </body>
</html>
