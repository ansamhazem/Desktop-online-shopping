package core;


import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConnectDB {
    
    private Connection con;
    private Statement st;
    private ResultSet rs;
    
    private ConnectDB()
            {
                try
                {
                    
                    Class.forName("com.mysql.jdbc.Driver");
                    System.out.println("wait for get Connection");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sw2","root","");
                    st = con.createStatement();
                    System.out.println("Conection Created... ");
                    
                }
                catch(Exception ex)
                {
                    System.out.println(" Error:"+ex);
                }
            }
    private static ConnectDB Connection;
    public Connection GetConnect()
    {
        return con;
    }
    public  ResultSet GetData(String query)
    {
        
        try
        {
            System.out.println("wait for Execute");
            rs = st.executeQuery(query);
            
        }catch(Exception ex)
        {
            System.out.println(" In Function GetData"+ex);
        }
       return rs;
    }
    public static ConnectDB Getconnection()
    {
        if(Connection == null){
           Connection = new ConnectDB();
        }
        return Connection;
    }
    public void Insert(String TableName,String[] Values, int CoulmnNo)
    {
        try {
            String quationmarks="";
            for(int i=1; i<=Values.length;i++)
            {
                if(i!=Values.length)
                quationmarks+="?,";
                else
                    quationmarks+="?"; 
            }
            String query = " insert into "+ TableName
                    + " values (" + quationmarks +")";
            
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            for (int i=1; i<=Values.length;i++){
            preparedStmt.setString (i, Values[i-1]);
            }
            // execute the preparedstatement
            preparedStmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }

    //Add & Update & Delete (in case add an execut return row number otherwise it return no thing  )
    public int manipulateData(String query) {
        int index = -1; 
        try {
            PreparedStatement preparedStmt = con.prepareStatement(query);
            index = preparedStmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return index;
    }

    
    
    
}





