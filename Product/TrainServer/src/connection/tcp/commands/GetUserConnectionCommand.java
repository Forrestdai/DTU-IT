/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection.tcp.commands;

import execute.Server;
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
    public void execute(Socket clientConnection, TransmissionPacket incomingPacket) throws IOException, ClassNotFoundException
    {
        int userID = Integer.parseInt(incomingPacket.dataString); //checkConnectionValidityReturnUserID(incomingPacket);
        if (userID != -1)
        {
            User user = new User();
            user.ID = userID;

            compareWithArrays(user);

            reply.command = TransmissionPacket.Commands.ACKNOWLEDGE;
            reply.dataString = incomingPacket.dataString;
        } else
        {
            reply.command = TransmissionPacket.Commands.nil;
            reply.dataString = "illegal registration";
        }
        MessageUtils.sendTransmission(clientConnection, reply);
    }

    private void waitToReply(User user)
    {
        while (!Server.potentialUsers.userExists(user) && !Server.activeUsers.userExists(user))
        {
            try
            {
                Thread.sleep(Server.serverTransmitter.getDelay() / 4);
            } catch (InterruptedException ex)
            {
            }
        }
    }

    private void compareWithArrays(User user) throws IOException, ClassNotFoundException
    {
        switch (Server.state)
        {
            case arrivedAtStation:
                if (Server.potentialUsers.userExists(user))
                {
                    System.out.println("Was potential");
                    Server.potentialUsers.removeUser(user);
                    Server.activeUsers.pushUser(user);
                } else
                {
                    if (!Server.activeUsers.userExists(user))
                    {
                        System.out.println("Was new");
                        Server.serverTransmitter.requestUser(user);
                        waitToReply(user);
                    }
                }
                break;
            case leftStation:
                System.out.println("was put to charged");
                if (Server.activeUsers.userExists(user))
                {
                    Server.chargeUserArray.pushUser(user);
                }
        }

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
