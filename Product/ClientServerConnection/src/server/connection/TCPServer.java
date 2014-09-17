/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.connection;

import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author JamesFoxes
 */
public class TCPServer
{

    private static Listener listener;
    private static final int PORT = 2954;

    

    public static void main(String[] args) throws IOException
    {
        listener = InitializeServer();
        listener.start();
    }
    
    public static Listener InitializeServer() throws IOException
    {
        return new Listener(new ServerSocket(PORT));
    }
}
