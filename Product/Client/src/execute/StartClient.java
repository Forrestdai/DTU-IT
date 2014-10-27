/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package execute;

import connection.udp.UDPListener;

/**
 *
 * @author JamesFoxes
 */

public class StartClient
{
    public static final ExecutorPool threads = new ExecutorPool();
    
    public static void main(String[] args)
    {
        UDPListener listener = new UDPListener();
        threads.schedule(listener);
    }
}
