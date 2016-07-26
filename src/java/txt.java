
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
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
public class txt {
    static void fileUpload(String name){
        try{
            Connection con=connect.getConnection();
            String query="select * from "+name;
            ResultSet rs=con.prepareStatement(query).executeQuery();
            //code left to write
            String file_name=name+".txt";
            FileWriter file=new FileWriter(file_name,true);
            BufferedWriter writeFile=new BufferedWriter(file);
            String writeData=new String();
            String temp1;
            temp1="nName| CIF| PAN| Phone No| Email ID| Address| Home Branch Number| Home Branch Name| Account Number\n";
            writeData=temp1.replace("\n", System.lineSeparator());
            file.write(writeData);
            while(rs.next()){
                String temp="\""+rs.getString("name1").trim()+" "+rs.getString("mid_name").trim()+" "+rs.getString("name2").trim()+"\"|\""+rs.getString("cif").replaceAll(",", "")
                        +
                        "\"|\""+rs.getString("pan")+"\"|\""+rs.getString("phone_no_res").trim()+","+rs.getString("phone_no_bus").trim()+","
                        +rs.getString("telex_no").trim()+"\"|\""+rs.getString("email_id1").trim()+","+rs.getString("email_id2").trim()+"\"|\""+rs.getString("add1").trim()+" "
                        +rs.getString("add2").trim()+" "+rs.getString("add3").trim()+" "+rs.getString("add4").trim()+"\"|\""+rs.getString("home_branch_no").trim()+"\"|\""+rs.getString("br_name").trim() ;
                String acct=rs.getString("key1").trim();
                int len=acct.length();
                if(len>11)
                    temp=temp+"\"|\""+acct.substring(len-11).replaceAll(",", "") +"\"\n";
                else{
                    temp=temp+"\"|\""+"  " +"\"\n";
                }
                writeData=temp.replace("\n", System.lineSeparator());
                file.write(writeData);
            }
            file.close();
            writeFile.close();
            query="select * from files where file_name='"+file_name+"'";
            rs=con.prepareStatement(query).executeQuery();
            boolean next=rs.next();
            System.out.println(next);
            
            if(next){
                System.out.println(rs.getString("file_name"));
                System.out.println("deleting");
                query="delete from files where file_name='"+file_name+"'";
                con.prepareStatement(query).executeQuery();
                con.commit();
            }
            PreparedStatement ps=con.prepareStatement("insert into files values(?,?)");
            File f=new File(file_name);
            FileReader fr=new FileReader(f);
            ps.setString(1,file_name);
            ps.setCharacterStream(2, fr, (int)f.length());
            ps.executeQuery();
            con.commit();
            fr.close();
            f.delete();
            con.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
