/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.connection;

import java.io.ObjectInputStream;

/**
 *
 * @author JamesFoxes
 */
public class InputInterpreter extends Thread
{

    ObjectInputStream inputStream;

    InputInterpreter(ObjectInputStream inputStream)
    {
        this.inputStream = inputStream;
    }

    @Override
    public void run()
    {
        System.err.println("HELLO FROM THREAD");
    }
}
