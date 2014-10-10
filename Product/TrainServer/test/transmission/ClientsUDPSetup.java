/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transmission;

import helpers.LogPrinter;
import java.net.MulticastSocket;

/**
 *
 * @author JamesFoxes
 */
class ClientsUDPSetup
{

    public Thread[] threads;
    public UDPClient[] clients;
    public String[] returnMessages;
    private MulticastSocket socket;
    private int numberOfClients;

    public ClientsUDPSetup(int numberOfClients, MulticastSocket socket)
    {
        this.socket = socket;
        this.numberOfClients = numberOfClients;

        threads = new Thread[numberOfClients];
        clients = new UDPClient[numberOfClients];
        returnMessages = new String[numberOfClients];

        initializeClients();
    }

    private void initializeClients()
    {
        for (int i = 0; i < numberOfClients; i++)
        {
            clients[i] = new UDPClient(socket);
        }
        for (int i = 0; i < numberOfClients; i++)
        {
            threads[i] = new Thread(clients[i]);
        }
        for (int i = 0; i < numberOfClients; i++)
        {
            returnMessages[i] = new String();
        }
    }

    public void startThreads()
    {
        for (Thread thread : threads)
        {
            thread.start();
        }
    }

    public void readMessages() throws InterruptedException
    {
        for (int i = 0; i < numberOfClients; i++)
        {
            if (!threads[i].isAlive())
            {
                returnMessages[i] = clients[i].getReturnedMessage();
                LogPrinter.printTest(returnMessages[i] + " " + i);
            } else
            {
                threads[i].interrupt();
            }
        }
    }
}
