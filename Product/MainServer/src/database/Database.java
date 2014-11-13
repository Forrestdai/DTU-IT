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

    private String host = "jdbc:derby://localhost:1527/TrafficNetwork";
    Connection connection;

    public Database(String host) throws SQLException
    {
        this.host = host;
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
            connection = DriverManager.getConnection(host);
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(connection.TRANSACTION_REPEATABLE_READ);
        }
    }
}
