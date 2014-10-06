/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transmission;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;
import transmission.common.MessageUtils;

/**
 *
 * @author JamesFoxes
 */
public abstract class TestingClient implements Runnable
{

    private int PORT;

    public TestingClient(int portNumber)
    {
        PORT = portNumber;
    }

    public String connectSendRecieve(int i) throws IOException
    {
        System.out.printf("Client %2d: connecting\n", i);
        Socket socket = new Socket("localhost", PORT);

        System.out.printf("Client %2d: sending message\n", i);
        MessageUtils.sendMessage(socket, Integer.toString(i));

        System.out.printf("Client %2d: getting reply\n", i);
        String returnMessage = MessageUtils.getMessage(socket);

        System.out.printf("Client %2d: finished\n.", i);
        socket.close();

        return returnMessage;
    }
}

class CountingClient extends TestingClient
{

    int clientNumber;
    AtomicInteger toAdd;

    CountingClient(int clientNumber, AtomicInteger toAdd, int portNumber)
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
            returnedMessage = connectSendRecieve(clientNumber);
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            if (returnedMessage != null && Integer.parseInt(returnedMessage) == clientNumber)
            {
                toAdd.incrementAndGet();
            }
        }
    }
}

class SimpleClient extends TestingClient
{

    int clientNumber;

    SimpleClient(int clientNumber, int portNumber)
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
            returnedMessage = connectSendRecieve(clientNumber);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
