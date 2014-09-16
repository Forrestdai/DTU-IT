/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server.connection;

import helpers.OpenClient;
import java.net.*;

/**
 *
 * @author James
 */
public interface InputConnection
{
    public Socket getSocket();
    public OpenClient probeConnection();
}
