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
import helpers.LogPrinter;
import helpers.User;

/**
 *
 * @author JamesFoxes
 */
public class ReturnedUsersCommand implements Command
{

    ArrayList<User> users;

    @Override
    public void execute(Socket clientConnection, TransmissionPacket incomingPacket) throws IOException
    {
        TransmissionPacket reply = new TransmissionPacket();
        try
        {
            castUserArray(incomingPacket);
            
            for (User user : users)
            {
                LogPrinter.print("Recieved User: " + user.ID);
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
    
    private void castUserArray(TransmissionPacket incomingPacket) throws Exception
    {
        try
        {
            users = (ArrayList<User>) incomingPacket.dataObject;
        } catch (ClassCastException e)
        {
            throw new Exception("Could not cast user array.");
        }
        
    }

}
