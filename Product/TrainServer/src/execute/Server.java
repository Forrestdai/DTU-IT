/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package execute;

import java.io.IOException;
import processing.ServerProcessorRequest;
import threading.PersistentExecutorPool;
import threading.ThreadPerRequestScheduler;
import transmission.IncomingUserConnectionsHandler;

/**
 *
 * @author JamesFoxes
 */
public class Server
{
    private static ThreadPerRequestScheduler serverThreadPool = new ThreadPerRequestScheduler();
    public static PersistentExecutorPool threadPool = new PersistentExecutorPool();
    
    public static void main(String[] args)
    {
        try
        {
            IncomingUserConnectionsHandler incomingTCP = new IncomingUserConnectionsHandler();
            ServerProcessorRequest incomingTCPProcess = new ServerProcessorRequest(incomingTCP);
            Server.serverThreadPool.schedule(incomingTCPProcess);
        } catch (IOException ex)
        {
        }
    }

    
}
