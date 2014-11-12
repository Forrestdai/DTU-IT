/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection.tcp.commands;

import connection.tcp.common.TransmissionPacket;
import execute.Server;
import helpers.User;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author JamesFoxes
 */
public class ChargeUser implements Command
{

    @Override
    public void execute(Socket clientConnection, TransmissionPacket incomingPacket) throws IOException
    {
        TransmissionPacket reply = new TransmissionPacket();
        try
        {
            Map<User, Double> userCharges = castUserObject(incomingPacket.dataObject);
            for (Entry<User, Double> chargeSet : userCharges.entrySet())
            {
                Server.database.chargeUser(chargeSet.getKey(), chargeSet.getValue());
            }
            
        } catch (Exception ex)
        {
            reply.command = TransmissionPacket.Commands.nil;
        }
    }

    private Map<User, Double> castUserObject(Object toCast) throws Exception
    {
        Map<User, Double> users;
        try
        {
            users = (Map<User, Double>) toCast;
        } catch (ClassCastException e)
        {
            users = new HashMap<>();
            throw new Exception("Could not cast charge user map");
        }
        return users;
    }
}
