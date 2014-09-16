/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import java.io.InputStream;

/**
 *
 * @author James
 */
public class Worker extends Thread
{

    Worker(InputStream inputStream)
    {
    }
    
    @Override
    public void run()
    {
        System.out.println("Hello from thread");
    }
}
