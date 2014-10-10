/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package transmission.common;

import common.interfaces.ServerMainExecutable;
import java.io.IOException;
import java.net.SocketException;
import transmission.common.connection.ConnectionManager;

/**
 *
 * @author JamesFoxes
 */
public abstract class TCPConnection implements ServerMainExecutable
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
