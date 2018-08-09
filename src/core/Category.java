package core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fawy
 */
public class Category {
    public String Name ;
    public int ID  ; 
    public int numberOfItems ;
    public String Description ;
   ResultSet rs ;
   
   public Category SelectSpecificCategory(int CategoryID,ConnectDB connection ){
       
       String query = "SELECT * FROM `category` WHERE `id` = "+CategoryID;
       rs = connection.GetData(query);
       Category c = new Category();
        System.out.println("111");
       c.ID = CategoryID;
        try {
            
            if (rs.next()) {
            c.Name = rs.getString("name");
            System.out.println("gg  "+rs.getString("name"));
            c.Description = rs.getString("description");
       c.numberOfItems =Integer.parseInt(rs.getString("numberOfItems"));
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       return c ;
   }
   
   public boolean UpdateCategory(Category cc,ConnectDB connection){
       
       String query = " UPDATE `category` SET `name`='"+cc.Name+"',`description`='"+cc.Description+"',`numberOfItems`='"+cc.numberOfItems+"' WHERE `id`="+cc.ID;
  
       /*"UPDATE `sw2`.`site_info` SET `email` = '"+Email+"', `phone` = '"+Phone+"', `description` = '"+Description+"' WHERE `site_info`.`ID` = 1";
        */
       int tt = connection.manipulateData(query);
       if(tt==-1)
           return false;
       else
           return true;
       
   }
    
    
    public int AddCategory(String name ,String description,ConnectDB connection){
        String query = ("INSERT INTO `category`(`name`, `description`) VALUES ('"+name+"','"+description+"')");
        return connection.manipulateData(query) ;
    }
    
}
