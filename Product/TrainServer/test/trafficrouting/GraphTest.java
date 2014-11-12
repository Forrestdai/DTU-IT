/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficrouting;

import trafficrouting.AStarTraversal;
import trafficrouting.TransportNode;
import trafficrouting.DirectedGraph;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

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

    //@Test
    public void testAddNodesOneDirectionManual()
    {
        SetupGraph graph = new SetupGraph();
        DirectedGraph<TransportNode> directedGraph = graph.buildAndGetGraph(5);

        AStarTraversal graphTraverser = new AStarTraversal();
        Map<TransportNode, Double> result = graphTraverser.findShortestPaths(directedGraph, graph.getNode(0), graph.getNode(5));

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
