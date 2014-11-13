/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import helpers.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import trafficrouting.DirectedGraph;
import trafficrouting.Edge;
import trafficrouting.Position;
import trafficrouting.SetupGraph;
import trafficrouting.TransportNode;

/**
 *
 * @author JamesFoxes
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

    /**
     * Test of addUser method, of class DatabaseHandler.
     */
    @Test
    public void testAddUser()
    {
        User user = null;
        DatabaseHandler instance = new DatabaseHandler();
        instance.addUser(user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUser method, of class DatabaseHandler.
     */
    @Test
    public void testGetUser() throws Exception
    {
        int userID = 0;
        DatabaseHandler instance = new DatabaseHandler();
        User expResult = null;
        User result = instance.getUser(userID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateUser method, of class DatabaseHandler.
     */
    @Test
    public void testUpdateUser()
    {
        User user = null;
        DatabaseHandler instance = new DatabaseHandler();
        instance.updateUser(user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of chargeUser method, of class DatabaseHandler.
     */
    @Test
    public void testChargeUser()
    {
        User user = null;
        double charge = 0.0;
        DatabaseHandler instance = new DatabaseHandler();
        instance.chargeUser(user, charge);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllNodes method, of class DatabaseHandler.
     */
    @Test
    public void testGetAllNodes() throws Exception
    {
        DatabaseHandler database = new DatabaseHandler();
        Map<Integer, TransportNode> expResult = null;
        Map<Integer, TransportNode> result = database.getAllNodes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEdgesFromStop method, of class DatabaseHandler.
     */
    @Test
    public void testGetEdgesFromStop() throws Exception
    {
        int fromStopReference = 702;
        int toStopReference = 703;

        DatabaseHandler database = new DatabaseHandler();
        Map<Integer, TransportNode> nodes = new HashMap<>();

        nodes.put(701, new TransportNode(new Position(55.9, 12.2), "A", 701, 2));
        nodes.put(fromStopReference, new TransportNode(new Position(54.8, 12.7), "B", fromStopReference, 5));
        nodes.put(toStopReference, new TransportNode(new Position(55.2, 12.4), "C", toStopReference, 5));

        ArrayList<Edge> edges = database.getEdgesFromStop(fromStopReference, nodes);

        for (Edge edge : edges)
        {
            assertEquals(3189813, edge.transportLineReference);
        }
    }

    @Test
    public void buildGraph()
    {
        SetupGraph graph = new SetupGraph();
        graph.buildGraph();
        DirectedGraph<TransportNode> directedGraph = graph.getGraph(5);

        int numberOfStops = 0;
        for (TransportNode node : directedGraph)
        {
            System.out.println(node.identity);
            assertTrue("invalid nodes added to directed graph", node.referenceID >= 0);
            ++numberOfStops;

            int numberOfEdges = 0;
            for (Edge edge : node)
            {
                assertTrue("circular node reference from " + node.identity, !edge.toNode.equals(node));
                assertTrue("invalid fromNode reference fromNode = " + edge.fromNode.identity, edge.fromNode.equals(node));
                ++numberOfEdges;
            }
            if (node.zone != 0)
            {
                assertTrue("No edges added to " + node.identity, numberOfEdges > 0);
            }
            
        }
        assertTrue("No stops added to directed graph", numberOfStops > 0);
    }

}
