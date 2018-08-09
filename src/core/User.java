/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import core.ConnectDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Fawy
 */
public class User extends Person {

    public int CartID;
    ResultSet rs;
    ResultSet rs1;
    ArrayList<User> one = new ArrayList<>();

    public ArrayList<User> ShowAllUsers(ConnectDB connection) {
        User user;
        String query = "SELECT user_id"
                + "       ,first_name\n"
                + "      ,last_name\n"
                + "      ,password\n"
                + "      ,email\n"
                + "      ,mobile\n"
                + "      ,birthdate\n"
                + "      ,gender\n"
                + "      ,nationality\n"
                + "      ,user_type.name\n"
                + "      ,images.url \n"
                + "FROM person\n"
                + "    JOIN images ON person.photo_id=images.image_id \n"
                + "    JOIN user_type ON person.user_type=user_type.id \n"
                + "   WHERE 1 ";
        rs = connection.GetData(query);
        //System.out.println(" Done 1");
        int i = 0;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            int j = 0;
            while (rs.next()) {
                user = new User();
                /*      
ID FirstName LastName Password MobilePhone BirthDate Gender Nationality Email PhotoId Salary Position Experiance DateOfWork
                 */
                user.ID = Integer.parseInt(rs.getString("user_id"));
                user.FirstName = rs.getString("first_name");
                //System.out.println("Done  "+ ++i+user.FirstName);
                user.LastName = rs.getString("last_name");
                System.out.println("Done " + ++i + user.LastName);
                user.Password = rs.getString("password");
                System.out.println("Done " + ++i + user.Password);
                user.MobilePhone = rs.getString("mobile");
                System.out.println("Done " + ++i + user.MobilePhone);
                user.BirthDate1 = rs.getDate("birthdate");
                System.out.println(df.format(user.BirthDate1));
                //System.out.println(rs.getDate("ExpiredDate").getTime());
                //System.out.println("Doneii "+ ++i+"  "+df);
                user.Gender = Integer.parseInt(rs.getString("gender"));
                System.out.println("Done " + ++i + user.Gender);
                user.Nationality = rs.getString("nationality");
                System.out.println("Done " + ++i + user.Nationality);
                user.Type = rs.getString("user_type.name");
                System.out.println("Done " + ++i + user.Type);
                user.Email = rs.getString("email");
                System.out.println("Done " + ++i + user.Email);
                user.photoURL = rs.getString("images.url");
                System.out.println("Done " + ++i + user.photoURL);
                one.add(user);
                //      System.out.println("Done "+ ++i);
            }

        } catch (SQLException | NumberFormatException e) {
            System.out.println("Error " + e);
        }

        return one;
    }

    public boolean Login(String email, String Password, ConnectDB connection) throws SQLException {
        String query = "  SELECT user_id"
                + "       ,first_name\n"
                + "      ,last_name\n"
                + "      ,password\n"
                + "      ,email\n"
                + "      ,mobile\n"
                + "      ,birthdate\n"
                + "      ,gender\n"
                + "      ,nationality\n"
                + "      ,user_type.name\n"
                + "      ,images.url \n"
                +" FROM `person` JOIN images ON person.photo_id=images.image_id \n"
                + "    JOIN user_type ON person.user_type=user_type.id \n"
                +"WHERE email=" + "\"" + email + "\"" + " && password=" + "\"" + Password + "\"";
        try {
            rs = connection.GetData(query);
        
        if (rs.next()) {
            do {
                ID = Integer.parseInt(rs.getString("user_id"));
                FirstName = rs.getString("first_name");
                LastName = rs.getString("last_name");
                this.Password = rs.getString("password");
                MobilePhone = rs.getString("mobile");
                BirthDate1 = rs.getDate("birthdate");
                Gender = Integer.parseInt(rs.getString("gender"));
                Nationality = rs.getString("nationality");
                Type = rs.getString("user_type.name");
                Email = rs.getString("email");
                photoURL = rs.getString("images.url");
            }while(rs.next()) ;
            query = "SELECT cart_id FROM `cart` WHERE user_id=" + ID;
             rs = connection.GetData(query);
             if(rs.next())
             CartID=Integer.parseInt(rs.getString("cart_id"));
            return true;
        }
        
        } catch (Exception e) {
            System.out.println(e);
        
    }
return false;
    }
}
