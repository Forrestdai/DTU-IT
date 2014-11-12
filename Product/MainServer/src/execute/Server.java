/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package execute;

import connection.tcp.IncomingConnectionsHandler;
import database.DatabaseHandler;
import threading.PersistentExecutorPool;
import trafficrouting.SetupGraph;

/**
 *
 * @author James
 */
public class Server
{

    public static PersistentExecutorPool threadPool = new PersistentExecutorPool();
    public static DatabaseHandler database = new DatabaseHandler();
    public static SetupGraph trafficGraph = new SetupGraph();
    
    public static void main(String[] args)
    {
         try
        {
            IncomingConnectionsHandler incomingTCP = new IncomingConnectionsHandler();
            SimpleProcessorRequest incomingTCPProcess = new SimpleProcessorRequest(incomingTCP);
            Server.threadPool.schedule(incomingTCPProcess);
        } catch (Exception ex)
        {
        }
    }
    
}
