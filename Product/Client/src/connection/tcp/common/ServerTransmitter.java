/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection.tcp.common;

import common.interfaces.ProcessorRequest;
import execute.Client;
import static execute.Client.threadPool;
import execute.States;
import helpers.ClientData;
import helpers.LogPrinter;
import java.net.Socket;

/**
 *
 * @author James
 */
public class ServerTransmitter
{
    private final ClientData data;

    public ServerTransmitter(ClientData data)
    {
        this.data = data;
        
    }
    
    public void loginToServer()
    {
        threadPool.schedule(new ProcessorRequest()
        {

            @Override
            public void process()
            {
                try
                {
                    Socket serverSocket = new Socket(data.TCP_SERVER_ADDRESS, data.TCP_SERVER_PORT);

                    TransmissionPacket packet = new TransmissionPacket();
                    packet.command = TransmissionPacket.Commands.USERCONNECTION;
                    packet.dataString = data.ServerTicket + " " + Integer.toString(data.clientID);

                    MessageUtils.sendTransmission(serverSocket, packet);
                    data.clientState = States.SENDING;
                    TransmissionPacket returnPacket = MessageUtils.getTransmission(serverSocket);
                    LogPrinter.print(returnPacket.command + " DS: " + returnPacket.dataString);
                    if (returnPacket.command.equals(TransmissionPacket.Commands.ACKNOWLEDGE))
                    {
                        data.clientState = States.LOGGEDIN;
                    }
                } catch (Exception ex)
                {
                }
            }
        });
    }
}
