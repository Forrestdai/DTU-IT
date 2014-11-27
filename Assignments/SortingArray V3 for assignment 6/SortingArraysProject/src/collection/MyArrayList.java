/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import collection.nodes.BetweenNode;
import java.util.ArrayList;

/**
 *
 * @author JamesFoxes
 */
public class MyArrayList implements MyCollection
{

    ArrayList<Integer> arrayList;

    public MyArrayList()
    {
        arrayList = new ArrayList<>();
    }

    @Override
    public void addFirst(int dataToStore)
    {
        arrayList.add(0, dataToStore);
    }

    @Override
    public void addLast(int dataToStore)
    {
        arrayList.add(arrayList.size(), dataToStore);
    }

    @Override
    public void add(int dataToStore, int index)
    {
        arrayList.add(index, dataToStore);
    }

    @Override
    public BetweenNode get(int index)
    {
        return new BetweenNode(arrayList.get(index));
    }

    @Override
    public int size()
    {
        return arrayList.size();
    }

    @Override
    public void update(int dataToAdd, int index)
    {
        arrayList.set(index, dataToAdd);
    }

    @Override
    public int[] toArray()
    {
        int[] toReturn = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++)
        {
            toReturn[i] = arrayList.get(i);
        }
        return toReturn;
    }

    @Override
    public MyCollection createFromArray(int[] array)
    {
        MyCollection toReturn = new MyArrayList();
        for (int i = 0; i < arrayList.size(); i++)
        {
            toReturn.add(array[i], i);
        }
        return toReturn;
    }

    @Override
    public void printArrayInLine()
    {
        for (Integer arrayListElement : arrayList)
        {
            System.out.print(arrayListElement);
        }
    }

    @Override
    public BetweenNode getFirst()
    {
        return new BetweenNode(arrayList.get(0));
    }

    @Override
    public BetweenNode getLast()
    {
        return new BetweenNode(arrayList.get(arrayList.size() - 1));
    }

    @Override
    public void removeFirst()
    {
        arrayList.remove(0);
    }

    @Override
    public void removeLast()
    {
        arrayList.remove(arrayList.size() - 1);
    }

    @Override
    public void remove(int index)
    {
        arrayList.remove(index);
    }

}
