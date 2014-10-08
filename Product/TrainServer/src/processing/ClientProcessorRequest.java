/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processing;

import common.interfaces.ProcessorRequest;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import transmission.common.connection.ClientConnection;
import transmission.common.MessageUtils;
import transmission.common.TransmissionPacket;

/**
 *
 * @author James
 */
public class ClientProcessorRequest implements ProcessorRequest
{
    private final Socket connection;

    public ClientProcessorRequest(ClientConnection clientConnection)
    {
        connection = clientConnection.getClientConnection();
    }

    @Override
    public void process()
    {
        try
        {
            if (connection == null)
            {
                Thread.currentThread().interrupt();
                return;
            }
            TransmissionPacket message = MessageUtils.getTransmission(connection);
            TransmissionInterpreter tI = new TransmissionInterpreter(message, connection);
        } catch (IOException | ClassNotFoundException e)
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
