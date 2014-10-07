package Sorting;

import collection.MyCollection;
import collection.MyLinkedList;

public class BuiltInQuickSort implements Sortable
{
    @Override
    public MyCollection sort(MyCollection arrayToSort)
    {
        int[] sorted = arrayToSort.toArray();
        java.util.Arrays.sort(sorted);
        
        return new MyLinkedList().createFromArray(sorted);
    }
    
}
