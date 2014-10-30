/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficRouting;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author James
 */
public class SetupGraph
{

    DatabaseHandler dbHandler;
    private Map<Integer, TransportNode> nodes;

    public SetupGraph()
    {
        this.dbHandler = new DatabaseHandler();
    }

    public DirectedGraph<TransportNode> buildAndGetGraph(int goalIdentity)
    {
        getNodesFromDatabase();
        assignNodeEdges();
        
        for (Map.Entry<Integer, TransportNode> entrySet : nodes.entrySet())
        {
            Integer key = entrySet.getKey();
            TransportNode value = entrySet.getValue();
            
            for (Iterator<Edge> iterator = value.iterator(); iterator.hasNext();)
            {
                Edge edge = iterator.next();
            }
        }

        Graph graph = new Graph(goalIdentity);
        graph.addNodes(nodes);
        return graph.getDirectedGraph();
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
            nodes = dbHandler.getAllNodes();
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
                ArrayList<Edge> edges = dbHandler.getEdgesFromStop(nodeReference, nodes);
                element.setEdges(edges);
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }
}
