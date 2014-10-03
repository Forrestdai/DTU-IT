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
public interface ClientScheduler
{
    public void schedule(ClientRequestProcessor requestProcessor);
    
}
