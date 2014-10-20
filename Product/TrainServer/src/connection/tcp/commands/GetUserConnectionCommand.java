/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection.tcp.commands;

import execute.Server;
import helpers.LogPrinter;
import helpers.ServerState;
import java.io.IOException;
import java.net.Socket;
import connection.tcp.common.MessageUtils;
import connection.tcp.common.TransmissionPacket;
import helpers.User;

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
        int userID = checkConnectionValidityReturnUserID(incomingPacket);
        if (userID != -1)
        {
            User user = new User();
            user.ID = userID;

            if (Server.potentialUsers.testIfContainsUser(user))
            {
                Server.potentialUsers.removeUser(user);
                Server.activeUsers.pushUser(user);
            } else
            {
                if (!Server.activeUsers.testIfContainsUser(user))
                {
                    Server.serverTransmitter.requestUser(user);
                }
            }
            reply.command = TransmissionPacket.Commands.ACKNOWLEDGE;
            reply.dataString = incomingPacket.dataString;
        } else
        {
            reply.command = TransmissionPacket.Commands.nil;
            reply.dataString = "illegal registration";
        }
        MessageUtils.sendTransmission(clientConnection, reply);
    }

    private int checkConnectionValidityReturnUserID(TransmissionPacket incomingPacket)
    {
        try
        {
            if (Server.state == ServerState.arrivedAtStation)
            {
                String[] message = incomingPacket.dataString.split("\\s+");
                if (Integer.parseInt(message[1]) == Server.UDPCode.code)
                {
                    return Integer.parseInt(message[0]);
                }
            }
            return -1;

        } catch (Exception e)
        {
            return -1;
        }
    }
}
