package database;

import database.interfaces.DataBaseHandling;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
  * @author sudhir
 */
public class DatabaseHandler implements DataBaseHandling
{      
            DatabaseAccess database;

    public DatabaseHandler()
    {
        try
        {
            database = new DatabaseAccess();
        } catch (SQLException ex)
        {
        }
    }
    @Override
    public UserImp getUserInformationObject(UserImp user)throws SQLException
    {
Object[] properties =
        {
           user.getCustomerID()
        };
      String getString ="SELECT * FROM CustomerDB WHERE CustomerID = ?";
      
      ResultSet rs = database.pushPreparedStatement(getString, properties);
      UserImp returnUser = new UserImp();
      returnUser.setCustomerID(-1);
      
        while(rs.next())
        {
           
       returnUser = new UserImp(rs.getInt("customerid"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("email"),rs.getString("Password"));
        }
         return  returnUser;
    }
       @Override
    public void updateUserInformation(UserImp user) throws SQLException
    {
        Object[] properties =
        {
            user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getCustomerID()
        };
      String updateString = "update customerdb FirstName = ?, LastName = ?, Email = ?, Password =?, where CustomerID = ?";
         database.pushPreparedStatement(updateString, properties);
                }

    @Override
    public void updateUserTransactions(UserImp user)throws SQLException
    {
       
               
        }
        
   
    @Override
    public void createUser(UserImp user) throws SQLException
    {
        Object[] properties =
        {
            user.getCustomerID(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword()
        };
        String userString = "insert into  customerdb values (?,?,?,?,?)";
        database.pushPreparedStatement(userString, properties);
    
    }}

