/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transmission;

import transmission.common.connection.Connection;
import java.io.IOException;
import java.net.MulticastSocket;

/**
 *
 * @author JamesFoxes
 */
public class BroadcastUDPConnection implements Connection, Runnable
{

    MulticastSocket socket;

    public BroadcastUDPConnection() throws IOException
    {
        this.socket = new MulticastSocket();
    }
    
    @Override
    public void send()
    {
    }

    @Override
    public void stopProcessing()
    {
    }

    @Override
    public void run()
    {
    }

}
