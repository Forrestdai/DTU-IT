/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection.tcp.commands;

import connection.tcp.common.MessageUtils;
import connection.tcp.common.TransmissionPacket;
import helpers.LogPrinter;
import helpers.User;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JamesFoxes
 */
public class GetUser implements Command
{

    private ArrayList<User> inputArray;
    private ArrayList<User> returnUserArray;
    private int attempts = 0;

    @Override
    public void execute(Socket clientConnection, TransmissionPacket incomingPacket) throws IOException
    {
        try
        {
            do
            {
                Thread.sleep(attempts*100);
                ++attempts;
                try
                {
                    readInputUsers(incomingPacket);
                } catch (Exception ex)
                {
                    sendErrorMessage();
                    return;
                }

                getUsersFromDB();

                TransmissionPacket reply = new TransmissionPacket();
                reply.command = TransmissionPacket.Commands.RETURNUSERS;
                reply.dataObject = returnUserArray;
                reply.dataString = "returned Array: " + returnUserArray.size();
                MessageUtils.sendTransmission(clientConnection, reply);
            } while (((TransmissionPacket)MessageUtils.getTransmission(clientConnection)).command.equals(TransmissionPacket.Commands.nil) && attempts < 5);
        } catch (ClassNotFoundException | InterruptedException ex)
        {
        }
    }

    private void readInputUsers(TransmissionPacket incomingPacket) throws Exception
    {
        try
        {
            inputArray = (ArrayList<User>) incomingPacket.dataObject;
        } catch (ClassCastException e)
        {
            throw new Exception("Could not cast arraylist from trainserver.");
        }
    }

    private void sendErrorMessage()
    {
        TransmissionPacket reply = new TransmissionPacket();
        reply.command = TransmissionPacket.Commands.nil;
        reply.dataString = "Could not cast";
    }

    private void getUsersFromDB()
    {
        returnUserArray = new ArrayList<>();
        for (User user : inputArray)
        {
            //GET USER FUNCTION
            User toSend = new User();
            toSend.ID = user.ID;
            LogPrinter.print("Recieved: " + user.ID);
            returnUserArray.add(toSend);
        }
    }

}
