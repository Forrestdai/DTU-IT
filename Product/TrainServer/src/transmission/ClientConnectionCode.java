/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transmission;

/**
 *
 * @author JamesFoxes
 */
public class ClientConnectionCode
{
    public int code;

    public ClientConnectionCode()
    {
        code = (int)(Math.random()*100000000);
    }
}
