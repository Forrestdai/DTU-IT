/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.CORBA.REBIND;

/**
 *
 * @author Aimo
 */
public class RMIServer
{
    
    
    public void createConnection() 
    {
        try 
        {
            //Socket ss = new Socket("2001:878:200:4102:216:3eff:fe1a:c", 2755);
            //SocketPermission sp = new SocketPermission("[2001:878:200:4102:216:3eff:fe1a:c]:3306", "connect");
            Class.forName("org.postgresql.Driver").newInstance();
            String url = "goonhilly6.eitlab.ihk-edu.dk";
            String userName = "aimo3e14";
            String password = "s082577";
            Connection conn = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection established");
        } 
        catch (Exception e) 
        {
            System.out.println("Connection error!" + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws RemoteException
    {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pst = null;
                
    }
}
