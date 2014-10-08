/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transmission.common.connection;

import static common.ServerData.TCP_PORT;
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

    public ConnectionManager() throws IOException //make new exception type
    {
        this.serverSocket = new ServerSocket(TCP_PORT);
        //serverSocket.setSoTimeout(socketTimeout);
    }

    public ClientConnection awaitClient() throws IOException
    {
        Socket socket = serverSocket.accept();
        return new ClientConnection(socket);
    }

    public void shutdown()
    {
        closeIgnoringException(serverSocket);   //will throw serverSocket.accept exception in incoming tcp loop
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
            }
        }
    }

}
