/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transmission.common;

import java.io.Serializable;
import java.net.Socket;

/**
 *
 * @author JamesFoxes
 */
public class TransmissionPacket implements Serializable, Transmittable
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

        GETINFO, GETJOURNEY, LOGOUT, CONN,
        INFO, JOURNEY, ACKLOGOUT, ACK,
        nil
    }
}
