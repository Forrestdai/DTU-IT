/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection.udp;

import execute.Client;
import execute.ProcessorRequest;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

/**
 *
 * @author JamesFoxes
 */
public class UDPListener implements ProcessorRequest
{

    private MulticastSocket socketUDP;
    private String message;
    private String[] splitMessage;

    public UDPListener()
    {
        try
        {
            this.socketUDP = Client.udpconn.getSocket();
        } catch (Exception ex)
        {
        }
    }
    
    @Override
    public void process()
    {
        try
        {
            byte[] messageBuffer = new byte[256];
            DatagramPacket packet = new DatagramPacket(messageBuffer, messageBuffer.length);
            socketUDP.receive(packet);
            message = new String(packet.getData(), 0, packet.getLength());
            ReadServerData serverData = new ReadServerData(message);
            System.out.println(serverData.address + serverData.ticketCode + serverData.port);
            
        } catch (IOException ex)
        {
        }
    }
}
