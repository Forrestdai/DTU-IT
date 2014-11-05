/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package connection.tcp.commands;

import java.io.IOException;
import java.net.Socket;
import connection.tcp.common.TransmissionPacket;

/**
 *
 * @author JamesFoxes
 */
public interface Command
{
    public void execute(Socket clientConnection, TransmissionPacket incomingPacket) throws IOException, ClassNotFoundException;
}
