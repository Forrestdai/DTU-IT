/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Aimo
 */
public class RMIPassengerImplementationTest
{
    
    public RMIPassengerImplementationTest()
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
     * Test of getCustomerID method, of class RMIPassengerImplementation.
     */
    @Test
    public void testGetCustomerID() throws Exception
    {
        System.out.println("getCustomerID");
        RMIPassengerImplementation instance = new RMIPassengerImplementation();
        int expResult = 0;
        int result = instance.getCustomerID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCustomerID method, of class RMIPassengerImplementation.
     */
    @Test
    public void testSetCustomerID() throws Exception
    {
        System.out.println("setCustomerID");
        int customerID = 0;
        RMIPassengerImplementation instance = new RMIPassengerImplementation();
        instance.setCustomerID(customerID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFirstName method, of class RMIPassengerImplementation.
     */
    @Test
    public void testGetFirstName()
    {
        System.out.println("getFirstName");
        RMIPassengerImplementation instance = new RMIPassengerImplementation();
        String expResult = "";
        String result = instance.getFirstName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFirstName method, of class RMIPassengerImplementation.
     */
    @Test
    public void testSetFirstName()
    {
        System.out.println("setFirstName");
        String firstName = "";
        RMIPassengerImplementation instance = new RMIPassengerImplementation();
        instance.setFirstName(firstName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLastName method, of class RMIPassengerImplementation.
     */
    @Test
    public void testGetLastName()
    {
        System.out.println("getLastName");
        RMIPassengerImplementation instance = new RMIPassengerImplementation();
        String expResult = "";
        String result = instance.getLastName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLastName method, of class RMIPassengerImplementation.
     */
    @Test
    public void testSetLastName()
    {
        System.out.println("setLastName");
        String lastName = "";
        RMIPassengerImplementation instance = new RMIPassengerImplementation();
        instance.setLastName(lastName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of geteMail method, of class RMIPassengerImplementation.
     */
    @Test
    public void testGeteMail()
    {
        System.out.println("geteMail");
        RMIPassengerImplementation instance = new RMIPassengerImplementation();
        String expResult = "";
        String result = instance.geteMail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of seteMail method, of class RMIPassengerImplementation.
     */
    @Test
    public void testSeteMail()
    {
        System.out.println("seteMail");
        String eMail = "";
        RMIPassengerImplementation instance = new RMIPassengerImplementation();
        instance.seteMail(eMail);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
