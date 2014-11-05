/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection.tcp;

import connection.tcp.common.TCPConnection;
import connection.tcp.processing.ProcessConnection;
import connection.tcp.common.ClientConnection;
import execute.Client;
import java.io.IOException;

/**
 *
 * @author JamesFoxes
 */
public final class IncomingConnectionsHandler extends TCPConnection
{
    public IncomingConnectionsHandler() throws IOException
    {
        super();
    }

    @Override
    public void startProcessing()
    {
        while (keepProcessing)
        {
            try
            {
                ClientConnection clientConnection = connectionManager.awaitClient();
                ProcessConnection requestProcessor = new ProcessConnection(clientConnection);
                Client.threadPool.schedule(requestProcessor);
            } catch (IOException e)
            {
            }
        }
        stopProcessing();
    }
}
