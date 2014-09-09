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
public interface Communication
{
    public String recieveMessage(String toRecieve);
    public void sendMessage(String toSend);
}
