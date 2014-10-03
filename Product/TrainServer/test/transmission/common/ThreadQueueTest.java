/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package transmission.common;

import depricated.ThreadQueue;
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
public class ThreadQueueTest
{
    
    public ThreadQueueTest()
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
     * Test of getClientHandler method, of class ThreadQueue.
     */
    @Test
    public void testGetClientHandler()
    {
        System.out.println("getClientHandler");
        Runnable toRun = null;
        ThreadQueue instance = new ThreadQueue();
        Thread expResult = null;
        Thread result = instance.getClientHandler(toRun);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of returnClientHandler method, of class ThreadQueue.
     */
    @Test
    public void testReturnClientHandler()
    {
        System.out.println("returnClientHandler");
        Thread clientHandler = null;
        ThreadQueue instance = new ThreadQueue();
        instance.returnClientHandler(clientHandler);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
