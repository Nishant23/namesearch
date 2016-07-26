
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import oracle.jdbc.OracleConnection;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class deleteTable {
    static void delete(String name){
        Connection con;
            try{
                con=connect.getConnection();
                //System.out.println("Fuck");
                //System.out.println(con);
                System.out.println(name);
                
                con.prepareStatement("delete from "+name).executeQuery();
                con.commit();
                System.out.println("Fuck");
                con.close();
                
            }catch(Exception e){
               e.printStackTrace();
            }
    }
}
