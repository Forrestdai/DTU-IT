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
public class PotentialUsersArray
{

    //concurrent array is FAST to traverse but SLOW to add
    private CopyOnWriteArrayList<User> potentialUsers;
    private CopyOnWriteArrayList<Integer> potentialUserIDArray;
    private ListIterator<User> userList;

    public PotentialUsersArray()
    {
        potentialUsers = new CopyOnWriteArrayList<>();
        potentialUserIDArray = new CopyOnWriteArrayList<>();
    }

    public boolean testIfContainsUser(User user)
    {
        if (user.ID == -1)
        {
            return true;
        }
        return potentialUserIDArray.contains(user.ID);
    }

    public void pushUser(User user)
    {
        potentialUsers.add(user);
        potentialUserIDArray.add(user.ID);
    }

    public void removeUser(User user)
    {
        userList = potentialUsers.listIterator();

        for (; userList.hasNext();)
        {
            User nextUser = userList.next();
            if (nextUser.ID.equals(user.ID))
            {
                potentialUsers.remove(nextUser);
                break;
            }
        }
    }

    public ListIterator<User> getUserIterator()
    {
        userList = potentialUsers.listIterator();
        return userList;
    }

    public User getUserByIndex(int index)
    {
        return potentialUsers.get(index);
    }

    public int getArraySize()
    {
        return potentialUsers.size();
    }
}
