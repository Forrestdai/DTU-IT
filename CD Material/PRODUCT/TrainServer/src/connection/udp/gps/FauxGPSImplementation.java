/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection.udp.gps;

import execute.Server;
import helpers.ServerState;
import threading.executiontypes.ExecuteOnImpulse;
import trafficrouting.TransportNode;

/**
 *
 * @author JamesFoxes
 */
public class FauxGPSImplementation implements GPS
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
    public void setState(ServerState.State state)
    {
        Server.state.currentState = state;
    }

    @Override
    public void setStop(TransportNode stop)
    {
        Server.state.currentStop = stop;
    }

}
