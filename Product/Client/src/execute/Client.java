/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package execute;

import common.interfaces.ProcessorRequest;
import connection.tcp.IncomingConnectionsHandler;
import connection.tcp.common.MessageUtils;
import connection.tcp.common.TransmissionPacket;
import connection.udp.UDPConnection;
import connection.udp.UDPListener;
import helpers.ClientData;
import java.net.Socket;
import threading.PersistentExecutorPool;

/**
 *
 * @author JamesFoxes
 */
public class Client
{

    public static PersistentExecutorPool threadPool = new PersistentExecutorPool();
    public States clientState = States.IDLE;

    public ProcessorRequest udpListener;

    public Client()
    {
        try
        {
            IncomingConnectionsHandler incomingTCP = new IncomingConnectionsHandler();
            SimpleProcessorRequest incomingTCPProcess = new SimpleProcessorRequest(incomingTCP);
            udpListener = new UDPListener();
            System.out.println("Hey");
            Client.threadPool.schedule(incomingTCPProcess);
            System.out.println("Hey2");
            Client.threadPool.schedule(udpListener);
            System.out.println("Hey3");
        } catch (Exception ex)
        {
        }
    }

    public void loginToServer()
    {
        int clientID = this.hashCode();
        threadPool.schedule(new ProcessorRequest()
        {

            @Override
            public void process()
            {
                try
                {
                    Socket serverSocket = new Socket(ClientData.TCP_SERVER_ADDRESS, ClientData.TCP_SERVER_PORT);

                    TransmissionPacket packet = new TransmissionPacket();
                    packet.command = TransmissionPacket.Commands.USERCONNECTION;
                    packet.dataString = Integer.toString(0); //PUT CLIENT ID

                    MessageUtils.sendTransmission(serverSocket, packet);
                    clientState = States.SENDING;
                    TransmissionPacket returnPacket = MessageUtils.getTransmission(serverSocket);
                    System.out.println(returnPacket.command + " DS: " + returnPacket.dataString);
                    if (returnPacket.command.equals(TransmissionPacket.Commands.ACKNOWLEDGE))
                    {
                        clientState = States.LOGGEDIN;
                    }
                } catch (Exception ex)
                {
                }
            }
        });
    }
}
