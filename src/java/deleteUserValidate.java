
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
public class deleteUserValidate {
    public static boolean validate(String userid){
        boolean status=false;
        try{
            Connection con=connect.getConnection();
            ResultSet rs=con.prepareStatement("DELETE FROM LOGIN WHERE USERID=('"+userid+"')").executeQuery();
            status=rs.next();
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return status;
    }
}
