package database;

import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sudhir
 */
public class DataBaseConnectionImplTest
{
    
    public DataBaseConnectionImplTest()
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
     * Test of doUpdate method, of class DataBaseConnectionImpl.
     */
    @Test
    public void testDoUpdate() throws SQLException
    {
        System.out.println("doUpdate");
        String doUpdate = "peter";
//         String doUpdate = "5";
//        String lastName = "dai";
        
        DataBaseConnectionImpl instance = new DataBaseConnectionImpl();
        boolean expResult = false;
        boolean result = instance.doUpdate(doUpdate );
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }}

    /**
     * Test of doCreate method, of class DataBaseConnectionImpl.
     */
//    @Test
//    public void testDoCreate()
//    {
//        System.out.println("doCreate");
//        DataBaseConnectionImpl instance = new DataBaseConnectionImpl();
//        boolean expResult = false;
//        boolean result = instance.doCreate();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of doFetch method, of class DataBaseConnectionImpl.
//     */
//    @Test
//    public void testDoFetch()
//    {
//        System.out.println("doFetch");
//        DataBaseConnectionImpl instance = new DataBaseConnectionImpl();
//        boolean expResult = false;
//        boolean result = instance.doFetch();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of doDelete method, of class DataBaseConnectionImpl.
//     */
//    @Test
//    public void testDoDelete()
//    {
//        System.out.println("doDelete");
//        DataBaseConnectionImpl instance = new DataBaseConnectionImpl();
//        boolean expResult = false;
//        boolean result = instance.doDelete();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
//}
