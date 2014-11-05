/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author James
 */
public class PotentialUsers extends UserArray
{

    public PotentialUsers()
    {
        userMap = new ConcurrentHashMap<>();
    }

}
