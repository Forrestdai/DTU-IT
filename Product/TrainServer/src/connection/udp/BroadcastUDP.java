/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection.udp;

import execute.Server;
import helpers.LogPrinter;
import helpers.ServerData;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import threading.executiontypes.ExecuteOnImpulse;

/**
 *
 * @author JamesFoxes
 */
public class BroadcastUDP implements ExecuteOnImpulse
{
    private MulticastSocket socket;
    //private InetAddress castAddress;
    
    private DatagramPacket packet;
    private byte[] messageBuffer;
    
    public BroadcastUDP()
    {
        //try
       // {
            messageBuffer = new byte[256];
            //castAddress = InetAddress.getByName(ServerData.UDP_ADDRESS);

       // } catch (IOException e)
       // {
        //    LogPrinter.printError("ERR: UDP host error", e);
        //    e.printStackTrace();
       // }
    }

    @Override
    public void execute()
    {
        Server.UDPCode = new ClientConnectionCode();
        try
        {
            socket = new MulticastSocket(ServerData.UDP_SERVER_PORT);
            //socket.joinGroup(castAddress);
            socket.setBroadcast(true);
            
            send500Packets();
            
           // socket.leaveGroup(castAddress);
            socket.close();
            System.out.println("SENT");
        } catch (Exception ex)
        {
            LogPrinter.printError("ERR: UDP send error", ex);
        }
    }

    private void send500Packets() throws Exception
    {
        //for (int i = 0; i < 500; i++)
        //{
            messageBuffer = (ServerData.TCP_LOCAL_ADDRESS + " " + ServerData.TCP_PORT + " " + Server.UDPCode.code).getBytes();
            packet = new DatagramPacket(messageBuffer, messageBuffer.length, InetAddress.getByName(ServerData.UDP_ADDRESS), ServerData.UDP_SERVER_PORT + 1);
            socket.send(packet);
            Thread.sleep(10);
        //}
    }
}
