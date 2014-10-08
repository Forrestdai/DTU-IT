/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package execute;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author JamesFoxes
 */
public class pushCollectionToArray
{

    private CopyOnWriteArrayList<User> potentialUsers;
    private ConcurrentLinkedQueue<User> toBePushed;
    private long startTime;
    
    private Runnable potentialUsersReader;
    private Runnable potentialUsersWriter;
    private Runnable toBePushedWriter;

    public pushCollectionToArray()
    {
        int runs = 100;
        toBePushed = new ConcurrentLinkedQueue();
        potentialUsers = new CopyOnWriteArrayList<>();
        runTimedTest(runs);
        
        System.err.println("With an initial array");
        
        toBePushed = new ConcurrentLinkedQueue();
        potentialUsers = new CopyOnWriteArrayList<>();
        initializeLargeArray();
        runTimedTest(runs);
    }
    
    private void initializeLargeArray()
    {
        for (int i = 0; i < 20000; i++)
        {
            potentialUsers.add(new User(1234567890, "Test User Test", "Some Random data that might be added"));
        }
    }

    private void runTimedTest(int runs)
    {
        createRunnables();
        
        System.out.println("This is using interim queue");
        Thread[] writerThreads = new Thread[runs];
        Thread[] readerThreads = new Thread[runs];
        startTimer();
        runThreads(writerThreads, toBePushedWriter);
        runThreads(readerThreads, potentialUsersReader);
        joinThreads(writerThreads);
        joinThreads(readerThreads);
        potentialUsers.addAll(toBePushed);
        read();
        stopTimerAndPrintTime();

        System.out.println("this writes directly to array");
        writerThreads = new Thread[runs];
        startTimer();
        runThreads(writerThreads, potentialUsersWriter);
        runThreads(readerThreads, potentialUsersReader);
        joinThreads(writerThreads);
        joinThreads(readerThreads);
        read();
        stopTimerAndPrintTime();
    }
    
    private void createRunnables()
    {
        toBePushedWriter = new Runnable()
        {
            @Override
            public void run()
            {
                toBePushed.add(new User(1234567890, "Test User Test", "Some Random data that might be added"));
            }
        };

        potentialUsersWriter = new Runnable()
        {
            @Override
            public void run()
            {
                potentialUsers.add(new User(1234567890, "Test User Test", "Some Random data that might be added"));
            }
        };
        
        potentialUsersReader = new Runnable()
        {
            @Override
            public void run()
            {
                read();
            }
        };
    }
    
    private void runThreads(Thread[] toRun, Runnable run)
    {
        for (int i = 0; i < toRun.length; i++)
        {
            toRun[i] = new Thread(run);
            toRun[i].start();
        }
    }
    
    private void joinThreads(Thread[] toJoin)
    {
        for (int i = 0; i < toJoin.length; i++)
        {
            try
            {
                toJoin[i].join();
            } catch (InterruptedException ex)
            {
            }
        }
    }

    private void startTimer()
    {
        startTime = System.nanoTime();
    }

    private void stopTimerAndPrintTime()
    {
        long time = (System.nanoTime() - startTime);
        System.out.println("Time: " + (((double) time) / 1000000) + "ms.");
    }

    private void read()
    {
        for (User potentialUser : potentialUsers)
        {
            Integer ignore = potentialUser.userID;
        }
    }

}
