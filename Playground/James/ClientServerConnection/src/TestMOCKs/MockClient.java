/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TestMOCKs;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JamesFoxes
 */
public class MockClient
{
    private ObjectOutputStream outStream;
    private int PORT;
    private String HOST;
    private Object objectToSend;

    public MockClient(int port, String host, Object objectToSend)
    {
        this.PORT = port;
        this.HOST = host;
        this.objectToSend = objectToSend;
    }
    
    public void runMockclient()
    {
        try
        {
            Socket clientSocket = new Socket(HOST, PORT);
            outStream = new ObjectOutputStream(clientSocket.getOutputStream());
            outStream.writeUnshared(objectToSend);
        } catch (IOException ex)
        {
            Logger.getLogger(MockClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
