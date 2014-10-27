/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package execute;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 *
 * @author JamesFoxes
 */
public class ExecutorPool implements Scheduler
{

    private final Executor executor;
    private final int availableThreads = 10;

    public ExecutorPool()
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
