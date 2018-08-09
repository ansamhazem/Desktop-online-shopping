package core;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Fawy
 */
public class Item implements _ShowAllItemsInterface {

    public String Name;
    public double Price;
    public int Quantity;
    public Date ExpireDate;
    public String Descritption;
    public String ProductionDate;
    public String PhotoURL;
    public String Type;
    public String Color;
    public String Size;
    public String Category;
    //public Date dateobj1 = new Date();
    //public int Id; 
    public int ID;
    //public double Price2;
    //public int Quantity2;
    //public Date ExpireDate2 = new Date();

    ResultSet rs;
    ArrayList<Item> Items = new ArrayList<>();

    //Date ExpiredDate = new Date();
    Date CurrentDate = new Date();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    Item it;
    ArrayList<Item> itemlist;

    public ArrayList<Item> NotifiyFinshed(int q,ConnectDB connection) throws SQLException {
        itemlist = new ArrayList<Item>();
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
                + "   WHERE item.quantity < " + q;
        rs = connection.GetData(query);

        while (rs.next()) {
            it = new Item();
            it.ID = Integer.parseInt(rs.getString("item.item_id"));
            it.Name = rs.getString("name");
            it.Price = Double.parseDouble(rs.getString("price"));
            it.Quantity = Integer.parseInt(rs.getString("quantity"));
            it.ExpireDate = rs.getDate("ExpiredDate");
            it.PhotoURL = rs.getString("images.name");
            it.Type = rs.getString("type.name");
            it.Color = rs.getString("color.name");
            it.Size = rs.getString("size.name");
            it.Descritption = rs.getString("item.description");
            it.Category = rs.getString("category.name");
            itemlist.add(it);

        }
        return itemlist;
    }

    public ArrayList<Item> NotifyExpired(ConnectDB connection) {
        ArrayList<Item> item = new ArrayList<>();
        Item itemObj = new Item();
        String query = "SELECT `item_id`, `name`,`ExpiredDate` FROM `item`";
        rs = connection.GetData(query);
        try {
            while (rs.next()) {

                ExpireDate = rs.getDate("ExpiredDate");
                String d1 = df.format(CurrentDate);
                String d2 = df.format(ExpireDate);

                if (d1.equals(d2)) {
                    itemObj.ExpireDate = this.ExpireDate;
                    itemObj.Name = rs.getString("name");
                    itemObj.ID = Integer.parseInt(rs.getString("item_id"));
                    item.add(itemObj);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error in Function NotifyExpired " + e);
        }
        return item;
    }

    @Override
    public ArrayList<Item> ShowAllItems(int categoryID,ConnectDB connection) {
        Item item;
        String query = "SELECT item.item_id\n"
                + "       ,item.name\n"
                + "      ,price\n"
                + "      ,quantity\n"
                + "      ,ExpiredDate\n"
                + "      ,images.url\n"
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
                + "   WHERE catogery_id= " + categoryID;
        rs = connection.GetData(query);
        //System.out.println(" Done 1");
        int i = 0;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            while (rs.next()) {
                item = new Item();
                item.ID = Integer.parseInt(rs.getString("item.item_id"));
                item.Name = rs.getString("name");
                //System.out.println("Done  "+ ++i+item.Name);
                item.Price = Double.parseDouble(rs.getString("price"));
                //System.out.println("Done "+ ++i+item.Price);
                item.Quantity = Integer.parseInt(rs.getString("quantity"));
                //System.out.println("Done "+ ++i+item.Quantity);
                item.ExpireDate = rs.getDate("ExpiredDate");
                //System.out.println(df.format(dateobj1));
                //System.out.println(rs.getDate("ExpiredDate").getTime());
                //System.out.println("Doneii "+ ++i+"  "+df);
                item.PhotoURL = rs.getString("images.url");
                //System.out.println("Done "+ ++i);
                item.Type = rs.getString("type.name");
                //System.out.println("Done "+ ++i);
                item.Color = rs.getString("color.name");
                //System.out.println("Done "+ ++i);
                item.Size = rs.getString("size.name");
                //System.out.println("Done "+ ++i);
                item.Descritption = rs.getString("description");
                //System.out.println("Done "+ ++i);
                item.Category = rs.getString("category.name");
                //System.out.println("Done "+ ++i);

                Items.add(item);
                //      System.out.println("Done "+ ++i);
            }

        } catch (SQLException | NumberFormatException e) {
            System.out.println("Error " + e);
        }

        return Items;
    }

    public void itemDetails(int id,ConnectDB connection) throws SQLException {

        String query = "SELECT item.item_id\n"
                + "       ,item.name\n"
                + "      ,price\n"
                + "      ,quantity\n"
                + "      ,ExpiredDate\n"
                + "      ,images.url\n"
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
                + "    WHERE item_id = " + id;
        ResultSet rs;
        rs = connection.GetData(query);
        rs.next();
        ID = Integer.parseInt(rs.getString("item.item_id"));
        Name = rs.getString("name");
        Price = rs.getFloat("price");
        Quantity = rs.getInt("quantity");
        ExpireDate = rs.getDate("ExpiredDate");
        PhotoURL = rs.getString("images.url");
        Type = rs.getString("type.name");
        Color = rs.getString("color.name");
        Size = rs.getString("size.name");
        Descritption = rs.getString("description");
        Category = rs.getString("category.name");

    }

    public ArrayList<Item> SearchByName(String name,ConnectDB connection) throws SQLException {
        Item item = new Item();
        String query = "SELECT item.item_id\n"
                + "       ,item.name\n"
                + "      ,price\n"
                + "      ,quantity\n"
                + "      ,ExpiredDate\n"
                + "      ,images.url\n"
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
                + "WHERE item.name LIKE '%" + name + "%'";

        ResultSet rs;
        rs = connection.GetData(query);
        while (rs.next()) {
            item.ID = Integer.parseInt(rs.getString("item.item_id"));
            item.Name = rs.getString("name");
            item.Price = rs.getFloat("price");
            item.Quantity = rs.getInt("quantity");
            item.ExpireDate = rs.getDate("ExpiredDate");
            item.PhotoURL = rs.getString("images.url");
            item.Type = rs.getString("type.name");
            item.Color = rs.getString("color.name");
            item.Size = rs.getString("size.name");
            item.Descritption = rs.getString("description");
            item.Category = rs.getString("category.name");
            Items.add(item);
            item = new Item();
        }
        return Items;
    }
    
    
    public int AddItem(ConnectDB db,Image image,Item item,int CategID )
    {
                ResultSet rs;

        int id = 0 ;
         String query1="INSERT INTO `images`(`name`,`url`) VALUES ("+"\""+ image.name+ "\"" + "," + "\""+image.url+"\"" +")";
         int index1=db.manipulateData(query1);
        if (index1!=-1)
        {
                    try {
                        String query2="SELECT * FROM `images` ORDER BY image_id DESC LIMIT 1";
                        rs = db.GetData(query2);
                        while (rs.next()) 
                        {
                            id = rs.getInt("image_id");
                            
                        }
                        
                        String query3="INSERT INTO `item`(`name`, `price`, `quantity`,`Photo_id`, `description`, `catogery_id`) VALUES"
                                + "("+"\""+item.Name+"\""+","+"\""+item.Price+"\""+","+"\""+item.Quantity+"\""+","+"\""+id+"\""+","+"\""+item.Descritption+"\""+","+"\""+CategID+"\""+")";
                        
                          index1=db.manipulateData(query3);
                          return index1;
                    } catch (SQLException ex) {
                        Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
                    }
        }
        return index1;
    }

}
