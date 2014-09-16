package server;


import java.io.*;
import java.net.*;
import transmission.Transferable;

class TCPServer
{

    public static void main(String argv[]) throws Exception
    {
        String request, reply;
        int port = 2954;
        
        ServerSocket listenSocket = new ServerSocket(port);
        while (true)
        {
            try (Socket clientSocket = listenSocket.accept())
            {
                ObjectInputStream objectInStream = new ObjectInputStream(clientSocket.getInputStream());
                
                Object obj = objectInStream.readObject();
                
                try
                {
                    Transferable returnedObject = (Transferable) obj;
                    returnedObject.printMessage();
                } catch (Exception e)
                {
                    System.err.println("TypeCast error");
                }
            }
        }
    }
}
