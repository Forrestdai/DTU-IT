/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import trafficrouting.TransportNode;

/**
 *
 * @author JamesFoxes
 */
public class ServerState
{

    public State currentState = State.leftStation;
    public TransportNode currentStop;

    public enum State
    {

        arrivedAtStation, leftStation
    }
}
