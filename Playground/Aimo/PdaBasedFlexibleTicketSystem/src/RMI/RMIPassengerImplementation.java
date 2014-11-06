/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.rmi.*;
import java.rmi.server.*;

/**
 *
 * @author Aimo
 */
public class RMIPassengerImplementation extends UnicastRemoteObject implements RMIPassengerInterface
{
    private final int customerID;
    private String firstName;
    private String lastName;
    private String eMail;

    public RMIPassengerImplementation(int Passenger) throws RemoteException
    {
        customerID = Passenger;
    }
    public int getCustomerID() throws RemoteException
    {
        return customerID;
    }

    public String getFirstName() throws RemoteException
    {
        return firstName;
    }

    public void setFirstName(String firstName) throws RemoteException
    {
        this.firstName = firstName;
    }

    public String getLastName() throws RemoteException
    {
        return lastName;
    }

    public void setLastName(String lastName) throws RemoteException
    {
        this.lastName = lastName;
    }

    public String geteMail() throws RemoteException
    {
        return eMail;
    }

    public void seteMail(String eMail) throws RemoteException
    {
        this.eMail = eMail;
    } 
    
}
