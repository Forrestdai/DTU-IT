package transmission;

import java.io.IOException;
import java.net.*;

/**
 *
 * @author James
 */
public class Connection
{

    private final int PORT = 2954;
    private ServerSocket listenSocket;
    private Socket clientSocket;

    public Socket getClientSocket()
    {
        open();
        return new Socket();
    }

    private void open()
    {
        try
        {
            listenSocket = new ServerSocket(PORT);
            clientSocket = listenSocket.accept();
        } catch (IOException ex)
        {
            System.err.println("SOCKET ERR: " + ex.getMessage());

        }

    }

    private void close()
    {

    }

}
