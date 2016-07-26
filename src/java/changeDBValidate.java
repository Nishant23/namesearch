
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
public class changeDBValidate {
    
    public static boolean validate(String ip, String port,String username, String password){
        boolean status=false;
        try{
            
            Connection con=connect.getConnection();
           
            PreparedStatement ps=con.prepareStatement("select * from DBCONFIG");
            ResultSet rs=ps.executeQuery();
            System.out.println("qwerty");
            if(rs.next()){
                ps=con.prepareStatement("delete from dbconfig");
                rs=ps.executeQuery();
                ps=con.prepareStatement("INSERT INTO dbconfig (ip, port, username, password) VALUES (?,?,?,?)");
                
                ps.setString(1, ip);
                ps.setString(2,port);
                ps.setString(3, username);
                ps.setString(4,password);
                rs=ps.executeQuery();
                status=rs.next();
            }else{
                ps=con.prepareStatement("INSERT INTO dbconfig (ip, port, username, password) VALUES (?,?,?,?)");
                
                ps.setString(1, ip);
                ps.setString(2,port);
                ps.setString(3, username);
                ps.setString(4,password);
                rs=ps.executeQuery();
                status=rs.next();
            }
            rs.close();
            con.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return status;
    }
    
}
