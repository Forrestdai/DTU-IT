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

    public CyclicalExecutor(ExecutableCyclic toExecute)
    {
        this.toExecute = toExecute;
    }

    @Override
    public void startProcessing()
    {
        toExecute.execute();
    }
    
}
