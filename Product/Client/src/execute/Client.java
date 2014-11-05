/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package execute;

import connection.udp.UDPConnection;
import connection.udp.UDPListener;

/**
 *
 * @author JamesFoxes
 */
public class Client
{

    public final ExecutorPool threads = new ExecutorPool();
    public static final UDPConnection udpconn = new UDPConnection();
    public States clientState = States.IDLE;

    public Client()
    {
        UDPListener listener = new UDPListener();
        threads.schedule(listener);
    }
}
