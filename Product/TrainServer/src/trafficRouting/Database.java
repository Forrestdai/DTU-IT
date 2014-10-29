/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trafficRouting;

import java.sql.*;

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
        this.connection = DriverManager.getConnection(host);
    }
    
    public ResultSet pushStatement(String toPush) throws SQLException
    {
        if (connection == null)
        {
            connection = DriverManager.getConnection(host);
        }
        Statement statement = connection.createStatement();
        
        return statement.executeQuery(toPush);
    }
}
