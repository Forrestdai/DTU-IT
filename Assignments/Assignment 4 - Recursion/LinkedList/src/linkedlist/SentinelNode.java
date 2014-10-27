/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedlist;

/**
 *
 * @author JamesFoxes
 */
public class SentinelNode implements Node
{
    private final boolean isLast;
    private Node nextNode;
    private Node prevNode;

    public SentinelNode(boolean isLast)
    {
        this.isLast = isLast;
    }
    
    @Override
    public int findElementLocation(int value)
    {
        if(isLast)
        {
            return -1;
        }
        return nextNode.findElementLocation(value);
    }

    @Override
    public void insertElement(int value, int index)
    {
        if(isLast)
        {
            insertBefore(value);
        }
        nextNode.insertElement(value, index);
    }

    @Override
    public void insertBefore(int value)
    {
    }

    @Override
    public void insertAfter(int value)
    {
    }
}
