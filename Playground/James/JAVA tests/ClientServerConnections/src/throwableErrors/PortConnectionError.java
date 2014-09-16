/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package throwableErrors;

/**
 *
 * @author James
 */
public class PortConnectionError extends Exception
{
    public boolean portWasBound;

    /**
     * Creates a new instance of <code>ResendErr</code> without detail message.
     */
    public PortConnectionError()
    {
        portWasBound = false;
    }

    /**
     * Constructs an instance of <code>ResendErr</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public PortConnectionError(String msg)
    {
        super(msg);
    }
}
