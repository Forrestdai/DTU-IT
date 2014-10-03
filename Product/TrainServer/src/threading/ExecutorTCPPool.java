/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package threading;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import processing.ClientRequestProcessor;

/**
 *
 * @author James
 */
public class ExecutorTCPPool implements ClientScheduler
{

    private Executor executor;
    private final int availableThreads = 20;

    public ExecutorTCPPool()
    {
        executor = Executors.newFixedThreadPool(availableThreads);
    }
    
    @Override
    public void schedule(ClientRequestProcessor requestProcessor)
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
