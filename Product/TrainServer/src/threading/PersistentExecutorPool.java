/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package threading;

import common.interfaces.Scheduler;
import java.util.concurrent.Executors;
import common.interfaces.ProcessorRequest;
import java.util.concurrent.ExecutorService;

/**
 *
 * @author James
 */
public class PersistentExecutorPool implements Scheduler
{
    private final ExecutorService executor;
    private final int availableThreads = 40;

    public PersistentExecutorPool()
    {
        executor = Executors.newFixedThreadPool(availableThreads);
    }
    
    @Override
    public void schedule(final ProcessorRequest requestProcessor)
    {
        Runnable runnable = new Runnable()
        {
            @Override
            public void run()
            {
               requestProcessor.process();
            }
        };
        executor.execute(runnable);
    }
}
