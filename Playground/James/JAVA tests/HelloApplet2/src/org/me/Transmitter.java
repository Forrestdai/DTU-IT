/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me;

/**
 *
 * @author James
 */
public class Transmitter extends Thread
{

    private Communication commLink = new CommunicationImpl();
    
    private String message;

    public Transmitter(String message)
    {
        this.message = message;
    }

    public void run()
    {
        System.out.println("Thread Running");
        
        for (int i = 0; i < message.length(); ++i)
        {
            try
            {
                System.out.println("Sending:");
                System.out.println(message.charAt(i));
                
                commLink.sendMessage(Character.toString(message.charAt(i)));
                
                Thread.sleep(1000);
            } catch (InterruptedException ex)
            {
                System.err.println("Thread Interrupted");
                return;
            }
            /*String toDisplay = movieName.getText() + movieFavourite.getText() + movieWhenLastSeen.getSelectedItem().toString();
            displaySent.setText(toDisplay.toUpperCase());
            movieName.setText("");
            movieFavourite.setText("");*/
        }
    }
}
