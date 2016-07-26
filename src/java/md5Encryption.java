
import java.security.MessageDigest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author LENOVO
 */
public class md5Encryption {
    
    static String encrypt(String pass){
        String password=pass;
        try{
            MessageDigest md=MessageDigest.getInstance("MD5");
            md.update(pass.getBytes());

            byte byteData[]=md.digest();

            StringBuffer sb= new StringBuffer();

            for(int i=0;i<byteData.length;i++){
                sb.append(Integer.toString((byteData[i] & 0xff)+0x100,16).substring(1));
            }

            password=sb.toString();
            return password;
        }catch(Exception e){
            return password;
        }
        
    }
    
}
