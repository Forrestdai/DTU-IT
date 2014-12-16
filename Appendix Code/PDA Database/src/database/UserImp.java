package database;

import database.interfaces.User;


public class UserImp implements User

{
  
    private Integer CustomerID;
    private String FirstName;
    private String LastName;
    private String Email;
    private String Password;
    private Integer Balance;
        
 
    public UserImp ()
    {

    }

    public Integer getBalance()
    {
        return Balance;
    }

    public void setBalance(Integer UserAmount)
    {
        this.Balance = UserAmount;
    }

    public String getPassword()
    {
        return Password;
    }

    public void setPassword(String Password)
    {
        this.Password = Password;
    }

    public int getCustomerID()
    {
        return CustomerID;
    }

    public void setCustomerID(int CustomerID)
    {
        this.CustomerID = CustomerID;
    }

    public String getFirstName()
    {
        return FirstName;
    }

    public void setFirstName(String FirstName)
    {
        this.FirstName = FirstName;
    }

    public String getLastName()
    {
        return LastName;
    }

    public void setLastName(String LastName)
    {
        this.LastName = LastName;
    }

    public String getEmail()
    {
        return Email;
    }

    public void setEmail(String Email)
    {
        this.Email = Email;
    }


     public UserImp (int CustomerID, String FirstName, String LastName, String Email, String Password)
    {

        this.CustomerID = CustomerID;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Email = Email;
        this.Password=Password;
//        this.UserAmount =UserAmount;

    }

   

}