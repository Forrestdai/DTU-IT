/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transmission;

import common.ServerData;
import helpers.LogPrinter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import users.ExecuteOnImpulse;

/**
 *
 * @author JamesFoxes
 */
public class BroadcastUDP implements ExecuteOnImpulse
{

    private DatagramSocket socket;
    private DatagramPacket packet;
    private byte[] messageBuffer;
    private InetAddress transmitUDPAddess;

    public BroadcastUDP()
    {
        try
        {
            messageBuffer = new byte[256];
            transmitUDPAddess = InetAddress.getByName(ServerData.TCP_LOCAL_ADDRESS);

        } catch (IOException e)
        {
            LogPrinter.printError("ERR: UDP host error");
            e.printStackTrace();
        }
    }

    @Override
    public void execute()
    {
        try
        {
            socket = new DatagramSocket(ServerData.UDP_SERVER_PORT);
            socket.setBroadcast(true);
            for (int i = 0; i < 500; i++)
            {
                messageBuffer = ("HELO" + ServerData.TCP_LOCAL_ADDRESS + ServerData.TCP_PORT).getBytes();
                packet = new DatagramPacket(messageBuffer, messageBuffer.length, transmitUDPAddess, ServerData.UDP_SERVER_PORT + 1);
                socket.send(packet);
                Thread.sleep(10);
            }
            socket.close();
        } catch (Exception ex)
        {
            LogPrinter.printError("ERR: UDP send error");
        }
    }
}
