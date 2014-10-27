/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package execute;

import helpers.ServerState;
import threading.PersistentExecutorPool;
import threading.ThreadPerRequestScheduler;
import connection.udp.ClientConnectionCode;
import connection.tcp.IncomingConnectionsHandler;
import connection.tcp.common.ServerTransmitter;
import helpers.ActiveUsersArray;
import helpers.PotentialUsersArray;

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

    public static ServerTransmitter serverTransmitter;
    
    public static ClientConnectionCode UDPCode;
    
    public static ServerState state;

    public static void main(String[] args)
    {
        try
        {
            IncomingConnectionsHandler incomingTCP = new IncomingConnectionsHandler();
            SimpleProcessorRequest incomingTCPProcess = new SimpleProcessorRequest(incomingTCP);
            Server.serverThreadPool.schedule(incomingTCPProcess);
        } catch (Exception ex)
        {
        }
    }

    public static void initialize()
    {
        potentialUsers = new PotentialUsersArray();
        activeUsers = new ActiveUsersArray();
        serverTransmitter = new ServerTransmitter();
    }

}
