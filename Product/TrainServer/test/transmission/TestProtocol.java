/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transmission;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import processing.ServerProcessorRequest;
import threading.ThreadPerRequestScheduler;
import static org.junit.Assert.*;
import transmission.common.TransmissionPacket;

/**
 *
 * @author JamesFoxes
 */
public class TestProtocol
{

    private static final int TIMEOUT = 5000;

    IncomingUserConnectionsHandler serverConnection;

    private ThreadPerRequestScheduler serverThreadPool;

    public TestProtocol()
    {
        serverThreadPool = new ThreadPerRequestScheduler();
    }

    @Before
    public void createServer() throws Exception
    {
        serverConnection = new IncomingUserConnectionsHandler();
        ServerProcessorRequest setupServer = new ServerProcessorRequest(serverConnection);
        serverThreadPool.schedule(setupServer);
    }

    @After
    public void shutdownServer() throws InterruptedException
    {
        if (serverConnection != null)
        {
            serverConnection.stopProcessing();
        }
    }

    @Test(timeout = TIMEOUT)
    public void clientGETINFO() throws Exception
    {
        TransmissionPacket toSend = new TransmissionPacket();
        TransmissionPacket toRecieve = new TransmissionPacket();

        toSend.command = TransmissionPacket.Commands.GETINFO;
        toRecieve.command = TransmissionPacket.Commands.INFO;

        testCommand(toSend, toRecieve);
        
        toSend.dataString = "This does nothing";
        toRecieve.dataString = null;

        testDataString(toSend, toRecieve);
    }

    @Test(timeout = TIMEOUT)
    public void clientGETJOURNEY() throws Exception
    {
        TransmissionPacket toSend = new TransmissionPacket();
        TransmissionPacket toRecieve = new TransmissionPacket();

        toSend.command = TransmissionPacket.Commands.GETJOURNEY;
        toRecieve.command = TransmissionPacket.Commands.JOURNEY;

        testCommand(toSend, toRecieve);
        
        toSend.dataString = "This does nothing";
        toRecieve.dataString = null;

        testDataString(toSend, toRecieve);
    }

    @Test(timeout = TIMEOUT)
    public void clientLOGOUT() throws Exception
    {
        TransmissionPacket toSend = new TransmissionPacket();
        TransmissionPacket toRecieve = new TransmissionPacket();

        toSend.command = TransmissionPacket.Commands.LOGOUT;
        toRecieve.command = TransmissionPacket.Commands.ACKLOGOUT;

        testCommand(toSend, toRecieve);
        
        toSend.dataString = "This does nothing";
        toRecieve.dataString = null;

        testDataString(toSend, toRecieve);
    }

    @Test(timeout = TIMEOUT)
    public void clientOKUID() throws Exception
    {
        TransmissionPacket toSend = new TransmissionPacket();
        TransmissionPacket toRecieve = new TransmissionPacket();

        toSend.command = TransmissionPacket.Commands.CONN;
        toRecieve.command = TransmissionPacket.Commands.ACK;

        testCommand(toSend, toRecieve);
        
        toSend.dataString = "This does nothing";
        toRecieve.dataString = null;

        testDataString(toSend, toRecieve);
    }
    
    private void testCommand(TransmissionPacket sendMessage, TransmissionPacket expectedReturn) throws Exception
    {
        TransmissionPacket[] returnedMessage = getReturnPacket(sendMessage, expectedReturn);
        
        for (TransmissionPacket returned : returnedMessage)
        {
            assertEquals(expectedReturn.command, returned.command);
        }
    }
    
    private void testDataString(TransmissionPacket sendMessage, TransmissionPacket expectedReturn) throws Exception
    {
        TransmissionPacket[] returnedMessage = getReturnPacket(sendMessage, expectedReturn);
        
        for (TransmissionPacket returned : returnedMessage)
        {
            assertEquals(expectedReturn.dataString, returned.dataString);
        }
    }
    
    private TransmissionPacket[] getReturnPacket(TransmissionPacket sendMessage, TransmissionPacket expectedReturn) throws Exception
    {
        int numberOfClients = 20;
        Thread[] threads = new Thread[numberOfClients];
        TransmissionClient[] clients = new TransmissionClient[numberOfClients];

        for (int i = 0; i < threads.length; ++i)
        {
            clients[i] = new TransmissionClient(sendMessage);
            threads[i] = new Thread(clients[i]);
            threads[i].start();
        }

        for (int i = 0; i < threads.length; ++i)
        {
            threads[i].join();
        }

        TransmissionPacket[] returnedMessage = new TransmissionPacket[numberOfClients];
        for (int i = 0; i < threads.length; i++)
        {
            returnedMessage[i] = new TransmissionPacket();
            returnedMessage[i] = clients[i].getReturnedMessage();
        }
        return returnedMessage;
    }
}
