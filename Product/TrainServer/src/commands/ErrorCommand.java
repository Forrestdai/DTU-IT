/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package commands;

import java.io.IOException;
import java.net.Socket;
import transmission.common.MessageUtils;
import transmission.common.TransmissionPacket;

/**
 *
 * @author JamesFoxes
 */
public class ErrorCommand implements Command
{

    TransmissionPacket reply;

    public ErrorCommand()
    {
        this.reply = new TransmissionPacket();
    }
    
    @Override
    public void execute(Socket clientConnection, TransmissionPacket incomingPacket) throws IOException
    {
        reply.dataObject = incomingPacket.dataObject;
        reply.dataString = incomingPacket.dataString;
        reply.command = TransmissionPacket.Commands.nil;
        
        MessageUtils.sendTransmission(clientConnection, reply);
    }
    
}
