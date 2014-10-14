/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transmission;

import common.ServerData;
import execute.Server;
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

    String expectedReturnHost;
    String expectedReturnPort;
    String expectedReturnCode;

    @Before
    public void createServer() throws Exception
    {
        server = new SetupUDP();
        clientSocket = new MulticastSocket(ServerData.UDP_SERVER_PORT + 1);
        addressGroup = InetAddress.getByName(ServerData.UDP_ADDRESS);
        clientSocket.joinGroup(addressGroup);
        expectedReturnHost = ServerData.TCP_LOCAL_ADDRESS;
        expectedReturnPort = Integer.toString(ServerData.TCP_PORT);
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

        expectedReturnCode = Integer.toString(Server.UDPCode.code);

        for (int i = 0; i < clients.clients.length; ++i)
        {
            clients.returnMessages[i] = clients.clients[i].getReturnedMessage();
            LogPrinter.printTest("UDP Client " + i + " has recieved: " + clients.returnMessages[i]);
            assertEquals(expectedReturnHost, clients.returnMessages[i][0]);
            assertEquals(expectedReturnPort, clients.returnMessages[i][1]);
            assertEquals(expectedReturnCode, clients.returnMessages[i][2]);
        }
    }

    @Test(timeout = 10000)
    public void testUDPFullReception400Clients() throws Exception
    {
        ClientsUDPSetup clients = new ClientsUDPSetup(400, clientSocket);
        clients.startThreads();

        server.getGPS().transmit();

        expectedReturnCode = Integer.toString(Server.UDPCode.code);

        for (int i = 0; i < clients.clients.length; ++i)
        {
            clients.returnMessages[i] = clients.clients[i].getReturnedMessage();
            LogPrinter.printTest("UDP Client " + i + " has recieved: " + clients.returnMessages[i]);
            assertEquals(expectedReturnHost, clients.returnMessages[i][0]);
            assertEquals(expectedReturnPort, clients.returnMessages[i][1]);
            assertEquals(expectedReturnCode, clients.returnMessages[i][2]);
        }
    }
}
