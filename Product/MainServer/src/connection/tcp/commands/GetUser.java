/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection.tcp.commands;

import connection.tcp.common.MessageUtils;
import connection.tcp.common.TransmissionPacket;
import helpers.User;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author JamesFoxes
 */
public class GetUser implements Command
{

    @Override
    public void execute(Socket clientConnection, TransmissionPacket incomingPacket) throws IOException
    {
        TransmissionPacket reply = new TransmissionPacket();
        User user = new User();
        user.ID = Integer.parseInt(incomingPacket.dataString);
        reply.dataObject = user;
        MessageUtils.sendTransmission(clientConnection, reply);
    }
    
}
