/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package users;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author James
 */
public class PotentialUsersArray
{
    //concurrent array is FAST to traverse but SLOW to add
    private CopyOnWriteArrayList<User> potentialUsers;
    private CopyOnWriteArrayList<Integer> potentialUserIDArray;
    
    public PotentialUsersArray()
    {
        potentialUsers = new CopyOnWriteArrayList<>();
        potentialUserIDArray = new CopyOnWriteArrayList<>();
    }
    
    public boolean testIfContainsUser(User user)
    {
        return potentialUserIDArray.contains(user.ID);
    }
    
    public void pushUser(User user)
    {
        potentialUsers.add(user);
        potentialUserIDArray.add(user.ID);
    }
    
    public User pollUser(User user)
    {
        return potentialUsers.remove(potentialUsers.indexOf(user, 0));
    }
}
