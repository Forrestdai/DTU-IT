/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficRouting;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author James
 */
public class SetupGraph
{

    DatabaseHandler dbHandler;
    private Map<String, TransportNode> nodes;

    public SetupGraph()
    {
        this.dbHandler = new DatabaseHandler();
    }

    public DirectedGraph<TransportNode> process(String goalIdentity)
    {
        getNodesFromDatabase();
        assignNodeEdges();

        Graph graph = new Graph(goalIdentity);
        graph.addNodes(nodes);
        return graph.getGraph();
    }

    private Map<String, TransportNode> getNodesFromDatabase()
    {
        nodes = new HashMap<>();
        try
        {
            DatabaseHandler dbHandler = new DatabaseHandler();              //MOVE TO EXTERNAL REF
            nodes = dbHandler.getAllNodes();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return nodes;
    }

    private void assignNodeEdges()
    {
        for (Map.Entry<String, TransportNode> node : nodes.entrySet())
        {
            try
            {
                String nodeName = node.getKey();
                TransportNode element = node.getValue();
                ArrayList<Edge> edges = dbHandler.getEdgesForNode(element, nodes);
                element.setEdges(edges);
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }
}
