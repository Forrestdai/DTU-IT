/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection.tcp.commands;

import execute.Server;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import connection.tcp.common.MessageUtils;
import connection.tcp.common.TransmissionPacket;
import helpers.User;

/**
 *
 * @author JamesFoxes
 */
public class ReturnedUsersCommand implements Command
{

    private TransmissionPacket reply;

    public ReturnedUsersCommand()
    {
        this.reply = new TransmissionPacket();
    }

    @Override
    public void execute(Socket clientConnection, TransmissionPacket incomingPacket) throws IOException
    {
        try
        {
            ArrayList<User> users = (ArrayList<User>) incomingPacket.dataObject;
            for (User user : users)
            {
                Server.potentialUsers.pushUser(user);
            }

            reply.command = TransmissionPacket.Commands.ACKNOWLEDGE;

            
        } catch (Exception e)
        {
            reply.command = TransmissionPacket.Commands.nil;
        } finally
        {
            MessageUtils.sendTransmission(clientConnection, reply);
        }

    }

}
