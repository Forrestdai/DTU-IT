/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package users;

import common.interfaces.ServerMainExecutable;

/**
 *
 * @author James
 */
public class CyclicalExecutor implements ServerMainExecutable
{
    
    ExecutableCyclic toExecute;
    int interval;

    public CyclicalExecutor(ExecutableCyclic toExecute, int interval)
    {
        this.interval = interval;
        this.toExecute = toExecute;
    }

    @Override
    public void startProcessing() throws InterruptedException
    {
        toExecute.execute();
        Thread.sleep(interval);
    }
    
}