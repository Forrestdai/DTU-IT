/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.connection;

import java.io.*;
import java.net.*;

/**
 *
 * @author JamesFoxes
 */
class Listener
{

    InputInterpreterWorker inputWorker;
    private final ServerSocket socketToListenTo;
    private boolean running;
    ObjectInputStream readObject;

    public Listener(ServerSocket socketToListenOn)
    {
        running = true;
        this.socketToListenTo = socketToListenOn;

    }

    public void start()
    {
        while (running)
        {
            acceptConnection();
            inputWorker = new InputInterpreterWorker(readObject);
            inputWorker.spawnWorker();
        }
    }
    
    public void stop()
    {
        running = false;
    }

    private void acceptConnection()
    {
        try
        {
            Socket clientSocket = socketToListenTo.accept();
            readObject = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException ex)
        {
        }
    }
}
