/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 *
 * @author James
 */
public abstract class UserArray
{

    protected ConcurrentMap<Integer, User> userMap;

    public UserArray()
    {
    }

    public boolean userExists(User user)
    {
        if (user.ID == -1)
        {
            return true;
        }
        return userMap.containsKey(user.ID);
    }

    public void pushUser(User user)
    {
        userMap.putIfAbsent(user.ID, user);
    }

    public void removeUser(User user)
    {
        userMap.remove(user.ID);
    }

    public Set<Map.Entry<Integer, User>> getUserEntrySet()
    {
        return userMap.entrySet();
    }

    public User getUserByID(int ID)
    {
        return userMap.get(ID);
    }

    public int getArraySize()
    {
        return userMap.size();
    }
}
