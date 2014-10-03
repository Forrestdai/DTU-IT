/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transmission;

import processing.ClientRequestProcessor;
import transmission.common.connection.ConnectionManager;
import transmission.common.connection.ClientConnection;
import transmission.common.connection.Connection;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;
import threading.ExecutorTCPPool;

/**
 *
 * @author JamesFoxes
 */
public class IncomingUserConnection implements Connection, Runnable
{
    ConnectionManager connectionManager;
    ExecutorTCPPool clientScheduler;

    volatile boolean keepProcessing = true;

    public IncomingUserConnection() throws IOException
    {
        clientScheduler = new ExecutorTCPPool();
        connectionManager = new ConnectionManager();
    }

    @Override
    public void run()
    {
        System.out.println("Server Starting");

        while (keepProcessing)
        {
            try
            {
                ClientConnection clientConnection = connectionManager.awaitClient();
                ClientRequestProcessor requestProcessor = new ClientRequestProcessor(clientConnection);
                clientScheduler.schedule(requestProcessor);
            } catch (Exception e)
            {
                System.err.println("ERROR main loop");
                e.printStackTrace();
                break;
            }
        }
        
        stopProcessing();
    }

    private void handle(Exception e)
    {
        if (!(e instanceof SocketException))
        {
            e.printStackTrace();
        }
    }

    public void stopProcessing()
    {
        keepProcessing = false;
        connectionManager.shutdown();
    }

    private void closeIgnoringException(ServerSocket serverSocket)
    {
        if (serverSocket != null)
        {
            try
            {
                serverSocket.close();
            } catch (IOException ignore)
            {

            }
        }
    }

    @Override
    public void send()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
