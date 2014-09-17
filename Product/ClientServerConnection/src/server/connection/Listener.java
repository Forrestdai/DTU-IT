/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.connection;

import java.io.*;
import java.net.*;
import java.util.Objects;

/**
 *
 * @author JamesFoxes
 */
class Listener
{

    InputInterpreterWorker inputWorker;
    private final ServerSocket socketToListenTo;
    private final boolean running;
    ObjectInputStream readObject;

    public Listener(ServerSocket socketToListenOn)
    {
        running = true;
        this.socketToListenTo = socketToListenOn;

    }

    void start()
    {
        while (running)
        {
            acceptConnection();
            inputWorker = new InputInterpreterWorker(readObject);
            inputWorker.spawnWorker();
        }
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

    @Override
    public boolean equals(Object compareTo)
    {
        if (this == compareTo)
        {
            return true;
        }
        if (!(compareTo instanceof Listener))
        {
            return false;
        }
        Listener toTest = (Listener) compareTo;
        return this.hashCode() == toTest.hashCode();
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 71 * hash + this.socketToListenTo.getLocalPort();
        return hash;
    }
}
