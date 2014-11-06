/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection.tcp;

import connection.tcp.commands.*;
import java.io.IOException;
import java.net.Socket;
import connection.tcp.common.TransmissionPacket;

/**
 *
 * @author JamesFoxes
 */
public class TransmissionInterpreter
{

    TransmissionPacket recievedTransmission;

    public TransmissionInterpreter(TransmissionPacket message, Socket clientConnection)
    {
        recievedTransmission = message;
        try
        {
            switchExecutionOnCommand().execute(clientConnection, message);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private Command switchExecutionOnCommand()
    {
        System.out.println("Stuff Happens");
        Command toExecute;
        switch (recievedTransmission.command)
        {
            default:
                toExecute = new ErrorCommand();
                break;
        }
        return toExecute;
    }

}
