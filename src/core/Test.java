/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import Admin.AddStaff;
import Person.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Ansam
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ParseException {
        // TODO code application logic here
        //new Home().setVisible(true);
        ConnectDB connection = ConnectDB.Getconnection();
        new Home(connection).setVisible(true);
        /*String query = "select* from person";
            ResultSet rs ;
            rs = connection.GetData(query);
            while(rs.next()){
                    String name = rs.getString("name");
                    String age = rs.getString("age");
                    System.out.println("Name:"+name);
                    System.out.println("Age:"+age);
                }*/
        /*String[] values = new String[2];
        values[0] = "taher";
        values[1] = "22";

        connection.Insert("user", values, 2);

        Staff s = new Staff();
        s.FName = "Ansam";
        s.LName = "Hazem";
        s.Gender = true;
        
        String BD="2015-03-31";  
        s.BD=Date.valueOf(BD);

      
        s.Expirence = 1;
        s.Mobile = "01127099290";
        s.email = "ansam@yahoo.com";
        s.Password = "password";
        s.Postion = "admin";
        s.Nationality = "egypt";
        s.Salary = 2000;
       
         String DOW="2015-02-31";  
        s.DOW=Date.valueOf(DOW);
        s.GetUserType(connection);
        s.InsertStaff(connection);*/
       /*AddStaff st= new AddStaff();
       st.connection=connection;
       st.setVisible(true);*/
       /*Item i= new Item();
       ArrayList<Item> iL = new ArrayList<>();
       i.itemDetails(1);
        System.out.println(i.Name);
        iL=i.SearchByName("z");
        System.out.println(iL.get(0).Name);*/
       
    }

}
