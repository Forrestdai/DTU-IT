/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package threading;

import common.interfaces.ProcessorRequest;
import common.interfaces.Scheduler;

/**
 *
 * @author James
 */
public class ThreadPerRequestScheduler implements Scheduler
{

    @Override
    public void schedule(final ProcessorRequest requestProcessor)
    {
        Runnable toRun = new Runnable()
        {
            @Override
            public void run()
            {
                requestProcessor.process();
            }
        };
        Thread thread = new Thread(toRun);
        thread.start();
    }
    
}