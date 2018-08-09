/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import com.mysql.fabric.xmlrpc.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ansam
 */
public class Staff extends User implements ShowProfile {

    public double Salary;
    public int Expirence;
    public String Postion;
    public int User_Type_id;
    public java.sql.Date DOW;

    public void InsertStaff(ConnectDB connection) {
        Connection con = connection.GetConnect();
        try {
            String quationmarks = "";
            for (int i = 1; i <= 9; i++) {
                if (i != 9) {
                    quationmarks += "?,";
                } else {
                    quationmarks += "?";
                }
            }
            String query = " insert into " + "person( `first_name`,`first_name`, `last_name`, `password`, `email`, `mobile`, `birthdate`, `gender`, `nationality`, `user_type`)"
                    + " values (" + quationmarks + ")";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, FirstName);
            preparedStmt.setString(2, LastName);
            preparedStmt.setString(3, Password);
            preparedStmt.setString(4, Email);
            preparedStmt.setString(5, MobilePhone);
            preparedStmt.setDate(6, BirthDate1);
            preparedStmt.setInt(7, Gender);
            preparedStmt.setString(8, Nationality);
            preparedStmt.setInt(9, User_Type_id);
            preparedStmt.execute();

            String query2 = "SELECT user_id FROM `person` ORDER BY user_id DESC LIMIT 1";
            ResultSet rs;
            rs = connection.GetData(query2);

            while (rs.next()) {
                ID = rs.getInt("user_id");
            }
            query = " insert into " + "staff ( `person_id`,`salary`, `experience`, `DOW`)"
                    + " values (?,?,?,?)";
            preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, ID);
            preparedStmt.setDouble(2, Salary);
            preparedStmt.setInt(3, Expirence);
            preparedStmt.setDate(4, DOW);

            // execute the preparedstatement
            preparedStmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void GetUserType(ConnectDB connection) throws SQLException {
        String query = "select id from user_type where name = " + "\"" + Postion + "\"";

        ResultSet rs;
        rs = connection.GetData(query);
        while (rs.next()) {
            User_Type_id = rs.getInt("id");

        }
        //User_Type_id=rs.getInt(1);

    }

    @Override
    public void showprofile(ConnectDB connection, int ID) {

        String query = "SELECT `salary`, `experience` ,`DOW` FROM `staff` WHERE person_id=" + ID;
        rs = connection.GetData(query);

        try {
            while (rs.next()) {

                Salary = rs.getDouble("salary");
                Expirence = rs.getInt("experience");
                DOW = rs.getDate("DOW");

            }

        } catch (SQLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
