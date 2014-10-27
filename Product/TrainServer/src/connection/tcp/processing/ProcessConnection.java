/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection.tcp.processing;

import connection.tcp.TransmissionInterpreter;
import common.interfaces.ProcessorRequest;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import connection.tcp.common.ClientConnection;
import connection.tcp.common.MessageUtils;
import connection.tcp.common.TransmissionPacket;

/**
 *
 * @author James
 */
public class ProcessConnection implements ProcessorRequest
{
    private final Socket connection;

    public ProcessConnection(ClientConnection clientConnection)
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
            new TransmissionInterpreter(message, connection);
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
