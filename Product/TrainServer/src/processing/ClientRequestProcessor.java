/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processing;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import transmission.common.connection.ClientConnection;
import transmission.common.MessageUtils;

/**
 *
 * @author James
 */
public class ClientRequestProcessor
{
    private Socket connection;

    public ClientRequestProcessor(ClientConnection clientConnection)
    {
        connection = clientConnection.getClientConnection();
    }

    public void process()
    {
        try
        {
            //Thread.sleep(1000);
            if (connection == null)
            {
                Thread.currentThread().interrupt();
                return;
            }
            
            String message = MessageUtils.getMessage(connection);
            MessageUtils.sendMessage(connection, "Processed: ");
        } catch (IOException e)
        {
            e.printStackTrace();
        } 
    }
    
        private void handle(Exception e)
    {
        if (!(e instanceof SocketException))
        {
            e.printStackTrace();
        }
    }

}
