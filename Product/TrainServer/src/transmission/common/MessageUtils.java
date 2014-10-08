package transmission.common;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author James
 */
public class MessageUtils
{
    public static void sendTransmission(Socket socket, TransmissionPacket message) throws IOException
    {
        OutputStream stream = socket.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(stream);
        oos.writeObject(message);
        oos.flush();
    }
    
    public static TransmissionPacket getTransmission(Socket socket) throws IOException, ClassNotFoundException
    {
        InputStream stream = socket.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(stream);
        return (TransmissionPacket) ois.readObject();
    }
}
