/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection.tcp.commands;

import execute.Server;
import helpers.LogPrinter;
import java.io.IOException;
import java.net.Socket;
import connection.tcp.common.MessageUtils;
import connection.tcp.common.TransmissionPacket;
import helpers.User;

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
        User user = new User(getID(clientConnection, incomingPacket));

        if (Server.potentialUsers.userExists(user))
        {
            //The user has travelled
        }
        //Calculate charge
        //Store chargeClient entry to array for later RMI access.
        //send ACKLOGOUT command and charge String
        reply.command = TransmissionPacket.Commands.ACKLOGOUT;

        MessageUtils.sendTransmission(clientConnection, reply);
    }
    
    private int getID(Socket clientConnection, TransmissionPacket incomingPacket) throws IOException
    {
        try
        {
            return Integer.parseInt(incomingPacket.dataString);
        } catch (NumberFormatException e)
        {
            LogPrinter.printError("Couldn't parse userID from socket", e);
            new ErrorCommand().execute(clientConnection, incomingPacket);
            return -1;
        }
    }
}
