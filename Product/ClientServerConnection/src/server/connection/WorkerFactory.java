/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.connection;

import java.io.ObjectInputStream;
import server.connection.helpers.Worker;

/**
 *
 * @author JamesFoxes
 */
abstract class WorkerFactory implements Worker
{

}

class InputInterpreterWorker extends WorkerFactory
{

    private ObjectInputStream inputStream;

    public InputInterpreterWorker(ObjectInputStream inputStream)
    {
        this.inputStream = inputStream;
    }

    @Override
    public void spawnWorker()
    {
        new InputInterpreter(inputStream).start();
    }

}
