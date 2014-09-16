/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.net.*;
import server.connection.InputConnection;

/**
 *
 * @author James
 */
public class OpenConnection implements InputConnection
{

    private final Socket listenSocket;
    private OpenClient client;

    public OpenConnection(Socket listenSocket)
    {
        this.listenSocket = listenSocket;
        client.sentFrom = listenSocket;
    }

    @Override
    public Socket getSocket()
    {
        return listenSocket;
    }

    @Override
    public OpenClient probeConnection()
    {
        return client;
    }

}
