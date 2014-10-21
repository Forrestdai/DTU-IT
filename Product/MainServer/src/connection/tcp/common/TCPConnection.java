/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package connection.tcp.common;

import connection.tcp.ConnectionManager;
import common.interfaces.ServerExecutable;
import java.io.IOException;
import java.net.SocketException;

/**
 *
 * @author JamesFoxes
 */
public abstract class TCPConnection implements ServerExecutable
{
    
    public ConnectionManager connectionManager;
    
    public volatile boolean keepProcessing = true;

    public TCPConnection() throws IOException
    {
        connectionManager = new ConnectionManager();
    }
    
    @Override
    public void stopProcessing()
    {
        keepProcessing = false;
        connectionManager.shutdown();
    }
    
    private void handle(Exception e)
    {
        if (!(e instanceof SocketException))
        {
            e.printStackTrace();
        }
    }
}
