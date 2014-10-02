package transmission;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import execute.Server;
import java.io.IOException;
import java.net.Socket;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import transmission.common.MessageUtils;

/**
 *
 * @author James
 */
public class ClientTest
{

    private static final int PORT = 2954;
    private static final int TIMEOUT = 2000;

    IncomingUserConnection server;
    Thread serverThread;

    @Before
    public void createServer() throws Exception
    {
        try
        {
            server = new IncomingUserConnection(PORT, TIMEOUT);
            serverThread = new Thread(server);
            serverThread.start();
        } catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        }
    }

    @After
    public void shutdownServer() throws InterruptedException
    {
        if (server != null)
        {
            server.stopProcessing();
            serverThread.join();
        }
    }

    class TrivialClient implements Runnable
    {

        int clientNumber;

        TrivialClient(int clientNumber)
        {
            this.clientNumber = clientNumber;
        }

        @Override
        public void run()
        {
            try
            {
                connectSendRecieve(clientNumber);
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Test(timeout = 10000)
    public void shouldRunInUnder10Seconds() throws Exception
    {
        Thread[] threads = new Thread[20];
        for (int i = 0; i < threads.length; ++i)
        {
            threads[i] = new Thread(new TrivialClient(i));
            threads[i].start();
        }

        for (int i = 0; i < threads.length; ++i)
        {
            threads[i].join();
        }
    }

    private void connectSendRecieve(int i) throws IOException
    {
        System.out.printf("Client %2d: connecting\n", i);
        Socket socket = new Socket("localhost", PORT);

        System.out.printf("Client %2d: sending message\n", i);
        MessageUtils.sendMessage(socket, Integer.toString(i));

        System.out.printf("Client %2d: getting reply\n", i);
        MessageUtils.getMessage(socket);

        System.out.printf("Client %2d: finished\n", i);
        socket.close();
    }
}
