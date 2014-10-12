/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import execute.Server;
import helpers.LogPrinter;
import java.io.IOException;
import java.net.Socket;
import transmission.common.MessageUtils;
import transmission.common.TransmissionPacket;
import users.User;

/**
 *
 * @author JamesFoxes
 */
public class GetUserConnectionCommand implements Command
{

    private TransmissionPacket reply;

    public GetUserConnectionCommand()
    {
        this.reply = new TransmissionPacket();
    }

    @Override
    public void execute(Socket clientConnection, TransmissionPacket incomingPacket) throws IOException
    {
        User user = new User();
        user.ID = getID(clientConnection, incomingPacket);

        if (Server.potentialUsers.testIfContainsUser(user))
        {
            Server.potentialUsers.removeUser(user);
            Server.activeUsers.pushUser(user);
        } else
        {
            if (Server.activeUsers.testIfContainsUser(user))
            {
                return;
            }
            Server.serverTransmitter.requestUser(user);
        }
        //ACK
        reply.dataString = incomingPacket.dataString;
        reply.command = TransmissionPacket.Commands.ACKNOWLEDGE;
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
