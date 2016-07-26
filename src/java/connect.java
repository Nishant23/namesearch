
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import oracle.jdbc.OracleConnection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author LENOVO
 */
public class connect {
    
    public static Connection getConnection(){
        
            Connection con;
            try{
                //Class.forName("oracle.jdbc.driver.OracleDriver");
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            }catch(Exception e){
                
                return null;
            }
            try{
       
                String url="jdbc:sqlserver://10.1.20.240\\sqlexpress";
                String u="";
                String p="";
                con=DriverManager.getConnection(url,u,p);
                
                //System.out.println("Fuck");
                //System.out.println(con);
                
                return con;
                
            }catch(Exception e){
               return null;
            }
    }
    
}
