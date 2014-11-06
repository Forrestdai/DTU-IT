/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection.udp;

import common.interfaces.ProcessorRequest;
import execute.Client;
import helpers.ClientData;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketAddress;
import java.util.Enumeration;

/**
 *
 * @author JamesFoxes
 */
public class UDPListener implements ProcessorRequest
{

    private MulticastSocket socketUDP;
    private SocketAddress castAddress;
    private NetworkInterface networkInterface;
    private String message;
    private String[] splitMessage;

    public UDPListener()
    {
        try
        {
            socketUDP = new MulticastSocket(ClientData.UDP_SERVER_PORT);
            castAddress = new InetSocketAddress(ClientData.UDP_ADDRESS, ClientData.UDP_SERVER_PORT);
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

            while (interfaces.hasMoreElements())
            {
                NetworkInterface next = interfaces.nextElement();
                if (next.supportsMulticast())
                {
                    System.out.println("Network Interface: " + next.getName() + " full: " + next.getDisplayName());
                }
            }
            networkInterface = NetworkInterface.getByName("eth1");

            socketUDP.joinGroup(castAddress, networkInterface);
        } catch (Exception ex)
        {
        }
    }
    
    @Override
    public void process()
    {
        System.out.println("hey " + socketUDP.toString());
        try
        {
            byte[] messageBuffer = new byte[256];
            DatagramPacket packet = new DatagramPacket(messageBuffer, messageBuffer.length);
            System.out.println("Listens");
            socketUDP.receive(packet);
            System.out.println("Recieved");
            
            socketUDP.leaveGroup(castAddress, networkInterface);
            socketUDP.close();
            
            System.err.println("HELLO");
            message = new String(packet.getData(), 0, packet.getLength());
            ReadServerData serverData = new ReadServerData(message);
            System.out.println(serverData.address + serverData.ticketCode + serverData.port);
            
        } catch (IOException ex)
        {
        }
    }
}
