/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package collection;


import collection.nodes.BetweenNodeChar;
import java.util.LinkedList;

/**
 *
 * @author JamesFoxes
 */
public class MyJavaLinkedListChar implements MyCollectionChar
{

    LinkedList<Character> linkedList;
    
    public MyJavaLinkedListChar()
    {
        linkedList = new LinkedList<>();
    }

    @Override
    public void addFirst(char dataToStore)
    {
        linkedList.addFirst(dataToStore);
    }

    @Override
    public void addLast(char dataToStore)
    {
        linkedList.addLast(dataToStore);
    }

    @Override
    public void add(char dataToStore, int index)
    {
        linkedList.add(index, dataToStore);
    }

    @Override
    public BetweenNodeChar get(int index)
    {
        return new BetweenNodeChar(linkedList.get(index));
    }

    @Override
    public int size()
    {
        return linkedList.size();
    }

    @Override
    public void update(char dataToAdd, int index)
    {
        linkedList.set(index, dataToAdd);
    }

    @Override
    public char[] toArray()
    {
        char[] toReturn = new char[linkedList.size()];
        for (int i = 0; i < linkedList.size(); i++)
        {
            toReturn[i] = linkedList.get(i);
        }
        return toReturn;
    }

    @Override
    public MyCollectionChar createFromArray(char[] array)
    {
        MyCollectionChar toReturn = new MyArrayListChar();
        for (int i = 0; i < linkedList.size(); i++)
        {
            toReturn.add(array[i], i);
        }
        return toReturn;
    }

    @Override
    public void printArrayInLine()
    {
        for (Character arrayListElement : linkedList)
        {
            System.out.print(arrayListElement);
        }
    }

    @Override
    public BetweenNodeChar getFirst()
    {
        return new BetweenNodeChar(linkedList.getFirst());
    }

    @Override
    public BetweenNodeChar getLast()
    {
        return new BetweenNodeChar(linkedList.getLast());
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
