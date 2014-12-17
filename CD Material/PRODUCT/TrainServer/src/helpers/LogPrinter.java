/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.io.IOException;

/**
 *
 * @author JamesFoxes
 */
public class LogPrinter
{
    public static void printError(String errorMessage, Exception e)
    {
        System.err.println("Err: " + errorMessage);
    }
    
    public static void print(String message)
    {
        System.out.println(message);
    }

    public static void printTestError(String err_unable_to_transmit_from_CountingClien, Exception e)
    {
    }
    
    public static void printTest(String err_unable_to_transmit_from_CountingClien)
    {
    }
}
