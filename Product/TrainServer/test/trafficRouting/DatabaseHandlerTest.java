/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficRouting;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author James
 */
public class DatabaseHandlerTest
{

    public DatabaseHandlerTest()
    {
    }

    @BeforeClass
    public static void setUpClass()
    {
    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
    }
    
    @Test
    public void testStuff()
    {
        DirectedGraph<TransportNode> graph = new SetupGraph().process("G");
        
    }

    //@Test
    public void testGetAllNodes() throws SQLException
    {
        DatabaseHandler dbHandler = new DatabaseHandler();
        Map<String, TransportNode> nodes = dbHandler.getAllNodes();
        for (Map.Entry<String, TransportNode> node : nodes.entrySet())
        {
            String nodeName = node.getKey();
            TransportNode element = node.getValue();
            ArrayList<Edge> edges = dbHandler.getEdgesForNode(element, nodes);
            element.setEdges(edges);
        }
        
        Graph graph = new Graph(nodes.get("G")); //G = goal
        graph.addNodes(nodes.values());
        DirectedGraph<TransportNode> graphToSearch = graph.getGraph();
        
        AStarTraversal graphTraverser = new AStarTraversal();
        Map<TransportNode, Double> result = graphTraverser.findShortestPaths(graphToSearch, nodes.get("S"), nodes.get("G"));
        
        for (Map.Entry<TransportNode, Double> entrySet : result.entrySet())
        {
            TransportNode key = entrySet.getKey();
            Double value = entrySet.getValue();
            
            System.out.println("key: " + key.identity);
            System.out.println("value: " + value);
            System.out.println("PATH:" + key.returnNode.identity);
            System.out.println("");
        }
    }

}
