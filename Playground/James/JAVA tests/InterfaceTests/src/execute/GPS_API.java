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
class GPS_API implements GPS
{

    private ExecuteOnImpulse executeUDPTransmission;
    
    public void setInterface(ExecuteOnImpulse impulseInterface)
    {
        executeUDPTransmission = impulseInterface;
    }
    
    @Override
    public void transmit()
    {
        executeUDPTransmission.execute();
    }
}
