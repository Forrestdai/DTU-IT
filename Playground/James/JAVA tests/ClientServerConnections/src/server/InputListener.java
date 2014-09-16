/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.connection.InputConnection;
import throwableErrors.PortConnectionError;

/**
 *
 * @author James
 */
public class InputListener
{

    private ServerSocket listenSocket;
    private final int PORT = 2954;
    private ArrayList<Worker> workers;
    private InputConnection connection;

    public void listen(InputConnection connection)
    {
        this.connection = connection;
        workers = new ArrayList<>();

        try
        {
            openConnection();

        } catch (PortConnectionError ex)
        {
        }
        while (true)
        {
            try (Socket clientSocket = listenSocket.accept())
            {
                new Worker(clientSocket.getInputStream()).start();
            } catch (IOException ex)
            {
                // TODO
            }
        }
    }

    private void openConnection() throws PortConnectionError
    {
        try
        {
            listenSocket = new ServerSocket(PORT);
            //listenSocket.setReuseAddress(true);
        } catch (IOException ex)
        {
            PortConnectionError errorLog = new PortConnectionError();

            if (listenSocket.isBound())
            {
                errorLog.portWasBound = true;
            }
            throw errorLog;
        } finally
        {
            try
            {
                if (listenSocket != null)
                {
                    listenSocket.close();
                }
            } catch (IOException IOe)
            {

            }
        }

    }

}
