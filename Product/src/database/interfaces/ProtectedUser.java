/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package database.interfaces;

import java.rmi.Remote;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author James
 */
public interface ProtectedUser extends Remote
{
    public String UIDNumber = "";
    public AtomicInteger balance = new AtomicInteger();
    
    public int getBalance();
    public boolean addToBalance(int toAdd);
    public boolean addJourney(Journey toAdd);   //Might not get implemented, created 10/2/2014
}
