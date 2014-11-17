/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package connection.tcp.commands;

import java.io.IOException;
import java.net.Socket;
import connection.tcp.common.MessageUtils;
import connection.tcp.common.TransmissionPacket;
import helpers.Journey;
import helpers.User;

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
        //Might look into not using a User object here:

        User requestingUser = (User) incomingPacket.dataObject;
        Journey journey = new Journey(requestingUser.startLocation, requestingUser.endLocation);
        reply.command = TransmissionPacket.Commands.ACKNOWLEDGE;
        reply.dataObject = journey.getJourney();
        reply.dataString = Double.toString(journey.getCost());
        
        MessageUtils.sendTransmission(clientConnection, reply);
    }
    
}
