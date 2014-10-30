/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.connection;

import interfaces.RecievablePacket;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *
 * @author JamesFoxes
 */
public class InputInterpreter extends Thread
{

    ObjectInputStream inputStream;
    Object recieveObject;
    RecievePackage recievedPackage;

    InputInterpreter(ObjectInputStream inputStream)
    {
        this.inputStream = inputStream;
        readObject();
    }

    @Override
    public void run()
    {
        castToPacketAndQueue();
        System.out.println("HELLO FROM THREAD");
        RecievablePacket packet = TCPServer.incomingQueue.poll();
        if (packet != null)
        {
            System.out.println("SUCCESS!");
        }
    }

    private void readObject()
    {
        try
        {
            recieveObject = inputStream.readObject();
            System.out.println("ReadObject: OK");
        } catch (IOException ex)
        {
            System.err.println("ReadObject Err: IO");
        } catch (ClassNotFoundException ex)
        {
            System.err.println("ReadObject Err: ClassNotFound");
        }
    }

    private void castToPacketAndQueue()
    {
        RecievablePacket packet = (RecievablePacket) recieveObject;
        queuePacket(packet);
    }

    private void queuePacket(RecievablePacket packet)
    {
        TCPServer.incomingQueue.add(packet);
    }
}
