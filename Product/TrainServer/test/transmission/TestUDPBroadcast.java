/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transmission;

import common.ServerData;
import helpers.LogPrinter;
import java.net.InetAddress;
import java.net.MulticastSocket;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author JamesFoxes
 */
public class TestUDPBroadcast
{

    SetupUDP server;
    InetAddress addressGroup;
    MulticastSocket clientSocket;

    String expectedReturnMessage;

    @Before
    public void createServer() throws Exception
    {
        server = new SetupUDP();
        clientSocket = new MulticastSocket(ServerData.UDP_SERVER_PORT + 1);
        addressGroup = InetAddress.getByName(ServerData.UDP_ADDRESS);
        clientSocket.joinGroup(addressGroup);
        expectedReturnMessage = "HELO" + ServerData.TCP_LOCAL_ADDRESS + ServerData.TCP_PORT;
    }
    
    @After
    public void teardownServer() throws Exception
    {
        clientSocket.leaveGroup(addressGroup);
        clientSocket.close();
    }

    @Test(timeout = 10000)
    public void testUDPSingleclient() throws Exception
    {
        ClientsUDPSetup clients = new ClientsUDPSetup(1, clientSocket);
        clients.startThreads();

        server.getGPS().transmit();
        
        for (int i = 0; i < clients.clients.length; ++i)
        {
            clients.returnMessages[i] = clients.clients[i].getReturnedMessage();
            LogPrinter.print("UDP Client " + i + " has recieved: " + clients.returnMessages[i]);
            assertEquals(expectedReturnMessage, clients.returnMessages[i]);
        }
    }

    @Test(timeout = 1000000)
    public void testUDPFullReception400Clients() throws Exception
    {
        ClientsUDPSetup clients = new ClientsUDPSetup(400, clientSocket);
        clients.startThreads();

        server.getGPS().transmit();
        
        clients.readMessages();
        
        int successes = 0;
        for (int i = 0; i < clients.clients.length; ++i)
        {
            if(clients.returnMessages[i].equals(expectedReturnMessage))
            {
                ++successes;
            }
        }
        assertEquals(clients.clients.length, successes);

        for (int i = 0; i < clients.clients.length; ++i)
        {
            LogPrinter.print("UDP Client " + i + " has recieved: " + clients.returnMessages[i]);
            assertEquals(expectedReturnMessage, clients.returnMessages[i]);
        }
    }
}
