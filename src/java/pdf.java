
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
public class pdf {
    static void fileUpload(String name){
        try{
            Connection con=connect.getConnection();
            String query="select * from "+name;
            ResultSet rs=con.prepareStatement(query).executeQuery();
            //code left to write
            String file_name=name+".doc";
            FileWriter file=new FileWriter(file_name,true);
            BufferedWriter writeFile=new BufferedWriter(file);
            String writeData=new String();
            String temp1;
            /*temp1="name1| mid_name| name2| cif| pan| phone_no_res| phone_no_bus| telex_no| email_id1| email_id2| add1| add2| add3| add4| home_branch_no\n";
            writeData=temp1.replace("\n", System.lineSeparator());
            file.write(writeData);*/
            while(rs.next()){
                String temp="Name: "+rs.getString("name1").trim()+" "+rs.getString("mid_name").trim()+" "+rs.getString("name2").trim()+"\nCIF: "+rs.getString("cif").trim().replace(",", "")
                        +
                        "\nPAN: "+rs.getString("pan").trim()+"\nPhone No.: "+rs.getString("phone_no_res").trim()+", "+rs.getString("phone_no_bus").trim()+", "
                        +rs.getString("telex_no")+"\nEmail ID: "+rs.getString("email_id1").trim()+", "+rs.getString("email_id2").trim()+"\nAddress: "+rs.getString("add1").trim()+" "
                        +rs.getString("add2").trim()+" "+rs.getString("add3").trim()+" "+rs.getString("add4").trim()+"\nhome branch no: "+rs.getString("home_branch_no").trim()+"\nHome branch Name: "+rs.getString("br_name");
                String acct=rs.getString("key1").trim();
                int len=acct.length();
                if(len>11)
                    temp=temp+"\nAccount Number: "+acct.substring(len-11).replaceAll(",","")+"\n\n\n\n";
                else{
                    temp=temp+"\nAccount Number: \n\n\n\n";
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
