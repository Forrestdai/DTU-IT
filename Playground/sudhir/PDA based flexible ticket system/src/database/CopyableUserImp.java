package database;

import database.interfaces.CopyableUser;


public class CopyableUserImp implements CopyableUser

{
   public String customerName;
    
    public String getCustomerName()
    {
        return customerName;
        
        
            

    } 
    public void setCustomerName(String customerName){
    this.customerName =customerName;
    }
    public String customerId;
    private String firstName;
    private String lastName;
    private String email;

    public CopyableUserImp ()
    {

    }

    public CopyableUserImp (String customerId, String firstName, String lastName, String email)
    {

        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

    }

    public String getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId(String customerId)
    {
        this.customerId = customerId;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

}
