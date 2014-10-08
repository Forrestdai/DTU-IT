/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transmission;

import common.interfaces.Connection;
import java.io.IOException;
import java.net.MulticastSocket;

/**
 *
 * @author JamesFoxes
 */
public abstract class BroadcastUDPConnection implements Connection, Runnable
{
    MulticastSocket socket;

    public BroadcastUDPConnection() throws IOException
    {
        this.socket = new MulticastSocket();
    }
    
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

    @Override
    public void startProcessing()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
