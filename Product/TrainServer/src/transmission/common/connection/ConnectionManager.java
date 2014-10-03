/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transmission.common.connection;

import exceptions.ServerSocketException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author James
 */
public class ConnectionManager
{

    private ServerSocket serverSocket;
    private final int socketTimeout = 2000;
    private final int port = 2954;

    public ConnectionManager() throws IOException //make new exception type
    {
        this.serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(socketTimeout);
    }

    public ClientConnection awaitClient() throws IOException
    {
        Socket socket = serverSocket.accept();
        return new ClientConnection(socket);
    }

    public void shutdown()
    {
        closeIgnoringException(serverSocket);

    }

    private void closeIgnoringException(ServerSocket serverSocket)
    {
        if (!serverSocket.isClosed())
        {
            try
            {
                serverSocket.close();
            } catch (IOException ex)
            {
                System.err.println("ERROR: Could not shut down server socket");
            }
        }
    }

}
