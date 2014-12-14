/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package collection;


import collection.nodes.BetweenNodeDouble;
import java.util.LinkedList;

/**
 *
 * @author JamesFoxes
 */
public class MyJavaLinkedListDouble implements MyCollectionDouble
{

    LinkedList<Double> linkedList;
    
    public MyJavaLinkedListDouble()
    {
        linkedList = new LinkedList<>();
    }

    @Override
    public void addFirst(double dataToStore)
    {
        linkedList.addFirst(dataToStore);
    }

    @Override
    public void addLast(double dataToStore)
    {
        linkedList.addLast(dataToStore);
    }

    @Override
    public void add(double dataToStore, int index)
    {
        linkedList.add(index, dataToStore);
    }

    @Override
    public BetweenNodeDouble get(int index)
    {
        return new BetweenNodeDouble(linkedList.get(index));
    }

    @Override
    public int size()
    {
        return linkedList.size();
    }

    @Override
    public void update(double dataToAdd, int index)
    {
        linkedList.set(index, dataToAdd);
    }

    @Override
    public double[] toArray()
    {
        double[] toReturn = new double[linkedList.size()];
        for (int i = 0; i < linkedList.size(); i++)
        {
            toReturn[i] = linkedList.get(i);
        }
        return toReturn;
    }

    @Override
    public MyCollectionDouble createFromArray(double[] array)
    {
        MyCollectionDouble toReturn = new MyArrayListDouble();
        for (int i = 0; i < linkedList.size(); i++)
        {
            toReturn.add(array[i], i);
        }
        return toReturn;
    }

    @Override
    public void printArrayInLine()
    {
        for (Double arrayListElement : linkedList)
        {
            System.out.print(arrayListElement);
        }
    }

    @Override
    public BetweenNodeDouble getFirst()
    {
        return new BetweenNodeDouble(linkedList.getFirst());
    }

    @Override
    public BetweenNodeDouble getLast()
    {
        return new BetweenNodeDouble(linkedList.getLast());
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
