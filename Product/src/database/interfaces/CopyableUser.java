/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package database.interfaces;

import java.io.Serializable;

/**
 *
 * @author James
 */
public interface CopyableUser extends Serializable
{
    public String UIDNumber = "";
    public String firstName = "";
    public String lastNames = "";
    public int age = -1;
}
