/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection.tcp.commands;

import connection.tcp.common.TransmissionPacket;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author JamesFoxes
 */
public class ChargeUser implements Command
{

    @Override
    public void execute(Socket clientConnection, TransmissionPacket incomingPacket) throws IOException
    {
        
    }
    
}
