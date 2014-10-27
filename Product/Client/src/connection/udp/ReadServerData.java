/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection.udp;

/**
 *
 * @author JamesFoxes
 */
public class ReadServerData
{
    public String address;
    public int port;
    public String ticketCode;

    public ReadServerData(String[] message)
    {
        address = message[1];
        port = Integer.parseInt(message[2]);
        ticketCode = message[3];
    }
}
