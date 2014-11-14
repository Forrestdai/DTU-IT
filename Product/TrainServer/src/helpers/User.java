/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package helpers;

import java.io.Serializable;
import trafficrouting.TransportNode;

/**
 *
 * @author James
 */
public class User implements Serializable
{
    public Integer ID;
    public String firstName;
    public String lastName;
    public String passWord;
    public Double balance;
    
    public TransportNode startLocation;
    public TransportNode endLocation;

    public User(int ID)
    {
        this.ID = ID;
        firstName = "unknown";
        lastName = "unknown";
        passWord = "none";
        balance = 0.0;
    }
    
    
}
