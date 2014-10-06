/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transmission;

import transmission.common.TCPConnection;
import execute.Server;
import processing.ClientProcessorRequest;
import transmission.common.connection.ClientConnection;
import java.io.IOException;

/**
 *
 * @author JamesFoxes
 */
public final class IncomingUserConnectionsHandler extends TCPConnection
{
    public IncomingUserConnectionsHandler() throws IOException
    {
        super();
    }

    @Override
    public void startProcessing()
    {
        System.out.println("Server Starting");

        while (keepProcessing)
        {
            try
            {
                ClientConnection clientConnection = connectionManager.awaitClient();
                ClientProcessorRequest requestProcessor = new ClientProcessorRequest(clientConnection);
                Server.threadPool.schedule(requestProcessor);
            } catch (IOException e)
            {
                System.err.println("ERROR main loop");
                e.printStackTrace();
                break;
            }
        }
        stopProcessing();
    }
}
