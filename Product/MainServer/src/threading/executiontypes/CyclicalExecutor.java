/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threading.executiontypes;

import common.interfaces.ServerExecutable;

/**
 *
 * @author James
 */
public class CyclicalExecutor implements ServerExecutable
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
        while (true)
        {
            Thread.sleep(interval);
            toExecute.execute();
        }
    }

    @Override
    public void stopProcessing() throws Exception
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
