package database;

import database.interfaces.CopyableUser;
import database.interfaces.DataBaseHandling;
import database.interfaces.ProtectedUser;

/**
 *
 * @author sudhir
 */
public class DatabaseHandler implements DataBaseHandling
{
    

    @Override
    public CopyableUser getUserInformationObject()
    {
        throw new UnsupportedOperationException();    
    }

    @Override
    public ProtectedUser getUserTransactionObject()
    {
        throw new UnsupportedOperationException(); 
    }

    @Override
    public void updateUserInformation(CopyableUser user)
    {
        throw new UnsupportedOperationException(); 
    }

    @Override
    public void updateUserTransactions(ProtectedUser user)
    {
        throw new UnsupportedOperationException(); 
    }

    @Override
    public void createUser(CopyableUser user)
    {
        throw new UnsupportedOperationException(); 
    }
    
}
