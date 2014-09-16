/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package transmission;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author James
 */
public class Transmitter
{

    public Transmitter(Connection connection)
    {
        connection.getClientSocket().accept();
        connectionSocket.accept();
    }
    
    
    Socket clientSocket = listenSocket.accept();
            BufferedReader bufIn
                    = new BufferedReader(new InputStreamReader(
                                    clientSocket.getInputStream()));
            DataOutputStream bufOut = new DataOutputStream(
                    clientSocket.getOutputStream());
            request = bufIn.readLine();
            // handle request, generate reply
            bufOut.writeBytes(reply);
            clientSocket.close();
}
