/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package execute;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import transmission.IncomingUserConnection;

/**
 *
 * @author JamesFoxes
 */
public class Server
{
    public static void main(String[] args)
    {
        try
        {
            new IncomingUserConnection();
        } catch (IOException ex)
        {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
