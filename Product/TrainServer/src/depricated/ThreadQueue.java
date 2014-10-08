/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depricated;

import helpers.LogPrinter;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author JamesFoxes
 */
public class ThreadQueue
{

    private final int CLIENT_HANDLER_AMOUNT = 10;
    private final Thread slowHandler;

    Queue<Thread> ClientHandlers;
    AtomicInteger clientHandlersAvailable;

    public ThreadQueue()
    {
        clientHandlersAvailable = new AtomicInteger(CLIENT_HANDLER_AMOUNT);
        ClientHandlers = new ConcurrentLinkedQueue<>();

        slowHandler = new Thread();
        slowHandler.setName("slowHandler");

        for (int i = 0; i < CLIENT_HANDLER_AMOUNT; i++)
        {
            ClientHandlers.add(new Thread());
            clientHandlersAvailable.incrementAndGet();
        }
    }

    public Thread getClientHandler(Runnable toRun)
    {
        boolean anyHandlerAvailable = clientHandlersAvailable.intValue() < 1;
        int attempts = 0;

        while (!anyHandlerAvailable && attempts < 10)
        {
            anyHandlerAvailable = clientHandlersAvailable.intValue() < 1;
            try
            {
                Thread.sleep(100 * ++attempts);
            } catch (InterruptedException ex)
            {
                LogPrinter.printError("getClientHandler interrupted");
            }
        }
        try
        {
            Thread toSend = ClientHandlers.remove();
            clientHandlersAvailable.decrementAndGet();
            toSend.setContextClassLoader((ClassLoader) toRun);
            return toSend;
        } catch (NoSuchElementException e)
        {
            LogPrinter.printError("Slow Handler Given");
            slowHandler.setContextClassLoader((ClassLoader) toRun);
            return slowHandler;
        }
    }

    public void returnClientHandler(Thread clientHandler)
    {
        Runnable closingRunnable = new Runnable()
        {
            @Override
            public void run()
            {
                if (clientHandler.getName().equals("slowHandler"))
                {
                    Thread.currentThread().interrupt(); //slowHandler must not die
                } else
                {
                    emptyAndKillThread(clientHandler);
                }

                if (clientHandler.isAlive())
                {
                    ClientHandlers.add(new Thread());
                } else
                {
                    ClientHandlers.add(clientHandler);
                }

                Thread.currentThread().interrupt();
            }
        };
        Thread closingThread = new Thread(closingRunnable);
        closingThread.start();
    }

    private void emptyAndKillThread(Thread thread)
    {
        ClassLoader emptyRunnable = new ClassLoader()
        {
        };

        thread.interrupt();
        thread.setContextClassLoader(emptyRunnable);
    }
}
