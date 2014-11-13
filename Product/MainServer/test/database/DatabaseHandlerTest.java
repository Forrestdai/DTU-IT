/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import helpers.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.omg.CosNaming.NamingContextPackage.NotFound;
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

    @Test
    public void testAddUser() throws SQLException, NotFound, InterruptedException
    {
        DatabaseHandler database = new DatabaseHandler();
        User user = database.getUser(0);
        user.balance = 50.0;
        database.removeUser(0);
        
        database.addUser(user);
        
        user = database.getUser(0);
        assertEquals(50, user.balance, 0.001);
    }

    @Test
    public void testGetUser() throws SQLException, NotFound, InterruptedException
    {
        DatabaseHandler database = new DatabaseHandler();
        User user = database.getUser(0);
        
        assertEquals("default", user.firstName);
        assertEquals("default", user.lastName);
        assertEquals("default", user.passWord);
    }

    @Test (timeout = 5500)
    public void testUpdateUser() throws SQLException, NotFound, InterruptedException
    {
        DatabaseHandler database = new DatabaseHandler();
        User user = database.getUser(0);
        user.balance = 0.0;
        
        database.updateUser(user);  //updating takes precedense during execution.
        database.chargeUser(user, 5);
        Thread.sleep(4500);
        user = database.getUser(0);
        assertEquals(-5.0, user.balance, 0.001);
    }

    @Test (timeout = 5500)
    public void testChargeUser() throws SQLException, NotFound, InterruptedException
    {
        DatabaseHandler database = new DatabaseHandler();
        User user = database.getUser(0);
        double currentBalance = user.balance;
        
        database.chargeUser(user, 5);
        Thread.sleep(4500);
        user = database.getUser(0);
        assertEquals(5, currentBalance - user.balance, 0.001);
    }

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
