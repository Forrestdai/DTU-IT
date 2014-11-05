/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection.udp;

import helpers.ClientData;
import java.net.DatagramSocket;
import java.net.MulticastSocket;

/**
 *
 * @author JamesFoxes
 */
public class UDPConnection
{
    private MulticastSocket socketUDP;

    public UDPConnection()
    {
        try
        {
            this.socketUDP = new MulticastSocket(ClientData.UDP_SERVER_PORT);
        } catch (Exception ex)
        {
        }
    }
    
    public MulticastSocket getSocket()
    {
        return socketUDP;
    }
    
    
}
