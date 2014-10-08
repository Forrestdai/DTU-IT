/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

/**
 *
 * @author JamesFoxes
 */
public class LogPrinter
{
    public static void printError(String errorMessage)
    {
        System.err.println("Err: " + errorMessage);
    }
    
    public static void print(String message)
    {
        System.out.println(message);
    }
}
