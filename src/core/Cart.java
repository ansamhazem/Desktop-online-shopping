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
public class Cart {

    public int ID;
    public String ItemName;
    public float TotalPrice;
    public int NumberOfItems;
    public float priceofoneitem; 
    ResultSet rs;
    ArrayList<Cart> cv = new ArrayList<>();

    public ArrayList<Cart> SelectSpecificCartItems(int id,ConnectDB connection) {

        String query = "SELECT \n"
                + "       item.name \n"
                + "      ,no_of_items\n"
                + "      ,total_price\n"
                + "FROM cart_item\n"
                + "    JOIN item ON item.item_id=cart_item.cart_id \n"
                + "  WHERE order_status=0 and cart_id = " + id;

        /*"SELECT item.name"
         + " , cart_item.no.of.items"
         + " , cart_item.total_price "
         + " FROM cart_item\n" +
         "JOIN item ON item.item_id =cart_item.cart_id\n" +
         "WHERE cart_id = "+id+" and cart_item.order_status = 1 ";*/
        rs = connection.GetData(query);

        //System.out.println("111");
        try {
            while (rs.next()) {

                Cart c = new Cart();
                c.ID = id;
                c.ItemName = rs.getString("item.name");
                // System.out.println("gg  "+rs.getString("name"));
                c.NumberOfItems = Integer.parseInt(rs.getString("no_of_items"));
                c.TotalPrice = Float.parseFloat(rs.getString("total_price"));
                c.priceofoneitem = c.TotalPrice / c.NumberOfItems;
                cv.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cv;

    }

    public boolean UpdateCart(ArrayList<Cart> cc,ConnectDB connection) {
        int tt = 0;
        for (int i = 0; i < cc.size(); i++) {

            cc.get(i).TotalPrice = cc.get(i).NumberOfItems * cc.get(i).priceofoneitem;
            String query = " UPDATE `cart_item` SET `no_of_items`='" + cc.get(i).NumberOfItems + "',`total_price`='" + cc.get(i).TotalPrice + "' WHERE order_status=0 and `cart_id`=" + cc.get(i).ID;
            /*"UPDATE `sw2`.`site_info` SET `email` = '"+Email+"', `phone` = '"+Phone+"', `description` = '"+Description+"' WHERE `site_info`.`ID` = 1";
             */
            tt = connection.manipulateData(query);
            System.out.println(cc.get(i).TotalPrice+" "+cc.get(i).NumberOfItems+" "+cc.get(i).priceofoneitem);
        if (tt == -1) {
            return false;
        } else {
            return true;
        }

    }
        return true;


    }
    
     public void AddToCart(int CartId, int ItemId, int NumOfItem,ConnectDB connection) {//add in table cart_item

        try {
            String query = "SELECT * FROM `item` WHERE `item_id` =" + ItemId;
            this.rs = connection.GetData(query);
            if (rs.next()) {
                priceofoneitem = rs.getFloat("price");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }

        this.TotalPrice = NumOfItem * priceofoneitem;
        String query = "INSERT INTO `cart_item`(`cart_id`, `item_id`, `no.of.items`, `total_price`, `order_status`) VALUES (" + CartId + "," + ItemId + "," + NumOfItem + "," + TotalPrice + "," + 0 + ")";
        connection.manipulateData(query);
    }
     
     public ArrayList<Item> getallitemincart(int clientid,ConnectDB connection) throws SQLException { //Return All Item
        ArrayList<Item> arrayList = new ArrayList<>();
        Item it;
        String query = "SELECT item.item_id\n"
                + "      ,item.name\n"
                + "      ,price\n"
                + "      ,quantity\n"
                + "      ,ExpiredDate\n"
                + "      ,images.name\n"
                + "      ,type.name\n"
                + "      ,color.name\n"
                + "      ,size.name\n"
                + "      ,item.description\n"
                + "      ,category.name \n"
                + "FROM item\n"
                + "    JOIN images ON item.Photo_id=images.image_id \n"
                + "    JOIN type ON item.type=type.id \n"
                + "    JOIN color ON item.color=color.id \n"
                + "    JOIN size ON item.size=size.id \n"
                + "    JOIN category ON item.catogery_id=category.id\n"
                +" join `cart_item` on `item`.`item_id`=`cart_item`.`item_id` join `cart` on `cart`.`cart_id` = `cart_item`.`cart_id`"
                + "where `cart`.`user_id`=" + clientid;
        rs = connection.GetData(query);
        try {
            while (rs.next()) {
                it = new Item();
                it.ID = Integer.parseInt(rs.getString("item.item_id"));
            it.Name = rs.getString("item.name");
            it.Price = Double.parseDouble(rs.getString("price"));
            it.Quantity = Integer.parseInt(rs.getString("quantity"));
            it.ExpireDate = rs.getDate("ExpiredDate");
            it.PhotoURL = rs.getString("images.name");
            it.Type = rs.getString("type.name");
            it.Color = rs.getString("color.name");
            it.Size = rs.getString("size.name");
            it.Descritption = rs.getString("item.description");
            it.Category = rs.getString("category.name");
                
                arrayList.add(it);
            }
        } catch (Exception e) {
        }
        return arrayList;
    }
    
     public double calculatetotalprice(int clientid,ConnectDB connection) {
        String query = "select total_price from cart_item join cart on cart.cart_id = cart_item.cart_id where cart.user_id=" + clientid;
        try {
            rs = connection.GetData(query);
            while (rs.next()) {
                TotalPrice += rs.getInt("total_price");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return TotalPrice;
    }

}