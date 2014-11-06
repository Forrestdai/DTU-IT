/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author Aimo
 */
public class RMITrainImplementation extends UnicastRemoteObject implements RMITrainInterface
{
        private final ArrayList<RMIPassengerInterface> passengerInfo;
        
        public RMITrainImplementation(ArrayList<RMIPassengerInterface> passengerValues) throws RemoteException{
            passengerInfo = passengerValues;
        }
        
        public ArrayList<RMIPassengerInterface> getRMITrainPassengers() throws RemoteException{
            return passengerInfo;
        }
}
