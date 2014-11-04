
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author JamesFoxes
 */
public class BinarySearch
{

    private ArrayList<Integer> array;
    private int searchFor;
    private int size;
    private int upperBoundIndex;
    private int lowerBoundIndex;

    public BinarySearch(ArrayList<Integer> array)
    {
        this.array = array;
        size = array.size();
        upperBoundIndex = size;
        lowerBoundIndex = 0;
    }

    public int getIndexIterative(int searchFor)
    {
        int i = size / 2;
        while (i > lowerBoundIndex && i < upperBoundIndex)
        {
            int element = array.get(i);
            if (element == searchFor)
            {
                return i;
            }
            if (element > searchFor)
            {
                upperBoundIndex = i;
                i -= (upperBoundIndex - lowerBoundIndex) / 2;
            }
            lowerBoundIndex = i;
            i += (upperBoundIndex - lowerBoundIndex) / 2;
        }
        return -1;
    }

    public int getIndexRecursive(int searchFor)
    {
        this.searchFor = searchFor;
        return search(size / 2);
    }

    private int search(int i)
    {
        if (i > lowerBoundIndex && i < upperBoundIndex)
        {
            int element = array.get(i);
            if (element == searchFor)
            {
                return i;
            }
            if (element > searchFor)
            {
                upperBoundIndex = i;
                return search(i - ((upperBoundIndex - lowerBoundIndex) / 2));
            }
            lowerBoundIndex = i;
            return search(i + ((upperBoundIndex - lowerBoundIndex) / 2));
        }
        return -1;
    }
}
