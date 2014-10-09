/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transmission;

import static common.ServerData.*;
import helpers.LogPrinter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;
import transmission.common.MessageUtils;
import transmission.common.TransmissionPacket;

/**
 *
 * @author JamesFoxes
 */
public abstract class TestingClients implements Runnable
{

    protected Socket socket;

    public TestingClients() throws IOException
    {
        socket = new Socket(TCP_LOCAL_ADDRESS, TCP_PORT);
    }

    public String connectSendRecieveTest(int i) throws IOException, ClassNotFoundException
    {
        System.out.printf("Client %2d: connecting\n", i);

        System.out.printf("Client %2d: sending message\n", i);
        TransmissionPacket tp = new TransmissionPacket();
        tp.dataString = Integer.toString(i);
        MessageUtils.sendTransmission(socket, tp);

        System.out.printf("Client %2d: getting reply\n", i);
        String returnMessage = MessageUtils.getTransmission(socket).dataString;

        System.out.printf("Client %2d: finished\n.", i);
        socket.close();

        return returnMessage;
    }
}

class TransmissionClient extends TestingClients
{

    private TransmissionPacket sendMessage;
    private TransmissionPacket returnMessage;

    public TransmissionClient(TransmissionPacket sendMessage) throws IOException
    {
        this.sendMessage = sendMessage;
        this.returnMessage = new TransmissionPacket();
    }

    @Override
    public void run()
    {
        try
        {
            MessageUtils.sendTransmission(socket, sendMessage);
            returnMessage = MessageUtils.getTransmission(socket);

        } catch (IOException ex)
        {
            LogPrinter.printError("ERR: unable to send message from StringClient", ex);
        } catch (ClassNotFoundException ex)
        {
            LogPrinter.printError("ERR: unable to cast transmission object from StringClient", ex);
        } finally
        {
            try
            {
                socket.close();
            } catch (IOException ex)
            {
            }
        }
    }

    public TransmissionPacket getReturnedMessage()
    {
        return returnMessage;
    }
}

class CountingClient extends TestingClients
{

    int clientNumber;
    AtomicInteger toAdd;

    CountingClient(int clientNumber, AtomicInteger toAdd) throws IOException
    {
        this.toAdd = toAdd;
        this.clientNumber = clientNumber;
    }

    @Override
    public void run()
    {
        String returnedMessage = null;
        try
        {
            returnedMessage = connectSendRecieveTest(clientNumber);
        } catch (IOException e)
        {
            LogPrinter.printError("ERR: unable to transmit from CountingClient", e);
        } catch (ClassNotFoundException ex)
        {
            LogPrinter.printError("ERR: unable to cast transmission object from CountingClient", ex);
        } finally
        {
            if (returnedMessage != null && Integer.parseInt(returnedMessage) == clientNumber)
            {
                toAdd.incrementAndGet();
            }
        }
    }
}

class SimpleClient extends TestingClients
{

    int clientNumber;

    SimpleClient(int clientNumber) throws IOException
    {
        this.clientNumber = clientNumber;
    }

    @Override
    public void run()
    {
        String returnedMessage = null;
        try
        {
            returnedMessage = connectSendRecieveTest(clientNumber);
        } catch (IOException | ClassNotFoundException e)
        {
            LogPrinter.printError("ERR: unable to cast transmission object from SimpleClient", e);
        }
    }
}

class UDPClient implements Runnable
{

    MulticastSocket socketUDP;
    String message;

    UDPClient(MulticastSocket socketUDP)
    {
        this.socketUDP = socketUDP;
    }

    @Override
    public void run()
    {
        try
        {
            byte[] messageBuffer = new byte[256];
            DatagramPacket packet = new DatagramPacket(messageBuffer, messageBuffer.length);
            socketUDP.receive(packet);
            message = new String(packet.getData(), 0, packet.getLength());
        } catch (IOException e)
        {
            LogPrinter.printError("ERR: testing UDP failed to join group.", e);
            e.printStackTrace();
        }
    }

    public String getReturnedMessage()
    {
        if (message != null)
        {
            return message;
        } else
        {
            return new String();
        }
    }
}
