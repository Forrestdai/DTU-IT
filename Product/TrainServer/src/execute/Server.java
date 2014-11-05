/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package execute;

import connection.tcp.IncomingConnectionsHandler;
import connection.tcp.common.ServerTransmitter;
import connection.udp.ClientConnectionCode;
import helpers.ActiveUsers;
import helpers.PotentialUsers;
import helpers.ServerState;
import helpers.UserArray;
import threading.PersistentExecutorPool;
import threading.ThreadPerRequestScheduler;

/**
 *
 * @author JamesFoxes
 */
public class Server
{

    public static ThreadPerRequestScheduler serverThreadPool = new ThreadPerRequestScheduler();
    public static PersistentExecutorPool threadPool = new PersistentExecutorPool();

    public static UserArray potentialUsers;
    public static UserArray activeUsers;

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
        potentialUsers = new PotentialUsers();
        activeUsers = new ActiveUsers();
        serverTransmitter = new ServerTransmitter();
    }

}
