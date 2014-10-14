/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transmission;

import execute.Server;
import helpers.State;
import users.ExecuteOnImpulse;

/**
 *
 * @author JamesFoxes
 */
class FauxGPSImplementation implements GPS
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

    @Override
    public void setState(State state)
    {
        Server.state = state;
    }

}
