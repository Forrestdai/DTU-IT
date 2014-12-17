/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection.udp;

import common.interfaces.ProcessorRequest;
import connection.tcp.common.ServerTransmitter;
import execute.Client;
import execute.States;
import helpers.ClientData;
import helpers.LogPrinter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import threading.PersistentExecutorPool;

/**
 *
 * @author JamesFoxes
 */
public class UDPListener implements ProcessorRequest
{

    private MulticastSocket socketUDP;
    private InetAddress castAddress;
    private DatagramPacket incomingMessage;
    private ClientData data;
    private PersistentExecutorPool threadPool;

    private ServerTransmitter serverTransmitter;

    public UDPListener(ClientData data, PersistentExecutorPool threadPool)
    {
        this.data = data;
        this.threadPool = threadPool;
    }

    @Override
    public void process()
    {
        while (data.clientState != States.GONE)
        {
            try
            {
                System.out.println("Reciever");

                socketUDP = new MulticastSocket(data.UDP_SERVER_PORT);
                castAddress = InetAddress.getByName(data.UDP_ADDRESS);
                socketUDP.joinGroup(castAddress);

                byte[] inputBuffer = new byte[256];
                incomingMessage = new DatagramPacket(inputBuffer, inputBuffer.length);
                socketUDP.receive(incomingMessage);

                String message = new String(inputBuffer, 0, incomingMessage.getLength());

                data.TCP_SERVER_ADDRESS = incomingMessage.getAddress().toString().substring(1);
                data.ServerTicket = message;
                LogPrinter.print("Recieved code: " + message);

                serverTransmitter = new ServerTransmitter(data, threadPool);
                serverTransmitter.loginToServer();

                /*System.err.println("HELLO");
                 message = new String(packet.getData(), 0, packet.getLength());
                 ReadServerData serverData = new ReadServerData(message);
                 LogPrinter.print(serverData.address + serverData.ticketCode + serverData.port);*/
            } catch (IOException ex)
            {
            }
        }
    }
}
