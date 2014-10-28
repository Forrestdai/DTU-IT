/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedlist;

import java.time.DayOfWeek;

/**
 *
 * @author JamesFoxes
 */
public class SentinelLastNode<E> implements Node<E>
{
    private Node prevNode;

    @Override
    public int findElementLocationRecursive(E value, int index)
    {
        return -1;
    }

    @Override
    public void insertElementRecursive(E value, int index)
    {
        insertBefore(value);
    }

    public void insertBefore(E value)
    {
        Node newNode = initializeNewNode(value);
        prevNode.setNextNode(newNode);
        prevNode = newNode;
    }
    
    private Node initializeNewNode(E value)
    {
        Node newNode = new ValueNode();
        newNode.setElementValue(value);
        
        newNode.setPreviousNode(prevNode);
        newNode.setNextNode(this);
        
        return newNode;
    }

    @Override
    public void setElementValue(E value)
    {
    }

    @Override
    public E getElementRecursive(int index)
    {
        throw new NullPointerException("reached end of list");
    }

    @Override
    public void setPreviousNode(Node node)
    {
        prevNode = node;
    }

    @Override
    public void setNextNode(Node node)
    {
    }

    @Override
    public int countElementsRecursive(int counter)
    {
        return counter;
    }
}
