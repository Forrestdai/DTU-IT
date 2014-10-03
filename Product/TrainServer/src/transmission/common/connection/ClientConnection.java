/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transmission.common.connection;

import java.net.Socket;
import transmission.common.connection.AddressIdentity;

/**
 *
 * @author James
 */
public class ClientConnection
{
    private AddressIdentity remote;
    private AddressIdentity local;
    private Socket connection;

    public ClientConnection(Socket socket)
    {
        connection = socket;
        
        remote = new AddressIdentity(socket.getInetAddress(), socket.getPort());
        local = new AddressIdentity(socket.getLocalAddress(), socket.getLocalPort());
    }

    public Socket getClientConnection()
    {
        return connection;
    }
}
