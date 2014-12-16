
package database.interfaces;

import database.DataAccessedException;
import database.UserImp;
import java.sql.SQLException;


public interface DataBaseHandling
{
    public UserImp getUserInformationObject(UserImp user)throws SQLException;
//    public boolean getUserTransactionObject(UserImp user) throws SQLException;
    public void updateUserInformation(UserImp user) throws SQLException;
    public void updateUserTransactions(UserImp user)throws SQLException;
    public void createUser(UserImp user) throws SQLException;
  
}
