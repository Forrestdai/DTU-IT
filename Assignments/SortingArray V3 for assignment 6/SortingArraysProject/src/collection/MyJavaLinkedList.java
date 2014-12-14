/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package collection;

import collection.nodes.BetweenNode;
import java.util.LinkedList;

/**
 *
 * @author JamesFoxes
 */
public class MyJavaLinkedList implements MyCollection
{

    LinkedList<Object> linkedList;
    
    public MyJavaLinkedList()
    {
        linkedList = new LinkedList<>();
    }

    @Override
    public void addFirst(int dataToStore)
    {
        linkedList.addFirst(dataToStore);
    }

    @Override
    public void addLast(int dataToStore)
    {
        linkedList.addLast(dataToStore);
    }

    @Override
    public void add(int dataToStore, int index)
    {
        linkedList.add(index, dataToStore);
    }

    @Override
    public BetweenNode get(int index)
    {
        return new BetweenNode(linkedList.get(index));
    }

    @Override
    public int size()
    {
        return linkedList.size();
    }

    @Override
    public void update(int dataToAdd, int index)
    {
        linkedList.set(index, dataToAdd);
    }

    @Override
    public int[] toArray()
    {
        int[] toReturn = new int[linkedList.size()];
        for (int i = 0; i < linkedList.size(); i++)
        {
            toReturn[i] = linkedList.get(i);
        }
        return toReturn;
    }

    @Override
    public MyCollection createFromArray(int[] array)
    {
        MyCollection toReturn = new MyArrayList();
        for (int i = 0; i < linkedList.size(); i++)
        {
            toReturn.add(array[i], i);
        }
        return toReturn;
    }

    @Override
    public void printArrayInLine()
    {
        for (Integer arrayListElement : linkedList)
        {
            System.out.print(arrayListElement);
        }
    }

    @Override
    public BetweenNode getFirst()
    {
        return new BetweenNode(linkedList.getFirst());
    }

    @Override
    public BetweenNode getLast()
    {
        return new BetweenNode(linkedList.getLast());
    }

    @Override
    public void removeFirst()
    {
        linkedList.removeFirst();
    }

    @Override
    public void removeLast()
    {
        linkedList.removeLast();
    }

    @Override
    public void remove(int index)
    {
        linkedList.remove(index);
    }
    
}
