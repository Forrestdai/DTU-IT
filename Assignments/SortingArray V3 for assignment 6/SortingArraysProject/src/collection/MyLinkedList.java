/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import collection.nodes.BetweenNode;
import collection.nodes.SentinelNode;
import collection.nodes.Node;

/**
 *
 * @author James
 */
public class MyLinkedList implements MyCollection
{

    private int size;
    private SentinelNode firstNode;
    private SentinelNode lastNode;

    public MyLinkedList()
    {
        initializeList();
    }

    private void initializeList()
    {
        size = 0;
        firstNode = new SentinelNode();
        lastNode = new SentinelNode();
        firstNode.setNextNode(lastNode);
        lastNode.setPreviousNode(firstNode);
    }

    @Override
    public int size()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    @Override
    public void addLast(int dataToStore)
    {
        lastNode.addNodeBefore(dataToStore);
        ++size;
    }

    @Override
    public void addFirst(int dataToStore)
    {
        firstNode.addNodeAfter(dataToStore);
        ++size;
    }

    @Override
    public void removeLast()
    {
        BetweenNode toRemove = tryToCastNode(lastNode.getPreviousNode());
        toRemove.removeNode();
        --size;
    }

    @Override
    public void removeFirst()
    {
        BetweenNode toRemove = tryToCastNode(firstNode.getNextNode());
        toRemove.removeNode();
        --size;
    }

    @Override
    public BetweenNode getFirst()
    {
        return tryToCastNode(firstNode.getNextNode());
    }

    @Override
    public BetweenNode getLast()
    {
        return tryToCastNode(lastNode.getPreviousNode());
    }

    private BetweenNode tryToCastNode(Node toCast)
    {
        BetweenNode cast;
        try
        {
            cast = (BetweenNode) toCast;
        } catch (ClassCastException e)
        {
            System.err.println("Array was empty");
            initializeList();
            return null;
        }
        return cast;
    }

    @Override
    public void add(int dataToStore, int index)
    {
        Node iterator = firstNode;

        for (int i = 0; i < index; i++)
        {
            iterator = iterator.getNextNodeForced();
        }

        iterator.addNodeAfter(dataToStore);
        ++size;
    }

    @Override
    public BetweenNode get(int index)
    {
        Node iterator = firstNode.getNextNode();

        for (int i = 0; i < index; i++)
        {
            if (iterator instanceof SentinelNode)
            {
                throw new NullPointerException("No such element");
            }
            iterator = iterator.getNextNode();
        }
        return tryToCastNode(iterator);
    }

    @Override
    public void update(int dataToAdd, int index)
    {
        BetweenNode toUpdate = get(index);
        toUpdate.setContents(dataToAdd);
    }

    @Override
    public void remove(int index)
    {
        BetweenNode toRemove = get(index);
        toRemove.removeNode();
        --size;
    }

    public int findFirst(int contentToFind)
    {
        Node iterator = firstNode;

        for (int i = 0; i < size; i++)
        {
            iterator = iterator.getNextNode();
            if(iterator instanceof BetweenNode && iterator.getContents() == contentToFind)
            {
                return i;
            }
        }
        return -1;
    }
    
    public int[] findAll(int contentToFind)
    {
        Node iterator = firstNode;
        int[] indicies = new int[size];
        int amountFound = 0;

        for (int i = 0; i < size; i++)
        {
            iterator = iterator.getNextNode();
            if(iterator instanceof BetweenNode && iterator.getContents() == contentToFind)
            {
                indicies[amountFound++] = iterator.getContents();
            }
        }
        return indicies;
    }

    public void clear()
    {
        initializeList();
    }
    
    @Override
    public int[] toArray()
    {
        int[] toReturn = new int[size];
        Node iterator = firstNode;
        
        for (int i = 0; i < size; ++i)
        {
            iterator = iterator.getNextNode();
            toReturn[i] = iterator.getContents();
        }
        
        return toReturn;
    }

    @Override
    public MyCollection createFromArray(int[] array)
    {
        initializeList();
        for (int i = 0; i < array.length; ++i)
        {
            add(array[i], i);
        }
        return this;
    }

    @Override
    public void printArrayInLine()
    {
        for (int i = 0; i < size; ++i)
        {
            System.out.print(get(i).getContents() + " ");
        }
    }
}
