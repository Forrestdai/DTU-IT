package database;

import database.interfaces.DataBaseConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * @author sudhir
 */
public class DataBaseConnectionImpl implements DataBaseConnection
{

    Connection conn;
    Statement statement;
    PreparedStatement pst;
    ResultSet rs;

    public DataBaseConnectionImpl() throws SQLException
    {
        String url = "jdbc:postgresql://goonhilly6.eitlab.ihk-edu.dk:5432/sudhir3e14";
        String userName = "sudhir3e14";
        String password = "s137239";
        conn = DriverManager.getConnection(url, userName, password);
        Statement statement = conn.createStatement();
        conn.setAutoCommit(false);
        conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

    }

    private boolean transmitToDatabase(String toCommit) throws SQLException
    {
        pst.execute(toCommit);
        conn.commit();
        return false;
    }

    @Override
    public boolean doUpdate(String toUpdate)
    {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        int result = 0;
        
        String change = "UPDATE customerInfo"
                                 + "SET firstName = ?"   //'sudhir',
                                 + "lastName =?"  //'kumar',
                                 +"email = ?"     //'sudhir.np.dk@mail.com'
                                 + "where id = ?";

      try
        {
            String url = "jdbc:postgresql://goonhilly6.eitlab.ihk-edu.dk:5432/sudhir3e14";
            String userName = "sudhir3e14";
            String password = "s137239";
            conn = DriverManager.getConnection(url, userName, password);
           statement = conn.prepareStatement(change);

            result = preparedStatement.executeUpdate();
            preparedStatement.executeUpdate();
            

        } catch (SQLException e)
        {
            e.printStackTrace();
        
        } finally
        {
            try
            {
                preparedStatement.close();
                conn.close();
            } catch (SQLException ex)
            {
                Logger.getLogger(DataBaseConnectionImpl.class.getName()).log(Level.SEVERE, null, ex);

            }
            if (result > 0)
            {
                return true;
            } else
            {
                return false;
            }
        }

    }
    @Override
    public boolean doCreate()
    {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        int result = 0;
        try
        {
            String url = "jdbc:postgresql://goonhilly6.eitlab.ihk-edu.dk:5432/sudhir3e14";
            String userName = "sudhir3e14";
            String password = "s137239";
            conn = DriverManager.getConnection(url, userName, password);
            statement = conn.prepareStatement("insert into customerinfo values (?, ?, ?, ?)");
//           pst.setString(1,CopyableUserImp.customerid);
//              pst.setString(2,CopyableUserImp.firstName);

            result = preparedStatement.executeUpdate();
        } catch (Exception e)
        {

            e.printStackTrace();
        } finally
        {
            try
            {
                preparedStatement.close();
                conn.close();
            } catch (SQLException ex)
            {
                Logger.getLogger(DataBaseConnectionImpl.class.getName()).log(Level.SEVERE, null, ex);

            }
            if (result > 0)
            {
                return true;
            } else
            {
                return false;
            }
        }

    }

    @Override
    public boolean doFetch()
    {

        try
        {
            String searchQuery = " select * from customerinfo where customerId = ?";
            pst = conn.prepareStatement(searchQuery);
            rs = pst.executeQuery();

            if (rs.next())
            {
                String add1 = rs.getString("CustomerId");
                String add2 = rs.getString("firstName");
                String add3 = rs.getString("lastName");
                String add4 = rs.getString("email");

            }

        } catch (SQLException ex)
        {
            Logger.getLogger(DataBaseConnectionImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean doDelete()

    {
        try
        {
            String sql = "delete from customerInfo where CustomerId =?";
            pst = conn.prepareStatement(sql);
            pst.execute();

        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }

        return false;

    }

}