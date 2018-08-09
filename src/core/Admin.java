package core;
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
public class Admin extends Staff {
    
     public void showingcomplain (int id,ConnectDB connection) throws SQLException{
        Complain co = new Complain();
      co.showcomplain(id,connection);
   
            }
     
     public void SetAllInfo(String Email, String Phone, String Description,ConnectDB connection)
    {
        WebSiteInfo WS = new WebSiteInfo();
        WS.SetAllInfo(Email, Phone, Description,connection);
    }
     
     public void AddStaff(ConnectDB connection)
     {
         Staff s = new Staff();
         s.InsertStaff(connection);
     }
    
    public ArrayList<User> ShowAllUser(ConnectDB connection){
        User u = new User();
        ArrayList<User> users= new ArrayList<>();
       users= u.ShowAllUsers(connection);
       return users;
    }
    
    public void AddItem(ConnectDB connection)
    {
        
    }

    public ArrayList<Item> ShowAllItems(int categoryID,ConnectDB connection) {
        Item i= new Item();
    ArrayList<Item> items =  i.ShowAllItems(categoryID,connection);
    return items;
    }
    
    
     public int AddShipping(String name, String duration, double coast_of_shipping,ConnectDB connection) {
        Shipping sh = new Shipping();
        return sh.AddShipping(name, duration, coast_of_shipping,connection);

    }
     
       public int AddCategory(String name ,String description,ConnectDB connection)
       {
           Category c = new Category();
           return c.AddCategory(name, description, connection);
       }
     public ArrayList<Complain> showAllcomplain(ConnectDB connection)
     {
         ArrayList<Complain> L= new ArrayList<>();
         Complain co= new Complain();
         L=co.showAllcomplain(connection);
         return L;
     }
}
