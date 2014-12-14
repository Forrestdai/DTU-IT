/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import collection.nodes.BetweenNodeChar;
import java.util.ArrayList;

/**
 *
 * @author JamesFoxes
 */
public class MyArrayListChar implements MyCollectionChar
{

    ArrayList<Character> arrayList;

    public MyArrayListChar()
    {
        arrayList = new ArrayList<>();
    }

    @Override
    public void addFirst(char dataToStore)
    {
        arrayList.add(0, dataToStore);
    }

    @Override
    public void addLast(char dataToStore)
    {
        arrayList.add(arrayList.size(), dataToStore);
    }

    @Override
    public void add(char dataToStore, int index)
    {
        arrayList.add(index, dataToStore);
    }

    @Override
    public BetweenNodeChar get(int index)
    {
        return new BetweenNodeChar(arrayList.get(index));
    }

    @Override
    public int size()
    {
        return arrayList.size();
    }

    @Override
    public void update(char dataToAdd, int index)
    {
        arrayList.set(index, dataToAdd);
    }

    @Override
    public char[] toArray()
    {
        char[] toReturn = new char[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++)
        {
            toReturn[i] = arrayList.get(i);
        }
        return toReturn;
    }

    @Override
    public MyCollectionChar createFromArray(char[] array)
    {
        MyCollectionChar toReturn = new MyArrayListChar();
        for (int i = 0; i < arrayList.size(); i++)
        {
            toReturn.add(array[i], i);
        }
        return toReturn;
    }

    @Override
    public void printArrayInLine()
    {
        for (Character arrayListElement : arrayList)
        {
            System.out.print(arrayListElement);
        }
    }

    @Override
    public BetweenNodeChar getFirst()
    {
        return new BetweenNodeChar(arrayList.get(0));
    }

    @Override
    public BetweenNodeChar getLast()
    {
        return new BetweenNodeChar(arrayList.get(arrayList.size() - 1));
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
