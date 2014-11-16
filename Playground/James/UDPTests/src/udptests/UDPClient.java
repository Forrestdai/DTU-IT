/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udptests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 *
 * @author James
 */
public class UDPClient
{

    public static void main(String args[]) throws Exception
    {
        multiCast();
    }

    private static void uniCast() throws IOException
    {
        System.out.println("Client");
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("localhost");
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        String sentence = inFromUser.readLine();
        sendData = sentence.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
        clientSocket.send(sendPacket);
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        String modifiedSentence = new String(receivePacket.getData());
        System.out.println("FROM SERVER:" + modifiedSentence);
        clientSocket.close();
    }

    private static void multiCast() throws IOException
    {
        System.out.println("Reciever");
        MulticastSocket socket = null;
        DatagramPacket inPacket = null;
        byte[] inBuf = new byte[256];

        //Prepare to join multicast group
        socket = new MulticastSocket(8888);
        InetAddress address = InetAddress.getByName("224.2.2.3");
        socket.joinGroup(address);

        while (true)
        {
            inPacket = new DatagramPacket(inBuf, inBuf.length);
            socket.receive(inPacket);
            String msg = new String(inBuf, 0, inPacket.getLength());
            System.out.println("From " + inPacket.getAddress() + " Msg : " + msg);
        }
    }
}
