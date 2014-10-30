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

    public Map<Integer, TransportNode> getAllNodes() throws SQLException
    {
        Map<Integer, TransportNode> nodes = new HashMap<>();

        String SQL = "SELECT * FROM STOPS_STATIONS";
        ResultSet stops = db.pushStatement(SQL);

        while (stops.next())
        {
            String name = stops.getString("STOP_NAME");
            int ref = stops.getInt("REF");
            double latitude = stops.getDouble("LAT");
            double longetude = stops.getDouble("LON");

            Position position = new Position(latitude, longetude);
            TransportNode nodeToAdd = new TransportNode(position, name, ref);
            nodes.put(ref, nodeToAdd);
        }
        return nodes;
    }

    public ArrayList<Edge> getEdgesFromStop(Integer fromStopReference, Map<Integer, TransportNode> nodes) throws SQLException
    {
        ArrayList<Edge> edges = new ArrayList<>();

        String getEdges = "SELECT *"
                + " FROM line_identity"
                + " INNER JOIN"
                + " paths lines ON (lines.line_ref = line_identity.line_ref)"
                + " AND from_stop_ref = ?";

        int[] properties =
        {
            fromStopReference
        };
        ResultSet stops = db.pushPreparedIntegerStatement(getEdges, properties);

        while (stops.next())
        {
            String type = stops.getString("TYPE");
            int fromStopRef = stops.getInt("FROM_STOP_REF");
            int toStopRef = stops.getInt("TO_STOP_REF");
            int transportID = stops.getInt("LINE_REF");
            String transportName = stops.getString("LINE_NAME");

            Edge.EdgeType edgeType = Edge.EdgeType.valueOf(type);
            Edge edge = new Edge(edgeType, nodes.get(fromStopRef), nodes.get(toStopRef), transportID, transportName);

            boolean addEdge = true;
            for (Edge existing : edges)
            {
                if (existing.toNode.referenceID == edge.toNode.referenceID && existing.transportLineReference == edge.transportLineReference)
                {
                    addEdge = false;
                    break;
                }
            }
            if (addEdge)
            {
                edges.add(edge);
            }
        }
        return edges;
    }

}
