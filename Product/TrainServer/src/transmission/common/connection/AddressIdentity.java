/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package transmission.common.connection;

import java.net.InetAddress;

/**
 *
 * @author James
 */
public class AddressIdentity
{
    public int port;
    public InetAddress address;

    public AddressIdentity(InetAddress address, int port)
    {
        this.address = address;
        this.port = port;
    }
    
    
}
