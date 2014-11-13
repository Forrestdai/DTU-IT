/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficrouting;

import trafficrouting.DirectedGraph;
import depricated.DijkstraTraversal;
import java.util.Collection;
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
public class DijkstraTraversalTest
{

    public DijkstraTraversalTest()
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

    @Test(timeout = 200)
    public void testFindShortestPathsKnownGraph()
    {
        DijkstraTraversal graphTraverser = new DijkstraTraversal();
        DirectedGraph<Integer> graphToSearch = new DirectedGraph<>();

        for (int i = 0; i < 8; i++)
        {
            graphToSearch.addNode(i);
        }

        graphToSearch.addEdge(0, 1, 9);
        graphToSearch.addEdge(0, 2, 7);
        graphToSearch.addEdge(0, 4, 5);

        graphToSearch.addEdge(1, 0, 9);
        graphToSearch.addEdge(1, 5, 5);

        graphToSearch.addEdge(2, 0, 7);
        graphToSearch.addEdge(2, 5, 8);
        graphToSearch.addEdge(2, 6, 8);

        graphToSearch.addEdge(3, 5, 3);
        graphToSearch.addEdge(3, 7, 7);

        graphToSearch.addEdge(4, 0, 5);

        graphToSearch.addEdge(5, 1, 5);
        graphToSearch.addEdge(5, 2, 8);
        graphToSearch.addEdge(5, 3, 3);
        graphToSearch.addEdge(5, 6, 5);

        graphToSearch.addEdge(6, 2, 8);
        graphToSearch.addEdge(6, 5, 5);
        graphToSearch.addEdge(6, 7, 1);

        graphToSearch.addEdge(7, 3, 7);
        graphToSearch.addEdge(7, 6, 1);

        Map<Integer, Double> result = graphTraverser.findShortestPaths(graphToSearch, 0);

        Iterator<Map.Entry<Integer, Double>> values = result.entrySet().iterator();

        assertEquals(0.0, values.next().getValue(), 0.1);
        assertEquals(9.0, values.next().getValue(), 0.1);
        assertEquals(7.0, values.next().getValue(), 0.1);
        assertEquals(17.0, values.next().getValue(), 0.1);
        assertEquals(5.0, values.next().getValue(), 0.1);
        assertEquals(14.0, values.next().getValue(), 0.1);
        assertEquals(15.0, values.next().getValue(), 0.1);
        assertEquals(16.0, values.next().getValue(), 0.1);

    }

    @Test(timeout = 600)
    public void testFindShortestPathsManyRandom()
    {
        DijkstraTraversal graphTraverser = new DijkstraTraversal();
        DirectedGraph<Integer> graphToSearch = new DirectedGraph<>();
        int nodes = 10000;
        int edgesPerNode = nodes/100;
        int maxPathCost = 1;

        for (int i = 0; i < nodes; i++)
        {
            graphToSearch.addNode(i);
        }

        for (int i = 0; i < nodes; i++)
        {
            int edgeNumber = edgesPerNode;
            for (int j = 0; j < edgeNumber; j++)
            {
                int from = i;
                int to = (int) (Math.random() * i);
                Double cost = (Math.random() * maxPathCost);
                graphToSearch.addEdge(from, to, cost);
            }
        }

        long timeToSort = System.nanoTime();

        Map<Integer, Double> result = graphTraverser.findShortestPaths(graphToSearch, (int) (Math.random() * nodes));

        timeToSort = System.nanoTime() - timeToSort;

        double timeToSortMilli = ((double) timeToSort) / 1000000;

        int connectedNodes = 0;
        
        for (Map.Entry<Integer, Double> entry : result.entrySet())
        {
            Integer key = entry.getKey();
            Double value = entry.getValue();

            if (Double.isFinite(value))
            {
                ++connectedNodes;
            }
        }

        System.out.println("Time to sort:" + timeToSortMilli + " ms. with: " + connectedNodes + " out of " + nodes + " connected nodes");
    }
}
