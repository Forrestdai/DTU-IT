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
public class GetLogoutCommand implements Command
{

    private TransmissionPacket reply;

    public GetLogoutCommand()
    {
        this.reply = new TransmissionPacket();
    }

    @Override
    public void execute(Socket clientConnection, TransmissionPacket incomingPacket) throws IOException
    {
        //Calculate charge
        //Store chargeClient entry to array for later RMI access.
        //send ACKLOGOUT command and charge String
        reply.command = TransmissionPacket.Commands.ACKLOGOUT;

        MessageUtils.sendTransmission(clientConnection, reply);
    }

}
