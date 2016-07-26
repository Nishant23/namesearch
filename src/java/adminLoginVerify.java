
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class adminLoginVerify {
    
    public static boolean validate(String userid, String password){
        boolean status=false;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            String url="jdbc:oracle:thin:@localhost:1521:test";
            String u="Nishant1";
            String p="Incorrect23";
            Connection con=DriverManager.getConnection(url,u,p);
            
            PreparedStatement ps=con.prepareStatement("select * from adminlogin where username=? and password=?");
            ps.setString(1, userid);
            ps.setString(2, password);
            ResultSet rs=ps.executeQuery();
            status=rs.next();
            System.out.print(status);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return status;
    }
    
}
