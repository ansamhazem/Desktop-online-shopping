/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 *
 * @author Ansam
 */
public interface IWebSiteInfo {
    
    public String GetEmail(ConnectDB connection);
    public String GetPhone(ConnectDB connection);
    public String GetDescription(ConnectDB connection);
}

