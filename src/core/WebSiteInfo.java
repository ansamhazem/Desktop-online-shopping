package core;

import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fawy
 */
public class WebSiteInfo implements IWebSiteInfo {
    private String Email;
    private String Phone;
    private String Description;
    private ResultSet rs;
    private String query;
     public void SetAllInfo(String Email, String Phone, String Description,ConnectDB connection) {
        query = "UPDATE `sw2`.`site_info` SET `email` = '" + Email + "', `phone` = '" + Phone + "', `description` = '" + Description + "' WHERE `site_info`.`ID` = 1";
        connection.manipulateData(query);
        //System.out.println(Email+" "+Phone+" "+Description+" ");
    }

    @Override
    public String GetEmail(ConnectDB connection) {
        query = "SELECT `ID`, `email`, `phone`, `description` FROM `site_info` WHERE 1";
        try {

            rs = connection.GetData(query);
            if (rs.next()) {
                this.Email = rs.getString("email");
            }
        } catch (SQLException e) {
            System.out.println("Error in Function GetAllInfo " + e);
        }
        return this.Email;
    }

    public String GetPhone(ConnectDB connection) {
        query = "SELECT `phone` FROM `site_info` WHERE 1";
        try {

            rs = connection.GetData(query);
            if (rs.next()) {
                this.Phone = rs.getString("phone");
            }
        } catch (SQLException e) {
            System.out.println("Error in Function GetAllInfo " + e);
        }
        return this.Phone;
    }

    public String GetDescription(ConnectDB connection) {
        query = "SELECT `description` FROM `site_info` WHERE 1";
        try {
            rs = connection.GetData(query);
            if (rs.next()) {
                this.Description = rs.getString("description");
            }
        } catch (SQLException e) {
            System.out.println("Error in Function GetAllInfo " + e);
        }
        return this.Description;
    }
}


