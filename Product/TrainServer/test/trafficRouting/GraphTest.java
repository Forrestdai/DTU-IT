/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficRouting;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author JamesFoxes
 */
public class GraphTest
{
    
    public GraphTest()
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
    public void testAddNodesOneDirection()
    {
        ArrayList<TransportNode> nodes = new ArrayList<>();
        nodes.add(new TransportNode(new Position(-3000, -100), "Start"));
        nodes.add(new TransportNode(new Position(-3000, 220), "A"));
        nodes.add(new TransportNode(new Position(-2400, 0), "B"));
        nodes.add(new TransportNode(new Position(-1850, 150), "C"));
        nodes.add(new TransportNode(new Position(-1300, 180), "D"));
        nodes.add(new TransportNode(new Position(-700, -50), "Goal"));
        
        nodes.get(0).addEdge(new Edge(Edge.EdgeType.WALKING, nodes.get(0), nodes.get(1))); //maybe write a get by identifier method
        nodes.get(0).addEdge(new Edge(Edge.EdgeType.WALKING, nodes.get(0), nodes.get(2)));
        
        nodes.get(1).addEdge(new Edge(Edge.EdgeType.BUS, nodes.get(1), nodes.get(2)));
        nodes.get(1).addEdge(new Edge(Edge.EdgeType.BUS, nodes.get(1), nodes.get(3)));
        
        nodes.get(2).addEdge(new Edge(Edge.EdgeType.BUS, nodes.get(2), nodes.get(3)));
        nodes.get(2).addEdge(new Edge(Edge.EdgeType.TRAIN, nodes.get(2), nodes.get(5)));
        
        nodes.get(3).addEdge(new Edge(Edge.EdgeType.BUS, nodes.get(3), nodes.get(4)));
        nodes.get(3).addEdge(new Edge(Edge.EdgeType.BUS, nodes.get(3), nodes.get(5)));
        
        nodes.get(4).addEdge(new Edge(Edge.EdgeType.BUS, nodes.get(4), nodes.get(5)));
        
        Graph graph = new Graph(nodes.get(5));
        graph.addNodes(nodes);
        
        DirectedGraph<TransportNode> graphToSearch = graph.getGraph();
        
        AStarTraversal graphTraverser = new AStarTraversal();
        Map<TransportNode, Double> result = graphTraverser.findShortestPaths(graphToSearch, nodes.get(0), nodes.get(5));
        //Iterator<Map.Entry<TransportNode, Double>> values = result.entrySet().iterator();
        
        for (Map.Entry<TransportNode, Double> entrySet : result.entrySet())
        {
            TransportNode key = entrySet.getKey();
            Double value = entrySet.getValue();
            
//            System.out.println("key: " + key.identity);
//            System.out.println("value: " + value);
//            System.out.println("PATH:" + key.returnNode.identity);
//            System.out.println("");
        }
    }
    
    @Test
    public void testAddNodesBothDirections()
    {
        ArrayList<TransportNode> nodes = new ArrayList<>();
        nodes.add(new TransportNode(new Position(-3000, -100), "Start"));
        nodes.add(new TransportNode(new Position(-3000, 220), "A"));
        nodes.add(new TransportNode(new Position(-2400, 0), "B"));
        nodes.add(new TransportNode(new Position(-1850, 150), "C"));
        nodes.add(new TransportNode(new Position(-1300, 180), "D"));
        nodes.add(new TransportNode(new Position(-700, -50), "Goal"));
        
        nodes.get(0).addEdge(new Edge(Edge.EdgeType.WALKING, nodes.get(0), nodes.get(1))); //maybe write a get by identifier method
        nodes.get(0).addEdge(new Edge(Edge.EdgeType.WALKING, nodes.get(0), nodes.get(2)));
        
        nodes.get(1).addEdge(new Edge(Edge.EdgeType.BUS, nodes.get(1), nodes.get(2)));
        nodes.get(1).addEdge(new Edge(Edge.EdgeType.BUS, nodes.get(1), nodes.get(3)));
        nodes.get(1).addEdge(new Edge(Edge.EdgeType.WALKING, nodes.get(1), nodes.get(0)));
        
        nodes.get(2).addEdge(new Edge(Edge.EdgeType.BUS, nodes.get(2), nodes.get(3)));
        nodes.get(2).addEdge(new Edge(Edge.EdgeType.TRAIN, nodes.get(2), nodes.get(5)));
        nodes.get(2).addEdge(new Edge(Edge.EdgeType.WALKING, nodes.get(2), nodes.get(0)));
        nodes.get(2).addEdge(new Edge(Edge.EdgeType.BUS, nodes.get(2), nodes.get(1)));
        
        nodes.get(3).addEdge(new Edge(Edge.EdgeType.BUS, nodes.get(3), nodes.get(4)));
        nodes.get(3).addEdge(new Edge(Edge.EdgeType.BUS, nodes.get(3), nodes.get(5)));
        nodes.get(3).addEdge(new Edge(Edge.EdgeType.BUS, nodes.get(3), nodes.get(1)));
        nodes.get(3).addEdge(new Edge(Edge.EdgeType.BUS, nodes.get(3), nodes.get(2)));
        
        nodes.get(4).addEdge(new Edge(Edge.EdgeType.BUS, nodes.get(4), nodes.get(5)));
        nodes.get(4).addEdge(new Edge(Edge.EdgeType.BUS, nodes.get(4), nodes.get(3)));
        
        nodes.get(5).addEdge(new Edge(Edge.EdgeType.BUS, nodes.get(5), nodes.get(4)));
        nodes.get(5).addEdge(new Edge(Edge.EdgeType.TRAIN, nodes.get(5), nodes.get(2)));
        nodes.get(5).addEdge(new Edge(Edge.EdgeType.BUS, nodes.get(5), nodes.get(3)));
        
        Graph graph = new Graph(nodes.get(5));
        graph.addNodes(nodes);
        
        DirectedGraph<TransportNode> graphToSearch = graph.getGraph();
        
        AStarTraversal graphTraverser = new AStarTraversal();
        Map<TransportNode, Double> result = graphTraverser.findShortestPaths(graphToSearch, nodes.get(0), nodes.get(5));
        //Iterator<Map.Entry<TransportNode, Double>> values = result.entrySet().iterator();
        
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

    @Test
    public void testGetGraph()
    {
    }
    
}
