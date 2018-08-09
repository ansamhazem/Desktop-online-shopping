/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.ArrayList;

/**
 *
 * @author Fawy
 */
public interface _ShowAllItemsInterface {
    public ArrayList<Item> ShowAllItems(int categoryID,ConnectDB DB);
}
