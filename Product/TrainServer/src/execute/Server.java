/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package execute;

import helpers.State;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import processing.ServerProcessorRequest;
import threading.PersistentExecutorPool;
import threading.ThreadPerRequestScheduler;
import transmission.ClientConnectionCode;
import transmission.IncomingConnectionsHandler;
import transmission.TransmitToServer;
import users.ActiveUsersArray;
import users.PotentialUsersArray;

/**
 *
 * @author JamesFoxes
 */
public class Server
{

    public static ThreadPerRequestScheduler serverThreadPool = new ThreadPerRequestScheduler();
    public static PersistentExecutorPool threadPool = new PersistentExecutorPool();

    public static PotentialUsersArray potentialUsers;
    public static ActiveUsersArray activeUsers;

    public static TransmitToServer serverTransmitter;
    
    public static ClientConnectionCode UDPCode;
    
    public static State state;

    public static void main(String[] args)
    {
        try
        {
            IncomingConnectionsHandler incomingTCP = new IncomingConnectionsHandler();
            ServerProcessorRequest incomingTCPProcess = new ServerProcessorRequest(incomingTCP);
            Server.serverThreadPool.schedule(incomingTCPProcess);
        } catch (Exception ex)
        {
        }
    }

    public static void initialize()
    {
        potentialUsers = new PotentialUsersArray();
        activeUsers = new ActiveUsersArray();
        serverTransmitter = new TransmitToServer();
    }

}
