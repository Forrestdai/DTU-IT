/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transmission;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import processing.TransmissionInterpreter;
import transmission.common.MessageUtils;
import transmission.common.TransmissionPackage;

/**
 *
 * @author JamesFoxes
 */
public abstract class TestingClients implements Runnable
{

    protected Socket socket;

    public TestingClients(int portNumber) throws IOException
    {
        socket = new Socket("localhost", portNumber);
    }

    public String connectSendRecieveTest(int i) throws IOException, ClassNotFoundException
    {
        System.out.printf("Client %2d: connecting\n", i);

        System.out.printf("Client %2d: sending message\n", i);
        TransmissionPackage tp = new TransmissionPackage();
        tp.dataString = Integer.toString(i);
        MessageUtils.sendTransmission(socket, tp);

        System.out.printf("Client %2d: getting reply\n", i);
        String returnMessage = MessageUtils.getTransmission(socket).dataString;

        System.out.printf("Client %2d: finished\n.", i);
        socket.close();

        return returnMessage;
    }
}

class CommandClient extends TestingClients
{

    private TransmissionPackage sendMessage;
    private TransmissionPackage returnMessage;

    public CommandClient(int portNumber, TransmissionPackage sendMessage) throws IOException
    {
        super(portNumber);
        this.sendMessage = sendMessage;
        this.returnMessage = new TransmissionPackage();
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
            System.err.println("ERR: unable to send message from StringClient");
        } catch (ClassNotFoundException ex)
        {
            System.err.println("ERR: unable to cast transmission object from StringClient");
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

    public TransmissionPackage getReturnedMessage()
    {
        return returnMessage;
    }
}

class CountingClient extends TestingClients
{

    int clientNumber;
    AtomicInteger toAdd;

    CountingClient(int clientNumber, AtomicInteger toAdd, int portNumber) throws IOException
    {
        super(portNumber);
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
            e.printStackTrace();
        } catch (ClassNotFoundException ex)
        {
            System.err.println("ERR: unable to cast transmission object from CountingClient");
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

    SimpleClient(int clientNumber, int portNumber) throws IOException
    {
        super(portNumber);
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
            e.printStackTrace();
        } catch (ClassNotFoundException ex)
        {
            System.err.println("ERR: unable to cast transmission object from SimpleClient");
        }
    }
}
