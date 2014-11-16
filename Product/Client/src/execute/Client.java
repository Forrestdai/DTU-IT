/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package execute;

import common.interfaces.ProcessorRequest;
import connection.tcp.common.ServerTransmitter;
import connection.udp.UDPListener;
import helpers.ClientData;
import helpers.LogPrinter;
import threading.PersistentExecutorPool;

/**
 *
 * @author JamesFoxes
 */
public class Client
{

    public static PersistentExecutorPool threadPool = new PersistentExecutorPool();
    public ClientData data = new ClientData();

    public ProcessorRequest udpListener;

    public Client()
    {
        try
        {
            data.clientID = this.hashCode();
            udpListener = new UDPListener(data);
            Client.threadPool.schedule(udpListener);
        } catch (Exception ex)
        {
            LogPrinter.printError("Start Client error", ex);
        }
    }
    
    public ClientData getData()
    {
        return data;
    }
    
    public void setState(States state)
    {
        data.clientState = state;
    }
}
