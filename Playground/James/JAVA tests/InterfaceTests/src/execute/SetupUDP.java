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
public class SetupUDP
{
    ExecuteOnImpulse sendUDPSignalImpulse;
    GPS_API gpsAPI;

    public SetupUDP()
    {
        sendUDPSignalImpulse = new TransmitUDP();
        gpsAPI = new GPS_API();
        gpsAPI.setInterface(sendUDPSignalImpulse);
        
        System.out.println("Transmitting in 2 seconds...");
        try
        {
            Thread.sleep(2000);
        } catch (InterruptedException ex)
        {
        }
        gpsAPI.transmit();
    }
    
    
}
