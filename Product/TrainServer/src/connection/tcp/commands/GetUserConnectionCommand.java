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
import helpers.Journey;
import helpers.ServerData;
import helpers.User;
import java.util.ArrayList;
import java.util.Stack;
import trafficrouting.TransportNode;

/**
 *
 * @author JamesFoxes
 */
public class GetUserConnectionCommand implements Command
{

    private TransmissionPacket reply;
    private User user;

    public GetUserConnectionCommand()
    {
        reply = new TransmissionPacket();
        user = new User();
    }

    @Override
    public void execute(Socket clientConnection, TransmissionPacket incomingPacket) throws IOException, ClassNotFoundException
    {
        int userID = Integer.parseInt(incomingPacket.dataString); //checkConnectionValidityReturnUserID(incomingPacket);
        if (userID != -1)
        {
            user.ID = userID;

            compareUserWithArrays();

            reply.command = TransmissionPacket.Commands.ACKNOWLEDGE;
            reply.dataString = incomingPacket.dataString;
        } else
        {
            reply.command = TransmissionPacket.Commands.nil;
            reply.dataString = "illegal registration";
        }
        MessageUtils.sendTransmission(clientConnection, reply);
    }

    private void waitToReply()
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

    private void compareUserWithArrays() throws IOException, ClassNotFoundException
    {
        switch (Server.state.currentState)
        {
            case arrivedAtStation:
                if (userIsPotential())
                {
                    moveToActiveArray();
                } else
                {
                    addToPotentialArray();
                }
                break;
            case leftStation:
                System.out.println("was put to charged");
                if (userIsActive())
                {
                    user.endLocation = Server.state.currentStop;
                    Server.serverTransmitter.chargeUser(user, calculateUserCharge());
                    Server.activeUsers.removeUser(user);
                }
                break;
        }
    }

    private boolean userIsPotential()
    {
        return !Server.activeUsers.userExists(user) && Server.potentialUsers.userExists(user);
    }
    
    private boolean userIsActive()
    {
        return Server.activeUsers.userExists(user);
    }

    private void moveToActiveArray()
    {
        System.out.println("Was potential");
        Server.potentialUsers.removeUser(user);
        Server.activeUsers.pushUser(user);
    }

    private void addToPotentialArray()
    {
        System.out.println("Was new");
        user.startLocation = Server.state.currentStop;
        Server.serverTransmitter.requestUser(user);
        waitToReply();
    }

    private double calculateUserCharge()
    {
        Journey shortestPath = new Journey(user.startLocation, user.endLocation);

        return shortestPath.getCost();
    }

    private int checkConnectionValidityReturnUserID(TransmissionPacket incomingPacket)
    {
        try
        {
            if (Server.state.currentState == ServerState.State.arrivedAtStation)
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
