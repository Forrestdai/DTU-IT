/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transmission;

import static common.ServerData.*;
import common.interfaces.ServerMainExecutable;
import execute.Server;
import helpers.LogPrinter;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import processing.ServerProcessorRequest;
import transmission.common.MessageUtils;
import transmission.common.TransmissionPacket;
import users.CyclicalExecutor;
import users.ExecutableCyclic;
import users.User;

/**
 *
 * @author James
 */
public class TransmitToServer implements ExecutableCyclic
{

    private final ConcurrentLinkedQueue<User> usersToBePushed;
    private final int PUSH_INTERVAL = 2000;
    Socket serverSocket;

    public TransmitToServer()
    {
        usersToBePushed = new ConcurrentLinkedQueue();
        initializeServerSocket();
        initializeCyclicalPushing();
    }

    private void initializeServerSocket()
    {
        try
        {
            serverSocket = new Socket(TCP_SERVER_ADDRESS, TCP_SERVER_PORT);
        } catch (IOException ex)
        {
            LogPrinter.printError("ERR: Initialize server sending socket.", ex);
        }
    }

    private void initializeCyclicalPushing()
    {
        ServerMainExecutable toExecute = new CyclicalExecutor(this, PUSH_INTERVAL);
        ServerProcessorRequest process = new ServerProcessorRequest(toExecute);
        Server.serverThreadPool.schedule(process);
    }

    public void requestUser(User user)
    {
        usersToBePushed.add(user);
    }

    @Override
    public void execute()
    {
        pushAll();
    }

    private void pushAll()
    {
        TransmissionPacket getUsersPacket = new TransmissionPacket();
        getUsersPacket.command = TransmissionPacket.Commands.GETUSERS;

        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < users.size(); ++i)
        {
            users.add(usersToBePushed.poll());
        }
        getUsersPacket.dataObject = users.toArray();
        getUsersPacket.dataString = Integer.toString(users.size());

        try
        {
            MessageUtils.sendTransmission(serverSocket, null);
        } catch (IOException ex)
        {
            LogPrinter.printError("ERR: could not send to server.", ex);
            usersToBePushed.addAll(users);
            LogPrinter.print("Users have been re-added to send queue.");
        }
    }
}
