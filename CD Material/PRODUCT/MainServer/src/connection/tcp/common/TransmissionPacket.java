/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection.tcp.common;

import java.io.Serializable;

/**
 *
 * @author JamesFoxes
 */
public class TransmissionPacket implements Serializable
{

    public Commands command;
    public String dataString;
    public Object dataObject;

    public TransmissionPacket()
    {
        command = Commands.nil;
    }

    public enum Commands
    {

        GETJOURNEY, USERCONNECTION,
        JOURNEY, ACKNOWLEDGE, GETUSERS, TICKETCOST,
        RETURNUSERS, CHARGEUSERS, GETGRAPH, RETURNGRAPH,
        nil
    }
}
