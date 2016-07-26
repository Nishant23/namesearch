
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import oracle.jdbc.OracleConnection;

public class dbConnect {
    
    public static Connection getConnection(){
        
            Connection con;
            try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            }catch(Exception e){
                
                return null;
            }
            try{
       
                String url="jdbc:sqlserver://10.1.20.240\\sqlexpress";
                String u="";
                String p="";
                con=DriverManager.getConnection(url,u,p);
                ResultSet rs=con.prepareStatement("select * from dbconfig").executeQuery();
                rs.next();
                String ip=rs.getString("ip").trim();
                String port=rs.getString("port").trim();
                String username=rs.getString("username").trim();
                String password=rs.getString("password").trim();
                rs.close();
                con.close();
                String URL="jdbc:oracle:thin:@"+ip+":"+port+":c012band1";
                con=DriverManager.getConnection(URL,username,password);
                //System.out.println("Fuck");
                //System.out.println(con);
                
                return con;
                
            }catch(Exception e){
               return null;
            }
    }
}
