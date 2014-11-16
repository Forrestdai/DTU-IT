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
import helpers.LogPrinter;
import helpers.User;
import java.util.NoSuchElementException;

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
    }

    @Override
    public void execute(Socket clientConnection, TransmissionPacket incomingPacket) throws IOException, ClassNotFoundException
    {
        int userID = checkConnectionValidityReturnUserID(incomingPacket);
        user = new User(userID);
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
                if (isUserPotential())
                {
                    user = Server.potentialUsers.getUserByID(user.ID);
                    moveToActiveArray();
                }
                if (isUserActive())
                {
                    user = Server.activeUsers.getUserByID(user.ID);
                    updateUserTravelCost(); //update end position.
                } else
                {
                    addToPotentialArray();
                }
                break;
            case leftStation:
                Server.noChargeUserArray.pushUser(user);
                break;
        }
    }

    private boolean isUserPotential()
    {
        return !Server.activeUsers.userExists(user) && Server.potentialUsers.userExists(user);
    }

    private boolean isUserActive()
    {
        return Server.activeUsers.userExists(user);
    }

    private void moveToActiveArray()
    {
        LogPrinter.print("Was potential");
        updateUserTravelCost();

        Server.potentialUsers.removeUser(user);
        Server.activeUsers.pushUser(user);
    }

    private void updateUserTravelCost()
    {
        user.endLocation = Server.state.currentStop;
        int newCost = calculateUserCharge();
        if (newCost > user.cost)
        {
            user.cost = newCost;
        }
    }

    private void addToPotentialArray() //New User
    {
        LogPrinter.print("Was new");
        Server.serverTransmitter.requestUser(user);
        waitToReply();
        Server.potentialUsers.setUserStartStop(user.ID, Server.state.currentStop);
    }

    private int calculateUserCharge()
    {
        Journey shortestPath = new Journey(user.startLocation, user.endLocation);

        return shortestPath.getCost();
    }

    private int checkConnectionValidityReturnUserID(TransmissionPacket incomingPacket) throws NoSuchElementException
    {
        String[] message = incomingPacket.dataString.split("\\s+"); //split on space regex

        if (Integer.parseInt(message[0]) == Server.UDPCode.code)
        {
            return Integer.parseInt(message[1]);
        }
        throw new NoSuchElementException("Wrong user Ticket code");
    }
}
