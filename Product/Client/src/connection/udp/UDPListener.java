/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection.udp;

import execute.ProcessorRequest;
import helpers.ClientData;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JamesFoxes
 */
public class UDPListener implements ProcessorRequest
{

    private DatagramSocket socketUDP;
    private String message;
    private String[] splitMessage;

    public UDPListener()
    {
        try
        {
            this.socketUDP = new DatagramSocket(ClientData.UDP_SERVER_PORT);
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
            splitIncomingMessage();
        } catch (IOException ex)
        {
            Logger.getLogger(UDPListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void splitIncomingMessage()
    {
        splitMessage = message.split("\\s+"); //split on space
    }
    
    public String[] getReturnMessage()
    {
        return splitMessage;
    }

    
}
