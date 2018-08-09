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
public class Payment {
  public String Type ; 
    public int ID ; 
    public String Fees ;
     public int payment_id;

    
    public void First(ConnectDB db ,int id,ArrayList<paypal> Paypall,ArrayList<cash> Cash,ArrayList<credit_card> creditt) throws SQLException
    {
        ResultSet rs,rs1,rs2,rs3 ;
        int inx1=0,inx2=0,inx3=0;
//        ArrayList<paypal> Paypall = new ArrayList<>();
//        ArrayList<cash> Cash = new ArrayList<>();
//        ArrayList<credit_card> creditt = new ArrayList<>();
        String[][] paypal = new String[200][4];
        String[][] cash = new String[200][4];
        String[][] credit = new String[200][4];
        String query ="SELECT * FROM `payment` WHERE `User_ID`="+id+"";
         rs = db.GetData(query);
            while (rs.next()) {                
           if (rs.getInt("Type")==1)
           {
               
               paypal[inx1][0]=rs.getString("payment_id");
               paypal[inx1][1]=rs.getString("Type");
               paypal[inx1][1]=rs.getString("fees");
               paypal[inx1][1]=rs.getString("User_ID");
              inx1++; 
                System.out.println("Pay pal");
            
           }
           else if (rs.getInt("Type")==2)
           {
               
               credit[inx2][0]=rs.getString("payment_id");
               credit[inx2][1]=rs.getString("Type");
               credit[inx2][1]=rs.getString("fees");
               credit[inx2][1]=rs.getString("User_ID");
              inx2++; 
                System.out.println("Credit");
            
           }
           else {
              
               cash[inx3][0]=rs.getString("payment_id");
               cash[inx3][1]=rs.getString("Type");
               cash[inx3][1]=rs.getString("fees");
               cash[inx3][1]=rs.getString("User_ID");
              inx3++; 
              System.out.println("Cash");
              
           }
    }
                            System.out.println("Paypill Details");

            for (int  i = 0 ; i<paypal.length;i++)
            {
                String query1="SELECT * FROM `paypal` WHERE `payment_id`="+"\""+paypal[i][0]+"\""+"";
                 rs1 = db.GetData(query1);
                 while (rs1.next()) {
                     paypal p = new paypal();
                     p.ID= rs1.getInt("id");
                     p.email=rs1.getString("email");
                     p.password=rs1.getString("password");
                    p.payment_id= Integer.parseInt(paypal[i][0]);
                    Paypall.add(p);
                    //System.out.println(rs1.getString("email")+" "+rs1.getString("password"));
            }
    }
            


            for (int  i = 0 ; i<cash.length;i++)
            {
                String query1="SELECT * FROM `cash` WHERE `payment_id`="+cash[i][0]+"";
                 rs2 = db.GetData(query1);
                 while (rs2.next()) {
                     cash c = new cash();
                     c.ID=rs2.getInt("id");
                     c.cheaq_num=rs2.getInt("cheaq_num");
                     c.payment_id=Integer.parseInt(cash[i][0]);
                     Cash.add(c);
                 //    System.out.println(rs2.getString("cheaq_num"));
            }
    }
            
                            System.out.println("Credit Details");

            for (int  i = 0 ; i<credit.length;i++)
            {
                String query1="SELECT * FROM `credit_card` WHERE `payment_id`="+credit[i][0]+"";
                 rs3 = db.GetData(query1);
                 while (rs3.next()) {
                     credit_card c = new credit_card();
                     c.ID=rs3.getInt("id");
                     c.num=rs3.getInt("num");
                     c.password=rs3.getInt("password");
                     c.payment_id=Integer.parseInt(credit[i][0]);
                     creditt.add(c);
                     System.out.println(rs3.getString("num")+" "+rs3.getString("password"));
            }
    }
    }
    public int AddPayment(ConnectDB db,int id,int type,ArrayList<String> input) throws SQLException
    {
        int fee= 0;
        if (type==1)//paypall 600
        fee=600;
        else if (type==2)//creditcard 400
        fee=400;
        if (type==3) //cash 500
        fee=500;
        String query = "INSERT INTO `payment`(`Type`, `fees`, `User_ID`) "
                + "VALUES ("+type+","+fee+","+id+")";
        
       if (db.manipulateData(query)!=-1)
       {
           int ind=0;
            ResultSet rs ;

             String query2="SELECT * FROM `payment` ORDER BY payment_id DESC LIMIT 1";
                        rs = db.GetData(query2);
                        while (rs.next()) 
                        {
                            ind = rs.getInt("payment_id");
                            
                        }
            if (type==1)
            {
                String query3="INSERT INTO `paypal`( `email`, `password`, `payment_id`) "
                        + "VALUES ("+input.get(0)+","+input.get(1)+","+ind+")";
             return db.manipulateData(query3);   
            }
            
            else if (type==2)
            {
                String query3="INSERT INTO `credit_card`( `num`, `password`, `payment_id`) "
                        + "VALUES ("+input.get(0)+","+input.get(1)+","+ind+")";
             return db.manipulateData(query3);   
            }
            
            else if (type==3)
            {
                String query3="INSERT INTO `cash`( `cheaq_num`, `payment_id`) "
                        + "VALUES ("+input.get(0)+","+ind+")";
             return db.manipulateData(query3);   
            }
            
            
            
       }
       return -1;
    }
    
    
   
    
}
