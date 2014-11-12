/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficrouting;

import execute.Server;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author James
 */
public class SetupGraph
{

    private Map<Integer, TransportNode> nodes;

    public void buildGraph()
    {
        getNodesFromDatabase();
        assignNodeEdges();
    }
    
    public DirectedGraph<TransportNode> getGraph(int goalIdentity)
    {
        Graph graph = new Graph(goalIdentity);
        graph.addNodes(nodes);
        return graph.getDirectedGraph();
    }
    
    public boolean isInvalid()
    {
        return nodes == null || nodes.isEmpty();
    }
    
    public GraphTransmitObject getTransmitObject()
    {
        return new GraphTransmitObject(nodes);
    }
    
    public TransportNode getNode(int identity)
    {
        TransportNode returnNode = nodes.get(identity);
        if (returnNode == null)
        {
            throw new IllegalArgumentException("Node: " + identity + " doesn't exist.");
        }
        return nodes.get(identity);
    }

    private void getNodesFromDatabase()
    {
        nodes = new HashMap<>();
        try
        {
            nodes = Server.database.getAllNodes();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    private void assignNodeEdges()
    {
        for (Map.Entry<Integer, TransportNode> node : nodes.entrySet())
        {
            try
            {
                Integer nodeReference = node.getKey();
                TransportNode element = node.getValue();
                ArrayList<Edge> edges = Server.database.getEdgesFromStop(nodeReference, nodes);
                element.setEdges(edges);
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }
}
