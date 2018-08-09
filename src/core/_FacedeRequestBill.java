package core;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fawy
 */
public class _FacedeRequestBill {

    
    ArrayList<Item> items = new ArrayList<>();
    ArrayList<String> Final  = new ArrayList<>();
    
    public ArrayList<Item> RetriveAllItems(int ClientID , ConnectDB connection){
                    Cart cart = new Cart();
        try {
            items = cart.getallitemincart(ClientID,connection);
        } catch (SQLException ex) {
            Logger.getLogger(_FacedeRequestBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return items;
        
    }
    
    
    public ArrayList<String> RequestBill( int CleintID ,int CartID , int ShippingMethodID , int PaymentMethodID , int PaymethodTypeID , int addressID,ConnectDB connection ){
        Cart cart = new Cart();
        Order order = new Order();
        Shipping ship = new Shipping();
        
        
        Final.add(ship.GetShippingName(ShippingMethodID,connection));
        double total = ship.GetShippingCoast(ShippingMethodID,connection);
        Final.add(String.valueOf( total ));
        Final.add(String.valueOf(cart.calculatetotalprice(CleintID,connection)));
            total+=cart.calculatetotalprice(CleintID,connection);
        if( PaymethodTypeID ==1 )
        {
            Final.add("PayPal");
            Final.add("600");    
            total+=600;
        }
        else if (PaymethodTypeID == 2){
            Final.add("Credit Cart");
            Final.add("700");
            total+=700;
            }
        else {
            Final.add("Cash");
            Final.add("500");
            total+=500;
        }
        
        
        int orderno = order.MakeOrder(CartID , ShippingMethodID ,  PaymentMethodID , total ,  addressID,connection);
        Final.add(String.valueOf(orderno));
        Final.add(String.valueOf(total));
        return Final;
    }
    
    
    
}
