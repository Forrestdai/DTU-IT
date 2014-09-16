package transmission;

import java.io.Serializable;

public class TransferPackage implements Serializable, Transferable
{
    private String helloMessage;

    public TransferPackage()
    {
        helloMessage = "Default Message";
    }
    
    public void saveMessage(String message)
    {
        helloMessage = message;
    }
    
    @Override
    public void printMessage()
    {
        System.out.println(helloMessage);
    }
}
