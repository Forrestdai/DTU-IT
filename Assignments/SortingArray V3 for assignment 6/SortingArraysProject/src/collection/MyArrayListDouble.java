/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import collection.nodes.BetweenNodeDouble;
import java.util.ArrayList;

/**
 *
 * @author JamesFoxes
 */
public class MyArrayListDouble implements MyCollectionDouble
{

    ArrayList<Double> arrayList;

    public MyArrayListDouble()
    {
        arrayList = new ArrayList<>();
    }

    @Override
    public void addFirst(double dataToStore)
    {
        arrayList.add(0, dataToStore);
    }

    @Override
    public void addLast(double dataToStore)
    {
        arrayList.add(arrayList.size(), dataToStore);
    }

    @Override
    public void add(double dataToStore, int index)
    {
        arrayList.add(index, dataToStore);
    }

    @Override
    public BetweenNodeDouble get(int index)
    {
        return new BetweenNodeDouble(arrayList.get(index));
    }

    @Override
    public int size()
    {
        return arrayList.size();
    }

    @Override
    public void update(double dataToAdd, int index)
    {
        arrayList.set(index, dataToAdd);
    }

    @Override
    public double[] toArray()
    {
        double[] toReturn = new double[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++)
        {
            toReturn[i] = arrayList.get(i);
        }
        return toReturn;
    }

    @Override
    public MyCollectionDouble createFromArray(double[] array)
    {
        MyCollectionDouble toReturn = new MyArrayListDouble();
        for (int i = 0; i < arrayList.size(); i++)
        {
            toReturn.add(array[i], i);
        }
        return toReturn;
    }

    @Override
    public void printArrayInLine()
    {
        for (Double arrayListElement : arrayList)
        {
            System.out.print(arrayListElement);
        }
    }

    @Override
    public BetweenNodeDouble getFirst()
    {
        return new BetweenNodeDouble(arrayList.get(0));
    }

    @Override
    public BetweenNodeDouble getLast()
    {
        return new BetweenNodeDouble(arrayList.get(arrayList.size() - 1));
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
