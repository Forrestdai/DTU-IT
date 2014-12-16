
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author sudhir
 */
public class DatabaseAccess
{

    private String userName = "sudhir3e14";
    private String password = "s137239";
    private String url = "jdbc:postgresql://goonhilly6.eitlab.ihk-edu.dk:5432/sudhir3e14";
    private Connection connection;

    public DatabaseAccess() throws SQLException
    {
        checkConnection();
    }

    public boolean pushStatement(String toPush) throws SQLException
    {
        checkConnection();
        Statement statement = connection.createStatement();

        return statement.execute(toPush);
    }

    public ResultSet pushPreparedStatement(String toPush, Object[] properties) throws SQLException
    {
        checkConnection();
        PreparedStatement statement = null;

        statement = connection.prepareStatement(toPush);
        int i = 0;
        for (Object property : properties)
        {
            if (property instanceof Integer)
            {
                i++;
                statement.setInt(i, (int) property);
            } else
            {
                if (property instanceof String)
                {
                    i++;
                    statement.setString(i, (String) property);
                }
            }
        }
        ResultSet toReturn = statement.executeQuery();
        connection.commit();
        return toReturn;
    }

    private void checkConnection() throws SQLException
    {
        if (connection == null || connection.isClosed())
        {
            connection = DriverManager.getConnection(url, userName, password);
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(connection.TRANSACTION_REPEATABLE_READ);
        }
    }
}
