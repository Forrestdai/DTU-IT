/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package helpers;

import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author James
 */
public class ActiveUsersArray
{
    //concurrent array is FAST to traverse but SLOW to add
    private CopyOnWriteArrayList<User> activeUsers;
    private CopyOnWriteArrayList<Integer> ActiveUserIDArray;
    private ListIterator<User> userList;
    
    public ActiveUsersArray()
    {
        activeUsers = new CopyOnWriteArrayList<>();
        ActiveUserIDArray = new CopyOnWriteArrayList<>();
    }
    
    public boolean testIfContainsUser(User user)
    {
        if (user.ID == -1)
        {
            return true;
        }
        return ActiveUserIDArray.contains(user.ID);
    }
    
    public void pushUser(User user)
    {
        activeUsers.add(user);
        ActiveUserIDArray.add(user.ID);
    }
    
    public void removeUser(User user)
    {
        userList = activeUsers.listIterator();

        for (; userList.hasNext();)
        {
            User nextUser = userList.next();
            if (nextUser.ID.equals(user.ID))
            {
                activeUsers.remove(nextUser);
                break;
            }
        }
    }
    
    public ListIterator<User> getUserIterator()
    {
        userList = activeUsers.listIterator();
        return userList;
    }
    
    public User getUserByIndex(int index)
    {
        return activeUsers.get(index);
    }
    
    public int getArraySize()
    {
        return activeUsers.size();
    }
}
