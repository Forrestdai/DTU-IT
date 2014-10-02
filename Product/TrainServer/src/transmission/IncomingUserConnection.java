/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transmission;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import transmission.common.MessageUtils;
import transmission.common.ThreadQueue;

/**
 *
 * @author JamesFoxes
 */
public class IncomingUserConnection implements Connection, Runnable
{
    ThreadQueue clientHandlers;

    ServerSocket serverSocket;
    volatile boolean keepProcessing = true;

    public IncomingUserConnection(int port, int millisecondsTimeout) throws IOException
    {
        clientHandlers = new ThreadQueue();
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(millisecondsTimeout);
    }

    @Override
    public void run()
    {
        System.out.println("Server Starting");

        while (keepProcessing)
        {
            try
            {
                System.out.println("accepting client");
                Socket socket = serverSocket.accept();
                System.out.println("Got client connection");
                process(socket);
            } catch (Exception e)
            {

            }
        }
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
        closeIgnoringException(serverSocket);
    }

    private void process(Socket socket)
    {
        if (socket == null)
        {
            return;
        }

        Runnable clientHandler = new Runnable()
        {
            public void run()
            {
                try
                {
                    String message = MessageUtils.getMessage(socket);
                    MessageUtils.sendMessage(socket, "Processed: " + message);
                    closeIgnoringException(socket);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        };

        Thread clientConnection = clientHandlers.getClientHandler(clientHandler);
        clientConnection.start();
    }

    private void closeIgnoringException(Socket socket)
    {
        if (socket != null)
        {
            try
            {
                socket.close();
            } catch (IOException ignore)
            {

            }
        }
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
