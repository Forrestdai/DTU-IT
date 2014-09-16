/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import helpers.OpenClient;
import helpers.OpenConnection;
import java.io.IOException;
import java.net.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import server.InputListener;
import server.connection.InputConnection;

/**
 *
 * @author James
 */
public class ConnectionTest
{
    InputConnection inputInterface;
    Socket inputSocket;
    
    public ConnectionTest() throws IOException
    {
        System.out.println("1");
        inputSocket = new Socket("thelizard6.eitlab.ihk-edu.dk", 2954);
        System.out.println("2");
        inputInterface = new OpenConnection(inputSocket);
        System.out.println("3");
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
     * Test of main method, of class InputListener.
     */
    @Test
    public void inputConnectionPromptShouldConnect() throws Exception
    {
        System.out.println("4");
        InputListener listener = new InputListener();
        System.out.println("5");
        listener.listen(inputInterface);
        System.out.println("6");
        OpenClient client = inputInterface.probeConnection();
        System.out.println("7");
        System.out.println(client.sentFrom.getLocalAddress() + " <---");
        assertEquals(client.sentFrom.getLocalAddress(), "localhost");
    }
    
}
