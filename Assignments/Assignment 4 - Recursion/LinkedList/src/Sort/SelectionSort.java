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
public class SelectionSort
{

    public void sortRecursive(ArrayList<Integer> array, int startIndex)
    {
        if (startIndex >= array.size() - 1)
        {
            return;
        }
        int smallestElementIndex = startIndex;
        for (int index = startIndex + 1; index < array.size(); index++)
        {
            if (array.get(index) < array.get(smallestElementIndex))
            {
                smallestElementIndex = index;
            }
        }
        int temporaryElement = array.get(startIndex);
        array.set(startIndex, array.get(smallestElementIndex));
        array.set(smallestElementIndex, temporaryElement);
        sortRecursive(array, startIndex + 1);
    }

    public void sortIterative(ArrayList<Integer> array)
    {
        int smallestElementIndex;
        int size = array.size();

        for (int i = 0; i < size - 1; ++i)
        {
            smallestElementIndex = i;
            for (int j = i + 1; j < size; j++)
            {
                if (array.get(j) < array.get(smallestElementIndex))
                {
                    smallestElementIndex = j;
                }
            }
            if (smallestElementIndex != i)
            {
                int temporaryElement = array.get(i);
                array.set(i, array.get(smallestElementIndex));
                array.set(smallestElementIndex, temporaryElement);
            }
        }
    }
}
