package transmission;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import helpers.LogPrinter;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import processing.ServerProcessorRequest;
import threading.ThreadPerRequestScheduler;

/**
 *
 * @author James
 */
public class TestClientConnection
{
    private static final int TIMEOUT = 2000;

    IncomingUserConnectionsHandler serverConnection;

    private ThreadPerRequestScheduler serverThreadPool = new ThreadPerRequestScheduler();

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

    @Test(timeout = 500)
    public void shouldRunInUnder500ms() throws Exception
    {
        int numberOfClients = 20;
        AtomicInteger successfulConnectionAttempts = new AtomicInteger(0);
        Thread[] threads = new Thread[numberOfClients];

        for (int i = 0; i < threads.length; ++i)
        {
            threads[i] = new Thread(new CountingClient(i, successfulConnectionAttempts));
            threads[i].start();
        }

        for (int i = 0; i < threads.length; ++i)
        {
            threads[i].join();
        }

        LogPrinter.printTest("Number of successes: " + successfulConnectionAttempts.get());
        Assert.assertEquals(numberOfClients, successfulConnectionAttempts.get());
    }

    @Test(timeout = 5000)
    public void runManyClients() throws Exception
    {
        int numberOfClients = 1000;
        AtomicInteger successfulConnectionAttempts = new AtomicInteger(0);
        Thread[] threads = new Thread[numberOfClients];

        for (int i = 0; i < threads.length; ++i)
        {
            threads[i] = new Thread(new CountingClient(i, successfulConnectionAttempts));
            threads[i].start();
        }

        for (int i = 0; i < threads.length; ++i)
        {
            threads[i].join();
        }

        LogPrinter.printTest("Number of successes: " + successfulConnectionAttempts.get());
        Assert.assertEquals(numberOfClients, successfulConnectionAttempts.get());
    }
}
