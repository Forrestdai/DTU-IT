/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sort;

import java.util.ArrayList;

/**
 *
 * @author JamesFoxes
 */
public class RecursiveSelectionSort
{

    int size;
    ArrayList<Integer> array;

    public RecursiveSelectionSort(ArrayList<Integer> toSort)
    {
        array = toSort;
        size = toSort.size();
    }

    public void sort()
    {
        runOnce(size-1);
    }
    
    private void runOnce(int fromIndex)
    {
        if (fromIndex < 1)
        {
            return;
        }
        runOnce(--fromIndex);
        int smallestIndex = getSmallestElementIndex(fromIndex);
        if (smallestIndex != fromIndex)
        {
            swapElements(fromIndex, smallestIndex);
        }
    }
    
    private void swapElements(int fromIndex, int toIndex)
    {
        int toElement = array.get(toIndex);
        array.set(toIndex, array.get(fromIndex));
        array.set(fromIndex, toElement);
    }

    private int getSmallestElementIndex(int fromIndex)
    {
        if (fromIndex < 1)
        {
            return 0;
        }
        int minElementIndex = getSmallestElementIndex(fromIndex - 1);
        int minElement = array.get(minElementIndex);
        int thisElement = array.get(fromIndex);
        return minElement < thisElement ? fromIndex - 1 : fromIndex;
    }
}
