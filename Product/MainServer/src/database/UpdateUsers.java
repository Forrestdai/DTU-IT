/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import helpers.LogPrinter;
import helpers.User;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import threading.executiontypes.ExecutableCyclic;

/**
 *
 * @author James
 */
class UpdateUsers implements ExecutableCyclic
{
    private Map<Integer, User> toPushToDatabase;
    private final ConcurrentMap<User, Integer> usersToCharge;
    private final ConcurrentLinkedQueue<User> usersToUpdate;
    private final DatabaseHandler outer;

    public UpdateUsers(final DatabaseHandler outer)
    {
        this.outer = outer;
        usersToCharge = new ConcurrentHashMap<>();
        usersToUpdate = new ConcurrentLinkedQueue<>();
        toPushToDatabase = new HashMap<>();
    }

    @Override
    public void execute()
    {
        try
        {
            runUpdates();
            runCharges();
            outer.pushUpdates(toPushToDatabase.values());
            toPushToDatabase.clear();
        } catch (SQLException ex)
        {
            LogPrinter.printError("SQL error", ex);
        }
    }

    void chargeUser(User user, Integer amount)
    {
        usersToCharge.put(user, amount);
    }

    void updateUser(User user)
    {
        usersToUpdate.add(user);
    }

    void runUpdates() throws SQLException
    {
        for (User user : usersToUpdate)
        {
            User existing;
            if (toPushToDatabase.containsKey(user.ID))
            {
                existing = toPushToDatabase.get(user.ID);
            } else
            {
                existing = outer.getUser(user.ID);
                toPushToDatabase.put(existing.ID, existing);
            }
            User updated = updateUserFields(existing, user);
            //toPushToDatabase.replace(user.ID, updated);
            toPushToDatabase.remove(user.ID);
            toPushToDatabase.put(user.ID, updated);
            usersToUpdate.remove(user);
        }
    }

    void runCharges() throws SQLException
    {
        for (Map.Entry<User, Integer> userEntry : usersToCharge.entrySet())
        {
            User user = userEntry.getKey();
            User existing;
            if (toPushToDatabase.containsKey(user.ID))
            {
                existing = toPushToDatabase.get(user.ID);
            } else
            {
                existing = outer.getUser(user.ID);
                toPushToDatabase.put(existing.ID, existing);
            }
            User charged = chargeUserFields(existing, userEntry.getValue());
            //toPushToDatabase.replace(user.ID, charged);
            toPushToDatabase.remove(user.ID);
            toPushToDatabase.put(user.ID, charged);
            usersToCharge.remove(user);
        }
    }

    User updateUserFields(User existing, User adding)
    {
        User combined = new User(existing.ID);
        combined.firstName = adding.firstName;
        combined.lastName = adding.lastName;
        combined.passWord = adding.passWord;
        combined.balance = adding.balance;
        return combined;
    }

    User chargeUserFields(User existing, Integer charge)
    {
        User combined = existing;
        combined.balance = existing.balance - charge;
        return combined;
    }
    
}
