package database;

import database.interfaces.Journey;
import database.interfaces.ProtectedUser;


public class ProtectedUserImp implements ProtectedUser  
{

    @Override
    public int getBalance()
    {
        throw new UnsupportedOperationException(); 
    }

    @Override
    public boolean addToBalance(int toAdd)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addJourney(Journey toAdd)
    {
        throw new UnsupportedOperationException();

}
}