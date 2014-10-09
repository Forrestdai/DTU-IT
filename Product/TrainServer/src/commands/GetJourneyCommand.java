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
public class GetJourneyCommand implements Command
{

    private TransmissionPacket reply;

    public GetJourneyCommand()
    {
        this.reply = new TransmissionPacket();
    }
    
    @Override
    public void execute(Socket clientConnection, TransmissionPacket incomingPacket) throws IOException
    {
        //calculate journey object
        //send object and JOURNEY command
        reply.command = TransmissionPacket.Commands.JOURNEY;
        
        MessageUtils.sendTransmission(clientConnection, reply);
    }
    
}
