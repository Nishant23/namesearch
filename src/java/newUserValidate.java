
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
public class newUserValidate {
    public static boolean validate(String userid){
        boolean status=false;
        try{
            Connection con=connect.getConnection();
            
            PreparedStatement ps=con.prepareStatement("select * from login where userid=?");
            ps.setString(1, userid);
            ResultSet rs=ps.executeQuery();
            status=rs.next();
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return status;
    }
}
