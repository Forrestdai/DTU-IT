/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficRouting;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author James
 */
public class DatabaseHandler
{

    Database db;

    public DatabaseHandler()
    {
        try
        {
            this.db = new Database();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public Map<String, TransportNode> getAllNodes() throws SQLException
    {
        Map<String, TransportNode> nodes = new HashMap<>();
        //ArrayList<TransportNode> result = new ArrayList<>();

        String SQL = "SELECT * FROM Nodes";
        ResultSet rs = db.pushStatement(SQL);

        while (rs.next())
        {
            String id = rs.getString("ID");
            double positionX = rs.getDouble("PositionX");
            double positionY = rs.getDouble("PositionY");

            Position position = new Position(positionX, positionY);
            TransportNode nodeToAdd = new TransportNode(position, id);
            nodes.put(id, nodeToAdd);
        }
        return nodes;
    }

    public ArrayList<Edge> getEdgesForNode(TransportNode fromNode, Map<String, TransportNode> nodes) throws SQLException
    {
        ArrayList<Edge> result = new ArrayList<>();

        String SQL = "SELECT * FROM Edges WHERE FromNode = '" + fromNode.identity + "'";
        ResultSet rs = db.pushStatement(SQL);

        while (rs.next())
        {
            Edge.EdgeType type = Edge.EdgeType.valueOf(rs.getString("TransportType"));
            String toNode = rs.getString("ToNode");

            Edge edge = new Edge(type, fromNode, nodes.get(toNode));
            result.add(edge);
        }

        return result;
    }

}
