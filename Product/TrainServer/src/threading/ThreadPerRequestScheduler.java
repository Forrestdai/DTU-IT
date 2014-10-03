/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package threading;

import processing.ClientRequestProcessor;

/**
 *
 * @author James
 */
public class ThreadPerRequestScheduler implements ClientScheduler
{

    @Override
    public void schedule(final ClientRequestProcessor requestProcessor)
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
