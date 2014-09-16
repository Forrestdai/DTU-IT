/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TestMOCKs;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author JamesFoxes
 */
public class MockClient
{
    private static ObjectOutputStream outStream;
    private static int PORT;
    private static String HOST;
    private static Object objectToSend;

    public MockClient(int port, String host, Object objectToSend)
    {
        MockClient.PORT = port;
        MockClient.HOST = host;
        MockClient.objectToSend = objectToSend;
    }
    
    public static void main(String[] args) throws IOException
    {
        Socket clientSocket = new Socket(HOST, PORT);
        outStream = new ObjectOutputStream(clientSocket.getOutputStream());
        outStream.writeUnshared(objectToSend);
    }
}
