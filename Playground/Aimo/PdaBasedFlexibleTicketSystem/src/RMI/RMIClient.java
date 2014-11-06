/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.rmi.Naming;
import java.util.ArrayList;

/**
 *
 * @author Aimo
 */
public class RMIClient
{
    
    private static final String HOST = "goonhilly6.eitlab.ihk-edu.dk";
    
    
    public static void main(String[] args)
    {
        try 
        {
            RMITrainInterface temp = (RMITrainInterface)Naming.lookup("rmi://" + HOST + "/RMI");
            
            ArrayList<RMIPassengerInterface> passengerDetails = temp.getRMITrainPassengers();
            
                        
        } catch (Exception e) {
        }
    }
}
