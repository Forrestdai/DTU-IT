/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.connection;

import TestMOCKs.MockClient;
import TestMOCKs.MockObject;
import interfaces.RecievablePacket;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.PriorityQueue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author James
 */
public class TCPServerTest
{

    private final int PORT = 2954;
    private Listener server; 

    public TCPServerTest()
    {
    }

    @BeforeClass
    public static void setUpClass() throws IOException
    {
    }

    @AfterClass
    public static void tearDownClass()
    {
    }
/*
    @Before
    public void setUp() throws IOException
    {
        TCPServer.incomingQueue = new PriorityQueue<>();
        server = TCPServer.InitializeServer();
        server.start();
    }

    @After
    public void tearDown()
    {
        server.stop();
    }
*/
    /**
     * Test of main method, of class TCPServer.
     */
    //@Test
    public void testCreateListenerMainExecution() throws Exception
    {
        Listener toCompare = TCPServer.InitializeServer();
        Listener expected = new Listener(new ServerSocket(PORT));
        
        Assert.assertTrue(toCompare.equals(expected));
    }
    
    @Test
    public void testRecievePacket() throws Exception
    {
        MockObject objectToSend = new MockObject();
        MockClient client = new MockClient(PORT, "localhost", objectToSend);
        client.runMockclient();
        
        RecievablePacket result = TCPServer.incomingQueue.poll();
        Assert.assertEquals(objectToSend, result);
   }

}
