/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package processing;

import common.interfaces.ProcessorRequest;
import common.interfaces.ServerMainExecutable;

/**
 *
 * @author JamesFoxes
 */
public class ServerProcessorRequest implements ProcessorRequest
{
    ServerMainExecutable toStart;

    public ServerProcessorRequest(ServerMainExecutable toStart)
    {
        this.toStart = toStart;
    }

    @Override
    public void process()
    {
        toStart.startProcessing();
    }
    
}
