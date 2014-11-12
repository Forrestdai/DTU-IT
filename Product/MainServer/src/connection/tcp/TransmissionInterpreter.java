/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection.tcp;

import connection.tcp.commands.ChargeUser;
import connection.tcp.commands.ErrorCommand;
import connection.tcp.commands.Command;
import connection.tcp.commands.GetGraph;
import connection.tcp.commands.GetUser;
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
        System.out.println("Main server interpreting message: " + message.command.toString());
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
        Command toExecute;
        switch (recievedTransmission.command)
        {
            case GETUSERS:
                toExecute = new GetUser();
                break;
            case CHARGEUSERS:
                toExecute = new ChargeUser();
                break;
            case GETGRAPH:
                toExecute = new GetGraph();
            default:
                toExecute = new ErrorCommand();
                break;
        }
        return toExecute;
    }

}
