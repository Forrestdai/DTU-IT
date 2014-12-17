package transmission;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import connection.tcp.IncomingConnectionsHandler;
import helpers.LogPrinter;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import execute.SimpleProcessorRequest;
import threading.ThreadPerRequestScheduler;
import connection.tcp.common.TransmissionPacket;

/**
 *
 * @author James
 */
public class TestClientConnection
{
    IncomingConnectionsHandler serverConnection;

    private ThreadPerRequestScheduler serverThreadPool = new ThreadPerRequestScheduler();
    private ExecutorService scheduler;

    @Before
    public void createServer() throws Exception
    {
        scheduler = Executors.newCachedThreadPool();
        serverConnection = new IncomingConnectionsHandler();
        SimpleProcessorRequest setupServer = new SimpleProcessorRequest(serverConnection);
        serverThreadPool.schedule(setupServer);
    }

    @After
    public void shutdownServer() throws InterruptedException
    {
        scheduler.shutdownNow();
        if (serverConnection != null)
        {
            serverConnection.stopProcessing();
        }
    }

    @Test(timeout = 500)
    public void shouldRunInUnder500ms() throws Exception
    {
        int numberOfClients = 20;
        ArrayList<Future<TransmissionPacket>> replies = new ArrayList<>();
        AtomicInteger successfulConnectionAttempts = new AtomicInteger(0);

        for (int i = 0; i < numberOfClients; ++i)
        {
            replies.add(scheduler.submit(new CountingClient(i, successfulConnectionAttempts)));
        }

        for (Future<TransmissionPacket> reply : replies)
        {
            reply.get();    //wait for threads.
        }
        
        LogPrinter.printTest("Number of successes: " + successfulConnectionAttempts.get());
        Assert.assertEquals(numberOfClients, successfulConnectionAttempts.get());
    }

    @Test(timeout = 5000)
    public void runManyClients() throws Exception
    {
        int numberOfClients = 1000;
        ArrayList<Future<TransmissionPacket>> replies = new ArrayList<>();
        AtomicInteger successfulConnectionAttempts = new AtomicInteger(0);

        for (int i = 0; i < numberOfClients; ++i)
        {
           replies.add(scheduler.submit(new CountingClient(i, successfulConnectionAttempts)));
        }

        for (Future<TransmissionPacket> reply : replies)
        {
            reply.get();    //wait for threads.
        }

        LogPrinter.printTest("Number of successes: " + successfulConnectionAttempts.get());
        Assert.assertEquals(numberOfClients, successfulConnectionAttempts.get());
    }
}
