package Sorting;

import efficiency.SortingTester;
import Helpers.arrayProperties;
import collection.CollectionType;
import collection.MyCollection;
import collection.MyLinkedList;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SortingTest
{
    MyCollection arrayToSort;

    private MyCollection createRandomArray(int arraySize, int fromZeroTo)
    {
        MyCollection scrambledArray = new MyLinkedList();
        for (int i = 0; i < arraySize; ++i)
        {
            scrambledArray.add((int) (Math.random() * fromZeroTo), i);
        }
        return scrambledArray;
    }

    @Before
    public void setUp()
    {
        arrayToSort = createRandomArray(500, 1000);
        System.out.println("Printing Array to be sorted: (size is " + arrayToSort.size() + ")");
        arrayToSort.printArrayInLine();
        System.out.println("");
    }

    @Test
    public void testSelectionSort()
    {
        MyCollection result = new SelectionSort().sort(arrayToSort);
        ArrayList<MyCollection> toBeQuickSorted = new ArrayList<>();
        toBeQuickSorted.add(arrayToSort);
        MyCollection expected = new SortingTester(new arrayProperties(), CollectionType.MyLinkedList).returnComparisonArraysForTesting(toBeQuickSorted).get(0);
        assertArrayEquals(expected.toArray(), result.toArray());
    }
    
    @Test
    public void testInsertionSort()
    {
        MyCollection result = new InsertionSort().sort(arrayToSort);
        ArrayList<MyCollection> toBeQuickSorted = new ArrayList<>();
        toBeQuickSorted.add(arrayToSort);
        MyCollection expected = new SortingTester(new arrayProperties(), CollectionType.MyLinkedList).returnComparisonArraysForTesting(toBeQuickSorted).get(0);
        assertArrayEquals(expected.toArray(), result.toArray());
    }
    
    @Test
    public void testInsertionFSMSort()
    {
        MyCollection result = new InsertionSortFSM().sort(arrayToSort);
        ArrayList<MyCollection> toBeQuickSorted = new ArrayList<>();
        toBeQuickSorted.add(arrayToSort);
        MyCollection expected = new SortingTester(new arrayProperties(), CollectionType.MyLinkedList).returnComparisonArraysForTesting(toBeQuickSorted).get(0);
        assertArrayEquals(expected.toArray(), result.toArray());
    }
    
    @Test
    public void testBuiltInQuickSort()
    {
        MyCollection result = new BuiltInQuickSort().sort(arrayToSort);
        ArrayList<MyCollection> toBeQuickSorted = new ArrayList<>();
        toBeQuickSorted.add(arrayToSort);
        MyCollection expected = new SortingTester(new arrayProperties(), CollectionType.MyLinkedList).returnComparisonArraysForTesting(toBeQuickSorted).get(0);
        assertArrayEquals(expected.toArray(), result.toArray());
    }
    
}
