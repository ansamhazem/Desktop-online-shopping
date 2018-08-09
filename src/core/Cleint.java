/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import com.mysql.fabric.xmlrpc.Client;
import com.sun.webkit.ContextMenu;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fawy
 */
public class Cleint extends User  implements _ShowAllItemsInterface , ShowProfile,IWebSiteInfo{
     IWebSiteInfo InterFace = new WebSiteInfo();

    _ShowAllItemsInterface show;
public Cleint(){
    show = new Item();
}

    @Override
    public ArrayList<Item> ShowAllItems(int categoryID,ConnectDB connection) {
    ArrayList<Item> items =  show.ShowAllItems(categoryID,connection);
    return items;
    }
    
     public ArrayList <Item> showhistrotyoforder(int clientid,ConnectDB connection){
   Order od=new Order(); 
   return od.showorderhistory(clientid,connection);
   
   }
   
   
    @Override
    public void showprofile(ConnectDB connection, int ID) {

        String query= "SELECT `first_name`, `last_name`, `password`, `email`, `mobile`, `gender`, `nationality` ,`birthdate`FROM `person` WHERE user_id ="+ID;
        rs=connection.GetData(query);
         
        try {
            while(rs.next()){
                
              FirstName=rs.getString("first_name");
              LastName=rs.getString("last_name");
              Password=rs.getString("password");
              Email=rs.getString("email");
             MobilePhone=rs.getString("mobile");
              Gender = Integer.parseInt(rs.getString("gender"));
                Nationality = rs.getString("nationality");
               BirthDate1 = rs.getDate("birthdate");
                Email = rs.getString("email");
              
            

            }  
        
        } catch (SQLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
      @Override
    public String GetEmail(ConnectDB connection) {
        return InterFace.GetEmail(connection);

    }

    @Override
    public String GetPhone(ConnectDB connection) {
        return InterFace.GetPhone(connection);
    }

    @Override
    public String GetDescription(ConnectDB connection) {
        return InterFace.GetDescription(connection);
    }

    
    
}
