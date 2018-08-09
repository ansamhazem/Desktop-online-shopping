package core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class Order {

    public int IdNumber;
    public int Status;

    ResultSet rs;

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public void MakeOrder(int cartID, ConnectDB DB) {

        this.setStatus(1);
        String query = ("INSERT INTO `order`( `status` , `Rate` ) VALUES ( '"+this.Status+"', 0 )");
        System.out.println("kkkkkkkkk   ");
        int index = DB.manipulateData(query);
        System.out.println("ggggggg   ");
        this.IdNumber = this.getOrderID(DB);
        System.out.println("ff  "+this.IdNumber);
        String gg = "SELECT `id_cart_item` FROM `cart_item` WHERE  `order_status` = 0 and `cart_id` = "+cartID;
        rs = DB.GetData(gg);
        ArrayList<Integer> ii = new ArrayList<>();
        try {
            while(rs.next()){
                ii.add( rs.getInt("id_cart_item"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int i=0;i<ii.size();i++) {
            System.out.println("hgh");
   String query2 = ("INSERT INTO `cart_item_order`(`id_cart_item`, `order_id`) VALUES ( '"+ii.get(i)+"','"+this.IdNumber+"')");
            DB.manipulateData(query2);

    }
    }

    public int getOrderID(ConnectDB DB) {

        String query = "SELECT * FROM `order` WHERE 1";
        rs = DB.GetData(query);
        try {
            boolean li = rs.last();
            System.out.println("jjjj " + li);
            if (li) {

                int kk = Integer.parseInt(rs.getString("order_id"));
                return kk;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public ArrayList<Item> showorderhistory(int clientid, ConnectDB connection) {
        ArrayList<Item> items = new ArrayList<>();

        String query = "SELECT   cart_item.no_of_items,cart_item.total_price,item.price,item.name,type.name ,size.name,color.name  FROM cart_item\n"
                + "join item  on item.item_id = cart_item.item_id \n"
                + "join color on item.color = color.id\n"
                + "join size  on item.size = size.id \n"
                + "join type  on item.type = type.id\n"
                + "join cart  on cart.cart_id = cart_item.cart_id\n"
                + "where `cart_item`.`order_status`= 1 and cart.user_id = " + "\"" + clientid + "\"" + "";

        try {
            rs = connection.GetData(query);

            while (rs.next()) {
                Item item = new Item();
                item.ID = rs.getInt("no_of_items");
                item.Price = rs.getFloat("item.price");
                item.Type = rs.getString("type.name");
                item.Name = rs.getString("item.name");
                item.Size = rs.getString("size.name");
                item.Color = rs.getString("color.name");

// int items =rs.getInt("no_of_items");
//            double totalprice =rs.getDouble("total_price");
//            float price =rs.getFloat("item.price");
//            String itname =rs.getString("item.name");
//            String tpname =rs.getString("type.name");
//            String siname =rs.getString("size.name");
//            String coname =rs.getString("color.name");
//            
//            
// 
//             System.out.println("number of item :"+items+"\n"+"total price"+totalprice+"\n"+"price of item :"+"\n"+price+"item name :"+itname+"\n"
//             +"type  :"+tpname+"\n"+"size  :"+siname+"\n"+"colorname : "+coname);           
                items.add(item);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return items;

    }
    
    public int MakeOrder(int cartID , int ship , int pay2 , double total , int address,ConnectDB DB){
        
        this.setStatus(0);
        String query = ("INSERT INTO `order`( `status` , `shipping_id` , `payment_id` , `address_id` , `total_coast` ) VALUES ( '"+this.Status+"','"+ship+"','"+pay2+"','"+address+"','"+total+"')");
        System.out.println("kkkkkkkkk   ");
        int index = DB.manipulateData(query);
        System.out.println("ggggggg   ");
        this.IdNumber = this.getOrderID(DB);
        System.out.println("ff  "+this.IdNumber);
        String gg = "SELECT `id_cart_item` FROM `cart_item` WHERE  `order_status` = 0 and `cart_id` = "+cartID;
        rs = DB.GetData(gg);
        ArrayList<Integer> ii = new ArrayList<>();
        try {
            while(rs.next()){
                ii.add( rs.getInt("id_cart_item"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int i=0;i<ii.size();i++) {
            System.out.println("hgh");
   String query2 = ("INSERT INTO `cart_item_order`(`id_cart_item`, `order_id`) VALUES ( '"+ii.get(i)+"','"+this.IdNumber+"')");
            DB.manipulateData(query2);
        }
  
        
        //if(index==-1 || index2 ==-1)
          //  return 0 ;
        return this.IdNumber ;
        
        
    }

}
