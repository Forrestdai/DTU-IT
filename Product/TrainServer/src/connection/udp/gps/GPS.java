/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection.udp.gps;

import helpers.ServerState;
import threading.executiontypes.ExecuteOnImpulse;

/**
 *
 * @author JamesFoxes
 */
public interface GPS
{
    public void transmit();
    public void setState(ServerState state);
    public void setInterface(ExecuteOnImpulse sendUDPSignalImpulse);
}
