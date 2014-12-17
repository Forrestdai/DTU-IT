package exceptions;

import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author James
 */
public class ServerSocketException extends Exception
{

    public ServerSocketException()
    {
    }

    @Override
    public synchronized Throwable fillInStackTrace()
    {
        return super.fillInStackTrace(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized Throwable getCause()
    {
        return super.getCause(); //To change body of generated methods, choose Tools | Templates.
    }

    
}
