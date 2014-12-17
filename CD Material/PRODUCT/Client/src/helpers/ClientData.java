/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import execute.States;

/**
 *
 * @author JamesFoxes
 */
public class ClientData
{
    public final String UDP_ADDRESS = "224.2.2.3";
    public final int UDP_SERVER_PORT = 8888;
    
    public String TCP_SERVER_ADDRESS = "localhost"; //192.168.0.22
    public int TCP_SERVER_PORT = 2954;
    
    public String ServerTicket;
    public int clientID;
    
    public States clientState = States.IDLE;
}
