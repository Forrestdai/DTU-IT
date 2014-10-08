/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package execute;

import interfaces.ExecuteOnImpulse;

/**
 *
 * @author JamesFoxes
 */
public class TransmitUDP implements ExecuteOnImpulse
{

    public TransmitUDP()
    {
    }

    @Override
    public void execute()
    {
        System.out.println("Hello from UDP");
    }
    
}
