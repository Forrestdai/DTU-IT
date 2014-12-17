/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection.tcp.commands;

import connection.tcp.common.MessageUtils;
import connection.tcp.common.TransmissionPacket;
import helpers.ServerData;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author James
 */
public class UpdateTicketCostCommand implements Command
{

    @Override
    public void execute(Socket clientConnection, TransmissionPacket incomingPacket) throws IOException, ClassNotFoundException
    {
        int newValue = Integer.parseInt(incomingPacket.dataString);
        TransmissionPacket reply = new TransmissionPacket();
        
        if (newValue != 0)
        {
            ServerData.costPerZone = newValue;
            reply.command = TransmissionPacket.Commands.ACKNOWLEDGE;
        }
        
        MessageUtils.sendTransmission(clientConnection, reply);
    }

}
