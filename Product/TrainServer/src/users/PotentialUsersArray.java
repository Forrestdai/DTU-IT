/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package users;

import common.interfaces.ServerMainExecutable;
import execute.Server;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import processing.ServerProcessorRequest;
import transmission.IncomingUserConnectionsHandler;

/**
 *
 * @author James
 */
public class PotentialUsersArray implements ExecutableCyclic
{
    //concurrent array is FAST to traverse but SLOW to add
    private CopyOnWriteArrayList<User> potentialUsers;
    private CopyOnWriteArrayList<Integer> potentialUserIDArray;
    private ConcurrentLinkedQueue<User> toBePushed;
    
    public PotentialUsersArray()
    {
        toBePushed = new ConcurrentLinkedQueue();
        potentialUsers = new CopyOnWriteArrayList<>();
        potentialUserIDArray = new CopyOnWriteArrayList<>();
        
        initializeCyclicalPushing();
    }
    
    public boolean containsUser(User user)
    {
        return potentialUserIDArray.contains(user.ID);
    }
    
    public void pushUser(User user)
    {
        toBePushed.add(user);
    }
    
    private void initializeCyclicalPushing()
    {
        ServerMainExecutable toExecute = new CyclicalExecutor(this);
        ServerProcessorRequest process = new ServerProcessorRequest(toExecute);
        Server.serverThreadPool.schedule(process);
    }

    @Override
    public void execute()
    {
        push();
    }

    private void push()
    {
        
    }
}
