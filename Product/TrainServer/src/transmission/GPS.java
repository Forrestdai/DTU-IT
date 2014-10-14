/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transmission;

import helpers.State;
import users.ExecuteOnImpulse;

/**
 *
 * @author JamesFoxes
 */
public interface GPS
{
    public void transmit();
    public void setState(State state);
    public void setInterface(ExecuteOnImpulse sendUDPSignalImpulse);
}
