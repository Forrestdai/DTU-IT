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
    private String[] splitMessage;

    public ReadServerData(String message)
    {
        splitIncomingMessage(message);
        address = splitMessage[1];
        port = Integer.parseInt(splitMessage[2]);
        ticketCode = splitMessage[3];
    }
    
    private void splitIncomingMessage(String message)
    {
        splitMessage = message.split("\\s+"); //split on space
    }
}
