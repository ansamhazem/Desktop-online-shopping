/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ahmed.taher
 */
public class adrress {
  public int address_id;
  public String address_name;
  public int user_id;
  
  
  public ArrayList<adrress> ShowAllAddress(ConnectDB db ,int id) throws SQLException
  {
      ResultSet rs ;
                  
      ArrayList<adrress> addrress = new ArrayList<>();
      


      String Query="SELECT * FROM `adrress` WHERE `user_id`="+id+"";
      rs = db.GetData(Query);
            while (rs.next()) { 
                adrress address = new  adrress();
                address.address_id=rs.getInt("address_id");
                address.address_name=rs.getString("address_name");
                address.user_id=rs.getInt("user_id");
                addrress.add(address);
                
            }    
      return addrress;
  
}

}