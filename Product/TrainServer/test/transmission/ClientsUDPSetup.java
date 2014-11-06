/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transmission;

import helpers.LogPrinter;
import java.net.MulticastSocket;
import java.util.ArrayList;

/**
 *
 * @author JamesFoxes
 */
class ClientsUDPSetup
{

    public ArrayList<Thread> threads;
    public ArrayList<UDPClient> clients;
    public ArrayList<String[]> returnMessages;
    private MulticastSocket socket;
    private int numberOfClients;

    public ClientsUDPSetup(int numberOfClients, MulticastSocket socket)
    {
        this.socket = socket;
        this.numberOfClients = numberOfClients;

        threads = new ArrayList<>();
        clients = new ArrayList<>();
        returnMessages = new ArrayList<>();

        initializeClients();
    }

    private void initializeClients()
    {
        for (int i = 0; i < numberOfClients; i++)
        {
            UDPClient client = new UDPClient(socket);
            Thread thread = new Thread(client);

            clients.add(client);
            threads.add(thread);
            returnMessages.add(new String[3]);
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
            if (!threads.get(i).isAlive())
            {
                String[] message = clients.get(i).getReturnedMessage();
                returnMessages.set(i, message);
                LogPrinter.print(message[i] + " " + i);
            } else
            {
                threads.get(i).interrupt();
            }
        }
    }
}
