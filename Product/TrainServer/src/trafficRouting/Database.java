/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficRouting;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author James
 */
public class Database
{

    private final String host = "jdbc:derby://localhost:1527/TrafficNetwork";
    Connection connection;

    public Database() throws SQLException
    {
        checkConnection();
    }

    public ResultSet pushStatement(String toPush) throws SQLException
    {
        checkConnection();
        Statement statement = connection.createStatement();

        return statement.executeQuery(toPush);
    }

    public ResultSet pushPreparedIntegerStatement(String toPush, int[] properties) throws SQLException
    {
        checkConnection();
        PreparedStatement statement = null;

        statement = connection.prepareStatement(toPush);
        int i = 0;
        for (int property : properties)
        {
            i++;
            statement.setInt(i, property);
        }
        ResultSet results = statement.executeQuery();
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
