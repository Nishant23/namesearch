
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class LoginDao {
    
    public static boolean validate(String userid, String pass){
        boolean status=false;
        try{
            
            Connection con=connect.getConnection();
            String password=md5Encryption.encrypt(pass);
            
            PreparedStatement ps=con.prepareStatement("select * from login where userid=? and password=?");
            ps.setString(1, userid);
            ps.setString(2, password);
            ResultSet rs=ps.executeQuery();
            status=rs.next();
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return status;
    }
    
}
