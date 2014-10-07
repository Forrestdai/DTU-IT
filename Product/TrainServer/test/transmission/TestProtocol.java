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
import transmission.common.TransmissionPackage;

/**
 *
 * @author JamesFoxes
 */
public class TestProtocol
{

    private static final int PORT = 2954;
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
        Thread.sleep(1000);
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
        TransmissionPackage toSend = new TransmissionPackage();
        TransmissionPackage toRecieve = new TransmissionPackage();

        toSend.command = TransmissionPackage.Commands.GETINFO;
        toRecieve.command = TransmissionPackage.Commands.INFO;

        testCommand(toSend, toRecieve);
    }

    @Test(timeout = TIMEOUT)
    public void clientGETJOURNEY() throws Exception
    {
        TransmissionPackage toSend = new TransmissionPackage();
        TransmissionPackage toRecieve = new TransmissionPackage();

        toSend.command = TransmissionPackage.Commands.GETJOURNEY;
        toRecieve.command = TransmissionPackage.Commands.JOURNEY;

        testCommand(toSend, toRecieve);
    }

    @Test(timeout = TIMEOUT)
    public void clientLOGOUT() throws Exception
    {
        TransmissionPackage toSend = new TransmissionPackage();
        TransmissionPackage toRecieve = new TransmissionPackage();

        toSend.command = TransmissionPackage.Commands.LOGOUT;
        toRecieve.command = TransmissionPackage.Commands.ACKLOGOUT;

        testCommand(toSend, toRecieve);
    }

    @Test(timeout = TIMEOUT)
    public void clientOKUID() throws Exception
    {
        TransmissionPackage toSend = new TransmissionPackage();
        TransmissionPackage toRecieve = new TransmissionPackage();

        toSend.command = TransmissionPackage.Commands.CONN;
        toRecieve.command = TransmissionPackage.Commands.ACK;

        testCommand(toSend, toRecieve);
    }

    private void testCommand(TransmissionPackage sendMessage, TransmissionPackage expectedReturn) throws Exception
    {
        int numberOfClients = 5;
        Thread[] threads = new Thread[numberOfClients];
        CommandClient[] clients = new CommandClient[numberOfClients];

        for (int i = 0; i < threads.length; ++i)
        {
            clients[i] = new CommandClient(PORT, sendMessage);
            threads[i] = new Thread(clients[i]);
            threads[i].start();
        }

        for (int i = 0; i < threads.length; ++i)
        {
            threads[i].join();
        }

        TransmissionPackage[] returnedMessage = new TransmissionPackage[numberOfClients];
        for (int i = 0; i < threads.length; i++)
        {
            returnedMessage[i] = new TransmissionPackage();
            returnedMessage[i] = clients[i].getReturnedMessage();
        }

        for (TransmissionPackage returned : returnedMessage)
        {
            assertEquals(expectedReturn.command, returned.command);
        }
    }
}
