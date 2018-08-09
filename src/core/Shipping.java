package core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jrockit.jfr.tools.ConCatRepository;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fawy
 */
public class Shipping {
    public String Type ;
    public String Duration;
    public double CostOfShippingMethod;
    public int ID ;
    ResultSet rs ;
    ArrayList<Shipping> ship = new ArrayList<>();
    Shipping sh ;
    public ArrayList<Shipping> SelectAllShippingMetod(ConnectDB DB){
           
        String query = "SELECT * FROM `shipping` WHERE 1";
           rs = DB.GetData(query);
        try {
            int i = 0 ;
            while(rs.next()){
                sh = new Shipping();
                System.out.println(" " + i++);
                
                
                
                
                sh.Type = rs.getString("name");
                System.out.println(sh.Type+" " + i++);
                sh.ID = Integer.parseInt(rs.getString("shipping_id"));
                System.out.println(sh.ID+" " + i++);
                
                sh.Duration=rs.getString("duration");
                System.out.println(sh.Duration+" " + i++);
                sh.CostOfShippingMethod =Double.parseDouble(rs.getString("coast_of_shipping"));
                System.out.println(sh.CostOfShippingMethod+" " + i++);
                ship.add(sh);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Shipping.class.getName()).log(Level.SEVERE, null, ex);
        }
           
           return ship;
        
    }
    
    public double GetShippingCoast(int id,ConnectDB DB ){
        
        String query = "SELECT coast_of_shipping FROM `shipping` WHERE shipping_id = "+id;
        rs = DB.GetData(query);
        
        try {
            if(!rs.next()) {
            } else {
                this.CostOfShippingMethod = Double.parseDouble(rs.getString("coast_of_shipping"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Shipping.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.CostOfShippingMethod;
        
    }
    
     public int AddShipping(String name ,String duration,double coast_of_shipping ,ConnectDB DB){
        String query =("INSERT INTO `shipping`(`name`, `duration`, `coast_of_shipping`) VALUES ( '" +name +"','"+duration+"',90)");
        return DB.manipulateData(query);
    }
     public String GetShippingName(int id ,ConnectDB DB){
        
        String query = "SELECT name FROM `shipping` WHERE shipping_id = "+id;
        rs = DB.GetData(query);
        
        try {
            if(!rs.next()) {
            } else {
                this.Type = rs.getString("name");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Shipping.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.Type;
        
    }
}
