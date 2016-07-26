
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class userCreateValidate {
    public static boolean validate(String userid, String pass){
        boolean status=false;
        try{
            
            Connection con=connect.getConnection();
            String password=md5Encryption.encrypt(pass);

            ResultSet rs=con.prepareStatement("INSERT INTO LOGIN (USERID, PASSWORD) VALUES ('"+userid+"', '"+password+"')").executeQuery();
            status=rs.next();
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return status;
    }
}
