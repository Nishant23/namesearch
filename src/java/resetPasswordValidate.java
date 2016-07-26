
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author LENOVO
 */
public class resetPasswordValidate {
    
    public static boolean validate(String userid, String pass){
        boolean status=false;
        try{
            
            Connection con=connect.getConnection();
            String password=md5Encryption.encrypt(pass);

            ResultSet rs=con.prepareStatement("UPDATE LOGIN SET PASSWORD='"+password+"' WHERE USERID=('"+userid+"')").executeQuery();
            status=rs.next();
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return status;
    }
    
}
