package database;

import java.lang.Exception;

/**
 *
 * @author sudhir
 */
public class DataAccessedException extends Exception
{

    private Exception exception;

    //constructor with String argument
    public DataAccessedException(String message)
    {
        super(message);
    }

    //constructor with Exception argument
    public DataAccessedException(Exception exception)
    {
        exception = this.exception;
    }

    //printStackTrace of exception from constructor
    public void printStackTrace()
    {
        exception.printStackTrace();
    }

}
