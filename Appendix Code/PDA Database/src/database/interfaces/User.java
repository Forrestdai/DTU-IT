

package database.interfaces;

import java.io.Serializable;


public interface User extends Serializable
{
    public int CustomerID =0;
    public String FirstName = "";
    public String LastName = "";
    public String Email= "";
    public String Password ="";
    public int Balance =0;

}


