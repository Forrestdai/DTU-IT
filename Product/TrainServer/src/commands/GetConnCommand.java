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
public class GetConnCommand implements Command
{

    private TransmissionPacket reply;

    public GetConnCommand()
    {
        this.reply = new TransmissionPacket();
    }

    @Override
    public void execute(Socket clientConnection, TransmissionPacket incomingPacket) throws IOException
    {
        //Check for user in POTENTIAL users array
        //IF the object exists, move it to ACTIVE users array

        //ELSE Check for user in ACTIVE users array
        //IF the object exists, return.
        //ELSE get user object from server
        //store user object to POTENTIAL users array
        //send ACK command
        reply.command = TransmissionPacket.Commands.ACKNOWLEDGE;

        MessageUtils.sendTransmission(clientConnection, reply);
    }

}
