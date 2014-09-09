/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.me;

/**
 *
 * @author James
 */
public class CommunicationImpl implements Communication
{

    @Override
    public String recieveMessage(String toRecieve)
    {
        
        return "";
    }
    
    @Override
    public void sendMessage(String toSend)
    {
        recieveMessage(toSend);
    }
    
}
