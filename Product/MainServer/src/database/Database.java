/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*;

/**
 *
 * @author James
 */
public class Database
{

    private String host = "jdbc:mysql://thelizard6.eitlab.ihk-edu.dk:3306/james3e14";
    private String user = "james3e14";
    private String pass = "s071954";
    Connection connection;

    public Database() throws SQLException
    {
        checkConnection();
    }

    public ResultSet pushStatement(String toPush) throws SQLException
    {
        checkConnection();
        Statement statement = connection.createStatement();

        ResultSet results = statement.executeQuery(toPush);
        connection.commit();

        return results;
    }

    public ResultSet pushPreparedStatement(String toPush, Object[] properties, boolean isUpdate) throws SQLException
    {
        checkConnection();
        PreparedStatement statement = null;

        statement = connection.prepareStatement(toPush);
        int i = 0;
        for (Object property : properties)
        {
            i++;
            if (property instanceof Integer)
            {
                statement.setInt(i, (Integer) property);
            }
            if (property instanceof String)
            {
                statement.setString(i, (String) property);
            }
            if (property instanceof Double)
            {
                statement.setDouble(i, (Double) property);
            }
        }
        ResultSet results = null;
        if (isUpdate)
        {
            statement.executeUpdate();
        } else
        {
            results = statement.executeQuery();
        }
        connection.commit();

        return results;
    }

    private void checkConnection() throws SQLException
    {
        if (connection == null || connection.isClosed())
        {
            connection = DriverManager.getConnection(host, user, pass);
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(connection.TRANSACTION_REPEATABLE_READ);
        }
    }
}
