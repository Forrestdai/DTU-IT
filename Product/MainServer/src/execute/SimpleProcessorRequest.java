/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package execute;

import common.interfaces.ProcessorRequest;
import common.interfaces.ServerExecutable;
import helpers.LogPrinter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JamesFoxes
 */
public class SimpleProcessorRequest implements ProcessorRequest
{
    ServerExecutable toStart;

    public SimpleProcessorRequest(ServerExecutable toStart)
    {
        this.toStart = toStart;
    }

    @Override
    public void process()
    {
        try
        {
            toStart.startProcessing();
        } catch (Exception ex)
        {
            LogPrinter.printError("ERR: SERVER processor request error: ", ex);
            ex.printStackTrace();
        }
    }
    
}
