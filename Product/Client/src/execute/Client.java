/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package execute;

import connection.tcp.IncomingConnectionsHandler;
import connection.tcp.common.MessageUtils;
import connection.tcp.common.TransmissionPacket;
import connection.udp.UDPConnection;
import helpers.ClientData;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import threading.PersistentExecutorPool;

/**
 *
 * @author JamesFoxes
 */
public class Client
{

    public static PersistentExecutorPool threadPool = new PersistentExecutorPool();
    public static final UDPConnection udpconn = new UDPConnection();
    public States clientState = States.IDLE;

    public Client()
    {
        try
        {
            IncomingConnectionsHandler incomingTCP = new IncomingConnectionsHandler();
            SimpleProcessorRequest incomingTCPProcess = new SimpleProcessorRequest(incomingTCP);
            Client.threadPool.schedule(incomingTCPProcess);
        } catch (Exception ex)
        {
        }
    }

    public void loginToServer()
    {
        try
        {
            Socket serverSocket = new Socket(ClientData.TCP_SERVER_ADDRESS, ClientData.TCP_SERVER_PORT);

            TransmissionPacket packet = new TransmissionPacket();
            packet.command = TransmissionPacket.Commands.USERCONNECTION;
            packet.dataString = "123";

            MessageUtils.sendTransmission(serverSocket, packet);
            TransmissionPacket returnPacket = MessageUtils.getTransmission(serverSocket);
            System.out.println(returnPacket.command + " DS: " + returnPacket.dataString);
        } catch (Exception ex)
        {
        }
    }
}
