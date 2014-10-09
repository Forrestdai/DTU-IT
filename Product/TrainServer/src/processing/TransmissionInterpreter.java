/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processing;

import commands.Command;
import commands.ErrorCommand;
import commands.GetConnCommand;
import commands.GetInfoCommand;
import commands.GetJourneyCommand;
import commands.GetLogoutCommand;
import java.io.IOException;
import java.net.Socket;
import transmission.common.TransmissionPacket;

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
        Command toExecute;
        switch (recievedTransmission.command)
        {
            case GETINFORMATION:
                toExecute = new GetInfoCommand();
                break;
            case GETJOURNEY:
                toExecute = new GetJourneyCommand();
                break;
            case LOGOUT:
                toExecute = new GetLogoutCommand();
                break;
            case CONN:
                toExecute = new GetConnCommand();
                break;
            default:
                toExecute = new ErrorCommand();
                break;
        }
        return toExecute;
    }

}
