/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.rmi.*;

/**
 *
 * @author Aimo
 */
public interface RMIPassengerInterface extends Remote
{
    public int getCustomerID() throws RemoteException;
    
    public String getFirstName() throws RemoteException;
    public void setFirstName(String firstName) throws RemoteException;
    
    public String getLastName() throws RemoteException;
    public void setLastName(String lastName) throws RemoteException;
    
    public String geteMail() throws RemoteException;
    public void seteMail(String eMail) throws RemoteException;
}
