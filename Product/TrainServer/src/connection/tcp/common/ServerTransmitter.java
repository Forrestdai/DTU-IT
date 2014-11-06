/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection.tcp.common;

import common.interfaces.ProcessorRequest;
import static helpers.ServerData.*;
import common.interfaces.ServerExecutable;
import connection.tcp.TransmissionInterpreter;
import execute.Server;
import helpers.LogPrinter;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import execute.SimpleProcessorRequest;
import threading.executiontypes.CyclicalExecutor;
import threading.executiontypes.ExecutableCyclic;
import helpers.User;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author James
 */
public class ServerTransmitter implements ExecutableCyclic
{

    private final ConcurrentLinkedQueue<User> usersToBePushed;
    private final int PUSH_INTERVAL = 4000;

    public ServerTransmitter()
    {
        usersToBePushed = new ConcurrentLinkedQueue();
        initializeCyclicalPushing();
    }

    private Socket initializeAndGetServerSocket()
    {
        Socket toReturn = null;
        try
        {
            toReturn = new Socket(TCP_SERVER_ADDRESS, TCP_SERVER_PORT);
        } catch (IOException ex)
        {
            LogPrinter.printError("Initialize server sending socket", ex);
            ex.printStackTrace();
        }
        return toReturn;
    }

    private void initializeCyclicalPushing()
    {
        ServerExecutable toExecute = new CyclicalExecutor(this, PUSH_INTERVAL);
        SimpleProcessorRequest process = new SimpleProcessorRequest(toExecute);
        Server.serverThreadPool.schedule(process);
    }

    public void requestUser(User user)
    {
        usersToBePushed.add(user);
    }

    @Override
    public void execute()   //executes cyclically
    {
        if (usersToBePushed.size() > 0)
        {
            pushAllUsers();
        }
    }

    private void pushAllUsers()
    {
        ArrayList<User> users = new ArrayList<>();
        int usersToPush = usersToBePushed.size();

        for (int i = 0; i < usersToPush; ++i)
        {
            users.add(usersToBePushed.poll());
        }

        Server.serverThreadPool.schedule(new RequestUsers(users));
    }

    public int getDelay()
    {
        return PUSH_INTERVAL;
    }

    private class RequestUsers implements ProcessorRequest
    {

        Socket sendSocket = initializeAndGetServerSocket();
        ArrayList<User> users;

        public RequestUsers(ArrayList<User> users)
        {
            this.users = users;
            for (User user : users)
            {
                System.out.println("USER: " + user);
            }
        }

        @Override
        public void process()
        {
            TransmissionPacket getUsersPacket = new TransmissionPacket();
            getUsersPacket.command = TransmissionPacket.Commands.GETUSERS;
            getUsersPacket.dataObject = users;
            getUsersPacket.dataString = Integer.toString(users.size());
            try
            {
                MessageUtils.sendTransmission(sendSocket, getUsersPacket);
                TransmissionPacket returnUsers = MessageUtils.getTransmission(sendSocket);
                new TransmissionInterpreter(returnUsers, sendSocket);
                //sendSocket.close();
            } catch (IOException | ClassNotFoundException ex)
            {
                usersToBePushed.addAll(users); //rollback
                reOpenConnection(sendSocket);
                LogPrinter.print("Users have been re-added to send queue.");
                ex.printStackTrace();
            }
        }

    }

    private void reOpenConnection(Socket toReopen)
    {
        try
        {
            toReopen.close();
            toReopen = initializeAndGetServerSocket();
        } catch (IOException ex)
        {
        }
    }

    public Socket getTestingSocket()
    {
        return initializeAndGetServerSocket();
    }

    public int getUsersInArray()
    {
        return usersToBePushed.size();
    }
}
