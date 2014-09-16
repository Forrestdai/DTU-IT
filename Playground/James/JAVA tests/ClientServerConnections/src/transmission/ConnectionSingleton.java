/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package transmission;

import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author James
 */
public class ConnectionSingleton
{
    private final int PORT = 2954;
    private ServerSocket listenSocket;
    private Socket clientSocket;
    
    private ConnectionSingleton instance;

    public ConnectionSingleton()
    {
        
    }
    
    
}
