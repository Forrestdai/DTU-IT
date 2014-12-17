/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection.udp;

import execute.Server;
import helpers.LogPrinter;
import helpers.ServerData;
import helpers.ServerState;
import helpers.User;
import helpers.UserArray;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
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
            System.out.println("Sender");
            DatagramSocket socket = null;
            DatagramPacket outPacket = null;
            byte[] outBuffer;
            final int PORT = 8888;

            socket = new DatagramSocket();
            long counter = 0;
            String msg;

            outBuffer = Server.UDPCode.toString().getBytes();

            //Send to multicast IP address and port
            InetAddress address = InetAddress.getByName("224.2.2.3");
            outPacket = new DatagramPacket(outBuffer, outBuffer.length, address, PORT);

            socket.send(outPacket);

            try
            {
                Thread.sleep(500);
            } catch (InterruptedException ie)
            {
            }

            if (Server.state.currentState.equals(ServerState.State.leftStation))
            {
                Thread.sleep(5000);
                findUsersToCharge();
            }

        } catch (Exception ex)
        {
            LogPrinter.printError("ERR: UDP send error", ex);
        }
    }

    private void findUsersToCharge()
    {
        for (Entry<Integer, User> userEntry : Server.activeUsers.getUserEntrySet())
        {
            User user = userEntry.getValue();
            if (!Server.noChargeUserArray.userExists(user))
            {
                Server.serverTransmitter.chargeUser(user, user.cost);
                Server.activeUsers.removeUser(user);
            }
        }
        Server.noChargeUserArray.clear();
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
