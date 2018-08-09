package core;
import core.ConnectDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tarek Hesham Mustafa
 */

public class Complain {
    
    public int ID ;
    public String Subject ; 
    public String Description ;
    public int sender_id ;
    public String Sender_name;
   
    ResultSet rs;
    
    
  
    public int ADDComplain (String Subject, String Description,int ClintID,ConnectDB connection){
        
        String query=("INSERT INTO `complain`( `subject`, `description`, `sender_id`) VALUES ('"+Subject+"','"+Description+"','"+ClintID+"')");
               
        return connection.manipulateData(query);

    }
    
    
    public void showcomplain (int complainID,ConnectDB connection) throws SQLException{
        
        String query= "select * from complain join person on sender_id = person.user_id WHERE complain_id="+complainID;
         rs=connection.GetData(query);
         
         while(rs.next()){
           ID = Integer.parseInt( rs.getString("complain_id"));
             Subject=rs.getString("subject");
             Description=rs.getString("description");
             sender_id=rs.getInt("sender_id");
             Sender_name=rs.getString("first_name");
             
             System.out.println(Subject+Description+sender_id);
   
    }
    }
    
     public ArrayList<Complain> showAllcomplain(ConnectDB connection) {

        Complain complain = new Complain();
        String query = "SELECT * FROM `complain` join person on sender_id = person.user_id";
        ArrayList<Complain> complains = new ArrayList<>();
        try {
            rs = connection.GetData(query);
            while (rs.next()) {
                
                complain = new Complain();
                complain.ID = Integer.parseInt( rs.getString("complain_id"));
                complain.Subject = rs.getString("subject");
                complain.Description = rs.getString("description");
                complain.sender_id = rs.getInt("sender_id");
                complain.Sender_name=rs.getString("first_name");
                complains.add(complain);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return complains;

    }
    
       
    
    }

    
