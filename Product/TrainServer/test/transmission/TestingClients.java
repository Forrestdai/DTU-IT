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
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import transmission.common.MessageUtils;
import transmission.common.TransmissionPacket;

/**
 *
 * @author JamesFoxes
 */
public abstract class TestingClients implements Callable
{

    Socket socket;
    Thread clientThread;

    public TestingClients() throws IOException
    {
        socket = new Socket(TCP_LOCAL_ADDRESS, TCP_PORT);
    }
    
    public String connectSendRecieveTest(int i) throws IOException, ClassNotFoundException
    {
        LogPrinter.printTest("Client " + i + ": connecting");

        LogPrinter.printTest("Client " + i + ": sending message");
        TransmissionPacket tp = new TransmissionPacket();
        tp.dataString = Integer.toString(i);
        MessageUtils.sendTransmission(socket, tp);

        LogPrinter.printTest("Client " + i + ": getting reply");
        String returnMessage = MessageUtils.getTransmission(socket).dataString;

        LogPrinter.printTest("Client " + i + ": finished.");
        socket.close();

        return returnMessage;
    }
}

class SendAndReturnPacketClient extends TestingClients
{

    private TransmissionPacket sendMessage;
    private TransmissionPacket returnMessage;

    SendAndReturnPacketClient(TransmissionPacket sendMessage) throws IOException
    {
        this.sendMessage = sendMessage;
        this.returnMessage = new TransmissionPacket();
    }

    @Override
    public Object call() throws Exception
    {
        try
        {
            MessageUtils.sendTransmission(socket, sendMessage);
            returnMessage = MessageUtils.getTransmission(socket);

        } catch (IOException ex)
        {
            LogPrinter.printTestError("ERR: unable to send message from StringClient", ex);
        } catch (ClassNotFoundException ex)
        {
            LogPrinter.printTestError("ERR: unable to cast transmission object from StringClient", ex);
        } finally
        {
            try
            {
                socket.close();
            } catch (IOException ex)
            {
            }
        }
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
    public Object call() throws Exception
    {
        String returnedMessage = null;
        try
        {
            returnedMessage = connectSendRecieveTest(clientNumber);
        } catch (IOException e)
        {
            LogPrinter.printTestError("ERR: unable to transmit from CountingClient", e);
        } catch (ClassNotFoundException ex)
        {
            LogPrinter.printTestError("ERR: unable to cast transmission object from CountingClient", ex);
        } finally
        {
            if (returnedMessage != null && Integer.parseInt(returnedMessage) == clientNumber)
            {
                toAdd.incrementAndGet();
            }
        }
        return returnedMessage;
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
    public Object call() throws Exception
    {
        String returnedMessage = null;
        try
        {
            returnedMessage = connectSendRecieveTest(clientNumber);
        } catch (IOException | ClassNotFoundException e)
        {
            LogPrinter.printTestError("ERR: unable to cast transmission object from SimpleClient", e);
        }
        return returnedMessage;
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
            LogPrinter.printTestError("ERR: testing UDP failed to join group.", e);
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
