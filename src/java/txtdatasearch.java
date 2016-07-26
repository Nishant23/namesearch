
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class txtdatasearch {
    
    static void search(String currentLine, ArrayList option, String name){
        try{
            Connection con=dbConnect.getConnection();
            String[] data=currentLine.split("\\|");
            boolean flag=false;
            ResultSet rs;
            String name1, mid_name, home_branch_name, account_no, name2, cif, cust_pan, phone_no_res, phone_no_bus, telex_no, email_id1, email_id2, add1, add2, add3, add4,home_branch_no;
            String query;
                
                if(option.contains("PAN No")){
                    int i=option.indexOf("PAN No");
                    String pan=data[i].toString().toUpperCase().trim();
                    
                    query="select cust_acct_no, home_branch_no, cust_tax_pan from cusm where cust_tax_pan=('" +pan+"')";
                    rs=con.prepareStatement(query).executeQuery();
                    if(rs.next()){
                        flag=true;
                        cif=rs.getString("cust_acct_no");
                        cust_pan=pan;
                        home_branch_no=rs.getString("home_branch_no");
                        query="select name1,mid_name,name2,phone_no_res,phone_no_bus,telex_no,add1,add2,add3,add4 from cusvaa where cust_no=('"+cif+"')";
                        rs=con.prepareStatement(query).executeQuery();
                        if(rs.next()){
                            name1=rs.getString("name1");
                            name2=rs.getString("name2");
                            mid_name=rs.getString("mid_name");
                            phone_no_res=rs.getString("phone_no_res");
                            phone_no_bus=rs.getString("phone_no_bus");
                            telex_no=rs.getString("telex_no");
                            add1=rs.getString("add1");
                            add2=rs.getString("add2");
                            add3=rs.getString("add3");
                            add4=rs.getString("add4");
                        }else{
                            name1=null;
                            name2=null;
                            mid_name=null;
                            phone_no_res=null;
                            phone_no_bus=null;
                            telex_no=null;
                            add1=null;
                            add2=null;
                            add3=null;
                            add4=null;
                        }
                        query="select email_add1, email_add2 from cumi where cust_no=('"+cif+"')";
                        rs=con.prepareStatement(query).executeQuery();
                        if(rs.next()){
                            email_id1=rs.getString("email_add1");
                            email_id2=rs.getString("email_add2");
                            
                        }else{
                            email_id1=null;
                            email_id2=null;
                        }
                       query="select br_name from br_master_report where br_code='"+home_branch_no+"'";
                        rs=con.prepareStatement(query).executeQuery();
                        if(rs.next()){
                            home_branch_name=rs.getString("br_name");
                        }else{
                            home_branch_name=null;
                        }
                        rs.close();
                        query="select key_1 from accounts where customer_no='"+cif+"'";
                        rs=con.prepareStatement(query).executeQuery();
                        if(rs.next()){
                            account_no=rs.getString("key_1");
                            
                        }else{
                            account_no=null;
                        }
                        con.close();
                        con=connect.getConnection();
                        query="Insert into "+name+" values('"+name1+"','"+mid_name+"','"+name2+"','"+cif+"','"+cust_pan+"','"+phone_no_res+"','"+phone_no_bus+"','"+telex_no+"','"+email_id1+"','"+email_id2+"','"+add1+"','"+add2+"','"+add3+"','"+add4+"','"+home_branch_no+"','"+home_branch_name+"','"+account_no+"')";
                        con.prepareStatement(query).executeQuery();
                    }else{
                        flag=false;
                    }
                    
                }
                
                if(!flag){
                    if(option.contains("Email ID")){
                        int i=option.indexOf("Email ID");
                        String email=data[i].toString().toLowerCase().trim();
                        //code
                        query="select email_add1, email_add2,cust_no from cumi where email_add1=('"+email+"') or email_add2=('"+email+"')";
                        rs=con.prepareStatement(query).executeQuery();
                        if(rs.next()){
                            flag=true;
                            email_id1=rs.getString("email_add1");
                            email_id2=rs.getString("email_add2");
                            cif=rs.getString("cust_no");
                            
                            query="select name1,mid_name,name2,phone_no_res,phone_no_bus,telex_no,add1,add2,add3,add4 from cusvaa where cust_no=('"+cif+"')";
                            rs=con.prepareStatement(query).executeQuery();
                            if(rs.next()){
                                name1=rs.getString("name1");
                                name2=rs.getString("name2");
                                mid_name=rs.getString("mid_name");
                                phone_no_res=rs.getString("phone_no_res");
                                phone_no_bus=rs.getString("phone_no_bus");
                                telex_no=rs.getString("telex_no");
                                add1=rs.getString("add1");
                                add2=rs.getString("add2");
                                add3=rs.getString("add3");
                                add4=rs.getString("add4");
                            }else{
                                name1=null;
                                name2=null;
                                mid_name=null;
                                phone_no_res=null;
                                phone_no_bus=null;
                                telex_no=null;
                                add1=null;
                                add2=null;
                                add3=null;
                                add4=null;
                            }
                            query="select home_branch_no, cust_tax_pan from cusm where cust_acct_no=('" +cif+"')";
                            rs=con.prepareStatement(query).executeQuery();
                            if(rs.next()){
                                home_branch_no=rs.getString("home_branch_no");
                                cust_pan=rs.getString("cust_tax_pan");
                            }else{
                                home_branch_no=null;
                                cust_pan=null;
                            }
                            query="select br_name from br_master_report where br_code='"+home_branch_no+"'";
                            rs=con.prepareStatement(query).executeQuery();
                            if(rs.next()){
                                home_branch_name=rs.getString("br_name");
                            }else{
                                home_branch_name=null;
                            }
                            rs.close();
                            query="select key_1 from accounts where customer_no='"+cif+"'";
                            rs=con.prepareStatement(query).executeQuery();
                            if(rs.next()){
                                account_no=rs.getString("key_1");

                            }else{
                                account_no=null;
                            }
                            con.close();
                            con=connect.getConnection();
                            query="Insert into "+name+" values('"+name1+"','"+mid_name+"','"+name2+"','"+cif+"','"+cust_pan+"','"+phone_no_res+"','"+phone_no_bus+"','"+telex_no+"','"+email_id1+"','"+email_id2+"','"+add1+"','"+add2+"','"+add3+"','"+add4+"','"+home_branch_no+"','"+home_branch_name+"','"+account_no+"')";
                            con.prepareStatement(query).executeQuery();
                        }else{
                            flag=false;
                        }
                    }
                }
                
                if(!flag){
                    if(option.contains("Aadhar Card Number/UID Number")){
                        int i=option.indexOf("Aadhar Card Number/UID Number");
                        String uid=data[i].toString().trim();
                        //code
                        query="select cif_uid from uidl where uid_no=('"+uid+"')";
                        rs=con.prepareStatement(query).executeQuery();
                        if(rs.next()){
                            flag=true;
                            cif=rs.getString("cif_uid");
                            query="select name1,mid_name,name2,phone_no_res,phone_no_bus,telex_no,add1,add2,add3,add4 from cusvaa where cust_no=('"+cif+"')";
                            rs=con.prepareStatement(query).executeQuery();
                            if(rs.next()){
                                name1=rs.getString("name1");
                                name2=rs.getString("name2");
                                mid_name=rs.getString("mid_name");
                                phone_no_res=rs.getString("phone_no_res");
                                phone_no_bus=rs.getString("phone_no_bus");
                                telex_no=rs.getString("telex_no");
                                add1=rs.getString("add1");
                                add2=rs.getString("add2");
                                add3=rs.getString("add3");
                                add4=rs.getString("add4");
                            }else{
                                name1=null;
                                name2=null;
                                mid_name=null;
                                phone_no_res=null;
                                phone_no_bus=null;
                                telex_no=null;
                                add1=null;
                                add2=null;
                                add3=null;
                                add4=null;
                            }
                            query="select home_branch_no, cust_tax_pan from cusm where cust_acct_no=('" +cif+"')";
                            rs=con.prepareStatement(query).executeQuery();
                            if(rs.next()){
                                home_branch_no=rs.getString("home_branch_no");
                                cust_pan=rs.getString("cust_tax_pan");
                            }else{
                                home_branch_no=null;
                                cust_pan=null;
                            }
                            query="select email_add1, email_add2 from cumi where cust_no=('"+cif+"')";
                            rs=con.prepareStatement(query).executeQuery();
                            if(rs.next()){
                                email_id1=rs.getString("email_add1");
                                email_id2=rs.getString("email_add2");

                            }else{
                                email_id1=null;
                                email_id2=null;
                            }
                            query="select br_name from br_master_report where br_code='"+home_branch_no+"'";
                            rs=con.prepareStatement(query).executeQuery();
                            if(rs.next()){
                                home_branch_name=rs.getString("br_name");
                            }else{
                                home_branch_name=null;
                            }
                            rs.close();
                            query="select key_1 from accounts where customer_no='"+cif+"'";
                            rs=con.prepareStatement(query).executeQuery();
                            if(rs.next()){
                                account_no=rs.getString("key_1");

                            }else{
                                account_no=null;
                            }
                            con.close();
                            con=connect.getConnection();
                            query="Insert into "+name+" values('"+name1+"','"+mid_name+"','"+name2+"','"+cif+"','"+cust_pan+"','"+phone_no_res+"','"+phone_no_bus+"','"+telex_no+"','"+email_id1+"','"+email_id2+"','"+add1+"','"+add2+"','"+add3+"','"+add4+"','"+home_branch_no+"','"+home_branch_name+"','"+account_no+"')";
                            con.prepareStatement(query).executeQuery();
                        }else{
                            flag=false;
                        }
                    }
                }
                
                if(!flag){
                    if(option.contains("Voter ID")){
                        int i=option.indexOf("Voter ID");
                        String voterID=data[i].toString().toLowerCase().trim();
                        //code
                        
                        query="select cust_acct_no, home_branch_no, cust_tax_pan from cusm where cust_voter_id=('" +voterID+"')";
                        rs=con.prepareStatement(query).executeQuery();
                        if(rs.next()){
                            flag=true;
                            cif=rs.getString("cust_acct_no");
                            cust_pan=rs.getString("cust_tax_pan");
                            home_branch_no=rs.getString("home_branch_no");
                            query="select name1,mid_name,name2,phone_no_res,phone_no_bus,telex_no,add1,add2,add3,add4 from cusvaa where cust_no=('"+cif+"')";
                            rs=con.prepareStatement(query).executeQuery();
                            if(rs.next()){
                                name1=rs.getString("name1");
                                name2=rs.getString("name2");
                                mid_name=rs.getString("mid_name");
                                phone_no_res=rs.getString("phone_no_res");
                                phone_no_bus=rs.getString("phone_no_bus");
                                telex_no=rs.getString("telex_no");
                                add1=rs.getString("add1");
                                add2=rs.getString("add2");
                                add3=rs.getString("add3");
                                add4=rs.getString("add4");
                            }else{
                                name1=null;
                                name2=null;
                                mid_name=null;
                                phone_no_res=null;
                                phone_no_bus=null;
                                telex_no=null;
                                add1=null;
                                add2=null;
                                add3=null;
                                add4=null;
                            }
                            query="select email_add1, email_add2 from cumi where cust_no=('"+cif+"')";
                            rs=con.prepareStatement(query).executeQuery();
                            if(rs.next()){
                                email_id1=rs.getString("email_add1");
                                email_id2=rs.getString("email_add2");

                            }else{
                                email_id1=null;
                                email_id2=null;
                            }
                            query="select br_name from br_master_report where br_code='"+home_branch_no+"'";
                            rs=con.prepareStatement(query).executeQuery();
                            if(rs.next()){
                                home_branch_name=rs.getString("br_name");
                            }else{
                                home_branch_name=null;
                            }
                            rs.close();
                            query="select key_1 from accounts where customer_no='"+cif+"'";
                            rs=con.prepareStatement(query).executeQuery();
                            if(rs.next()){
                                account_no=rs.getString("key_1");

                            }else{
                                account_no=null;
                            }
                            con.close();
                            con=connect.getConnection();
                            query="Insert into "+name+" values('"+name1+"','"+mid_name+"','"+name2+"','"+cif+"','"+cust_pan+"','"+phone_no_res+"','"+phone_no_bus+"','"+telex_no+"','"+email_id1+"','"+email_id2+"','"+add1+"','"+add2+"','"+add3+"','"+add4+"','"+home_branch_no+"','"+home_branch_name+"','"+account_no+"')";
                            con.prepareStatement(query).executeQuery();
                        }else{
                            flag=false;
                        }
                        
                    }
                }
                
                if(!flag){
                    if(option.contains("Mobile Number")){
                        int i=option.indexOf("Mobile Number");
                        String phNo=option.get(i).toString().trim();
                        //code
                        
                        query="select name1,mid_name,name2,phone_no_res,phone_no_bus,telex_no,add1,add2,add3,add4,cust_no from cusvaa where phone_no_res like '%"+ phNo+"%' or phone_no_bus like '%"+phNo+"%' or telex_no like '%"+phNo+"%'";
                        rs=con.prepareStatement(query).executeQuery();
                        if(rs.next()){
                            flag=true;
                            name1=rs.getString("name1");
                            name2=rs.getString("name2");
                            mid_name=rs.getString("mid_name");
                            phone_no_res=rs.getString("phone_no_res");
                            phone_no_bus=rs.getString("phone_no_bus");
                            telex_no=rs.getString("telex_no");
                            add1=rs.getString("add1");
                            add2=rs.getString("add2");
                            add3=rs.getString("add3");
                            add4=rs.getString("add4");
                            cif=rs.getString("cust_no");
                            query="select email_add1, email_add2 from cumi where cust_no=('"+cif+"')";
                            rs=con.prepareStatement(query).executeQuery();
                            if(rs.next()){
                                email_id1=rs.getString("email_add1");
                                email_id2=rs.getString("email_add2");

                            }else{
                                email_id1=null;
                                email_id2=null;
                            }
                            query="select home_branch_no, cust_tax_pan from cusm where cust_acct_no=('" +cif+"')";
                            rs=con.prepareStatement(query).executeQuery();
                            if(rs.next()){
                                home_branch_no=rs.getString("home_branch_no");
                                cust_pan=rs.getString("cust_tax_pan");
                            }else{
                                home_branch_no=null;
                                cust_pan=null;
                            }
                            query="select br_name from br_master_report where br_code='"+home_branch_no+"'";
                            rs=con.prepareStatement(query).executeQuery();
                            if(rs.next()){
                                home_branch_name=rs.getString("br_name");
                            }else{
                                home_branch_name=null;
                            }
                            rs.close();
                            query="select key_1 from accounts where customer_no='"+cif+"'";
                            rs=con.prepareStatement(query).executeQuery();
                            if(rs.next()){
                                account_no=rs.getString("key_1");

                            }else{
                                account_no=null;
                            }
                            con.close();
                            con=connect.getConnection();
                            query="Insert into "+name+" values('"+name1+"','"+mid_name+"','"+name2+"','"+cif+"','"+cust_pan+"','"+phone_no_res+"','"+phone_no_bus+"','"+telex_no+"','"+email_id1+"','"+email_id2+"','"+add1+"','"+add2+"','"+add3+"','"+add4+"','"+home_branch_no+"','"+home_branch_name+"','"+account_no+"')";
                            con.prepareStatement(query).executeQuery();
                        }else{
                            flag=false;
                        }
                    }
                }
                
                if(!flag){
                    if(option.contains("Name")){
                        int i=option.indexOf("Name");
                        String Name=data[i].toString().toUpperCase().trim();
                        //code
                        String fname="%";
                        String[] _name=Name.split(" ");
                        int l=_name.length;
                        for(int j=0;j<l;j++){
                            fname=fname+_name[j]+"%";
                        }
                        query="select name1,mid_name,name2,phone_no_res,phone_no_bus,telex_no,add1,add2,add3,add4,cust_no from cusvaa where name1|| mid_name|| name2 like '"+fname+"'";
                        rs=con.prepareStatement(query).executeQuery();
                        System.out.println(fname);
                        boolean next;
                        next=rs.next();
                        if(next){
                            flag=true;
                            while(next){
                                
                                name1=rs.getString("name1");
                                mid_name=rs.getString("mid_name");
                                name2=rs.getString("name2");
                                phone_no_res=rs.getString("phone_no_res");
                                phone_no_bus=rs.getString("phone_no_bus");
                                telex_no=rs.getString("telex_no");
                                add1=rs.getString("add1");
                                add2=rs.getString("add2");
                                add3=rs.getString("add3");
                                add4=rs.getString("add4");
                                cif=rs.getString("cust_no");
                                query="select home_branch_no, cust_tax_pan from cusm where cust_acct_no=('" +cif+"')";
                                ResultSet rs1=con.prepareStatement(query).executeQuery();
                                if(rs1.next()){
                                    home_branch_no=rs1.getString("home_branch_no");
                                    cust_pan=rs1.getString("cust_tax_pan");
                                }else{
                                    home_branch_no=null;
                                    cust_pan=null;
                                }
                                rs1.close();
                                query="select email_add1, email_add2 from cumi where cust_no=('"+cif+"')";
                                rs1=con.prepareStatement(query).executeQuery();
                                
                                if(rs1.next()){
                                    email_id1=rs1.getString("email_add1");
                                    email_id2=rs1.getString("email_add2");

                                }else{
                                    email_id1=null;
                                    email_id2=null;
                                }
                                rs1.close();
                                //Insert query
                                query="select br_name from br_master_report where br_code='"+home_branch_no+"'";
                                rs1=con.prepareStatement(query).executeQuery();
                                if(rs1.next()){
                                    home_branch_name=rs1.getString("br_name");
                                }else{
                                    home_branch_name=null;
                                }
                                rs1.close();
                                query="select key_1 from accounts where customer_no='"+cif+"'";
                                rs1=con.prepareStatement(query).executeQuery();
                                if(rs1.next()){
                                    account_no=rs1.getString("key_1");

                                }else{
                                    account_no=null;
                                }
                                rs1.close();
                                Connection con1;
                                con1=connect.getConnection();
                                query="Insert into "+name+" values('"+name1+"','"+mid_name+"','"+name2+"','"+cif+"','"+cust_pan+"','"+phone_no_res+"','"+phone_no_bus+"','"+telex_no+"','"+email_id1+"','"+email_id2+"','"+add1+"','"+add2+"','"+add3+"','"+add4+"','"+home_branch_no+"','"+home_branch_name+"','"+account_no+"')";
                                con1.prepareStatement(query).executeQuery();
                                con1.close();
                                next=rs.next();
                            }
                            
                        }else{
                            flag=false;
                        }
                        rs.close();
                    }
                }
                
                if(!flag){
                    if(option.contains("Address")){
                        int i=option.indexOf("Address");
                        String addr=data[i].toString().toUpperCase().trim();
                        //code
                        String add[]=addr.split(" ");
                        int l=add.length;
                        String address="%";
                        for(int j=0;j<l;j++){
                            address=address+add[j]+"%";
                        }
                        query="select name1,mid_name,name2,phone_no_res,phone_no_bus,telex_no,add1,add2,add3,add4,cust_no from cusvaa where add1|| add2|| add3|| add4 like '"+address+"'";
                        rs=con.prepareStatement(query).executeQuery();
                        boolean next=rs.next();
                        if(next){
                            flag=true;
                            while(next){
                                
                                name1=rs.getString("name1");
                                mid_name=rs.getString("mid_name");
                                name2=rs.getString("name2");
                                phone_no_res=rs.getString("phone_no_res");
                                phone_no_bus=rs.getString("phone_no_bus");
                                telex_no=rs.getString("telex_no");
                                add1=rs.getString("add1");
                                add2=rs.getString("add2");
                                add3=rs.getString("add3");
                                add4=rs.getString("add4");
                                cif=rs.getString("cust_no");
                                query="select home_branch_no, cust_tax_pan from cusm where cust_acct_no=('" +cif+"')";
                                ResultSet rs1;
                                rs1=con.prepareStatement(query).executeQuery();
                                if(rs1.next()){
                                    home_branch_no=rs1.getString("home_branch_no");
                                    cust_pan=rs1.getString("cust_tax_pan");
                                }else{
                                    home_branch_no=null;
                                    cust_pan=null;
                                }
                                rs1.close();
                                query="select email_add1, email_add2 from cumi where cust_no=('"+cif+"')";
                                rs1=con.prepareStatement(query).executeQuery();
                                if(rs1.next()){
                                    email_id1=rs1.getString("email_add1");
                                    email_id2=rs1.getString("email_add2");

                                }else{
                                    email_id1=null;
                                    email_id2=null;
                                }
                                rs1.close();
                                //Insert query
                                query="select br_name from br_master_report where br_code='"+home_branch_no+"'";
                                rs1=con.prepareStatement(query).executeQuery();
                                if(rs1.next()){
                                    home_branch_name=rs1.getString("br_name");
                                }else{
                                    home_branch_name=null;
                                }
                                rs1.close();
                                query="select key_1 from accounts where customer_no='"+cif+"'";
                                rs1=con.prepareStatement(query).executeQuery();
                                if(rs1.next()){
                                    account_no=rs1.getString("key_1");

                                }else{
                                    account_no=null;
                                }
                                rs1.close();
                                Connection con1=connect.getConnection();
                                query="Insert into "+name+" values('"+name1+"','"+mid_name+"','"+name2+"','"+cif+"','"+cust_pan+"','"+phone_no_res+"','"+phone_no_bus+"','"+telex_no+"','"+email_id1+"','"+email_id2+"','"+add1+"','"+add2+"','"+add3+"','"+add4+"','"+home_branch_no+"','"+home_branch_name+"','"+account_no+"')";
                                con1.prepareStatement(query).executeQuery();
                                con1.close();
                                next=rs.next();
                                rs.moveToCurrentRow();
                            }
                        }else{
                            flag=false;
                        }
                    }
                }
            
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
