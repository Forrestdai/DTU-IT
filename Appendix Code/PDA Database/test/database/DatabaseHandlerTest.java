package database;

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
     * Test of getUserInformationObject method, of class DatabaseHandler.
     * @throws java.lang.Exception
     */
       
      // @Test (timeout = 5000)
    public void testNewAddUser() throws Exception
    {
        DatabaseHandler database = new DatabaseHandler();
        UserImp user = new UserImp(55, "hello", "World", "someEmail", "5555");

        database.createUser(user);
        System.out.println("connected");
    }

    //@Test (timeout = 6000)
    public void testNewUpdateUser() throws Exception
    {
        DatabaseHandler database = new DatabaseHandler();
        UserImp user = new UserImp(14, "Helo", "World", "someEmail", "555555");

        database.createUser(user);

    }
 
    @Test
    public void testGetUserInformationObject() throws Exception
    {
        System.out.println("GetUserInformationObject");

        UserImp user = new UserImp();
        user.setCustomerID(4);

        DatabaseHandler database = new DatabaseHandler();
        UserImp result = database.getUserInformationObject(user);
        System.out.println(result.getFirstName() + " " + result.getLastName() + " " + result.getEmail() + " " + result.getPassword() + " " + result.getBalance());
        assertEquals(4, result.getCustomerID());

    }

 
}
