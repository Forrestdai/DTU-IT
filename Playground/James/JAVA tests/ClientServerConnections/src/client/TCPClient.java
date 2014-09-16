package client;

import java.io.*;
import java.net.*;
import transmission.*;

class TCPClient
{

    public static void main(String argv[]) throws Exception
    {
        Socket socket = new Socket("thelizard6.eitlab.ihk-edu.dk", 2954);
        ObjectOutputStream objectOutStream = new ObjectOutputStream(socket.getOutputStream());
        
        TransferPackage tp = new TransferPackage();
        tp.saveMessage("Writing to the server");
        
        objectOutStream.writeObject(tp);
        
        socket.close();
    }
}
