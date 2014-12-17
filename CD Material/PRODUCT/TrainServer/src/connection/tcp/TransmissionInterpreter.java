/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection.tcp;

import connection.tcp.commands.ErrorCommand;
import connection.tcp.commands.GetJourneyCommand;
import connection.tcp.commands.Command;
import connection.tcp.commands.GetUserConnectionCommand;
import connection.tcp.commands.ReturnedUsersCommand;
import connection.tcp.commands.UpdateTicketCostCommand;
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
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private Command switchExecutionOnCommand()
    {
        Command toExecute;
        switch (recievedTransmission.command)
        {
            case GETJOURNEY:
                toExecute = new GetJourneyCommand();
                break;
            case USERCONNECTION:
                toExecute = new GetUserConnectionCommand();
                break;
            case RETURNUSERS:
                toExecute = new ReturnedUsersCommand();
                break;
            case TICKETCOST:
                toExecute = new UpdateTicketCostCommand();
                break;
            default:
                toExecute = new ErrorCommand();
                break;
        }
        return toExecute;
    }

}
