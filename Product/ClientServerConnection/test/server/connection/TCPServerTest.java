/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.connection;

import java.net.ServerSocket;
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

    public TCPServerTest()
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
     * Test of main method, of class TCPServer.
     */
    @Test
    public void testCreateListenerMainExecution() throws Exception
    {
        Listener toCompare = TCPServer.InitializeServer();
        //toCompare.start();
        int hash1 = toCompare.hashCode();
        
        Listener expected = new Listener(new ServerSocket(PORT));
        //expected.start();
        int hash2 = expected.hashCode();
        
        Assert.assertEquals(hash1, hash2);
    }

}
