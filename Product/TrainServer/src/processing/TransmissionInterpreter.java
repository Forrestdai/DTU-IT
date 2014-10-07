/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processing;

import java.io.IOException;
import java.net.Socket;
import transmission.common.MessageUtils;
import transmission.common.TransmissionPackage;

/**
 *
 * @author JamesFoxes
 */
public class TransmissionInterpreter
{

    TransmissionPackage recievedTransmission;
    private final Socket clientConnection;

    public TransmissionInterpreter(TransmissionPackage message, Socket clientConnection)
    {
        this.clientConnection = clientConnection;
        recievedTransmission = message;
        switchOnCommand();
    }

    private void switchOnCommand()
    {
        TransmissionPackage TEMP = recievedTransmission;
        switch (recievedTransmission.command)
        {
            case GETINFO:
                TEMP.command = TransmissionPackage.Commands.INFO;
                try
                {
                    MessageUtils.sendTransmission(clientConnection, TEMP);
                } catch (IOException ex)
                {
                }
                break;
            case GETJOURNEY:
                TEMP.command = TransmissionPackage.Commands.JOURNEY;
                try
                {
                    MessageUtils.sendTransmission(clientConnection, TEMP);
                } catch (IOException ex)
                {
                }
                break;
            case LOGOUT:
                TEMP.command = TransmissionPackage.Commands.ACKLOGOUT;
                try
                {
                    MessageUtils.sendTransmission(clientConnection, TEMP);
                } catch (IOException ex)
                {
                }
                break;
            case CONN:
                TEMP.command = TransmissionPackage.Commands.ACK;
                try
                {
                    MessageUtils.sendTransmission(clientConnection, TEMP);
                } catch (IOException ex)
                {
                }
                break;
            default:
                try
                {
                    MessageUtils.sendTransmission(clientConnection, TEMP);
                } catch (IOException ex)
                {
                }
                break;

        }
    }

}
