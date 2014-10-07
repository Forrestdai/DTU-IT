package efficiency;

import Helpers.*;
import Sorting.*;
import collection.CollectionType;
import collection.MyArrayList;
import collection.MyCollection;
import collection.MyLinkedList;
import java.util.ArrayList;

public class SortingTester
{

    private final int numberOfTimesToRun;
    private final int arraySize;
    private final int testFromZeroTo;

    private MyCollection scrambledArray;
    private MyCollection nearlySortedArray;
    private MyCollection descendingArray;

    private volatile long startTime;
    private volatile long[] elapsedTimes;

    private Sortable sortMethod;

    private CollectionType arrayType;

    public SortingTester(arrayProperties arrayProperties, CollectionType arrayType)
    {
        elapsedTimes = new long[3];

        this.arrayType = arrayType;

        numberOfTimesToRun = arrayProperties.numberOfTimesToRun;
        arraySize = arrayProperties.sizeOfSortArray;
        testFromZeroTo = arrayProperties.testFromZeroTo;
    }

    public long[] run(Sortable sortMethod)
    {
        this.sortMethod = sortMethod;

        for (int i = 0; i < numberOfTimesToRun; ++i)
        {
            createRandomArray();
            createNearlySortedArray();
            createDescendingArray();

            doSort(Method.scrambledArray, scrambledArray);
            doSort(Method.nearlySortedArray, nearlySortedArray);
            doSort(Method.descendingArray, descendingArray);
        }
        return elapsedTimes;
    }

    private void createRandomArray()
    {
        switch (arrayType)
        {
            case MyLinkedList:
                scrambledArray = new MyLinkedList();
                break;
            case Array:
                scrambledArray = new MyArrayList();
                break;
        }

        for (int i = 0; i < arraySize; ++i)
        {
            scrambledArray.add((int) (Math.random() * testFromZeroTo), i);
        }
    }

    private void createNearlySortedArray()
    {
        switch (arrayType)
        {
            case MyLinkedList:
                nearlySortedArray = new MyLinkedList();
                break;
            case Array:
                nearlySortedArray = new MyArrayList();
                break;
        }

        for (int i = 0; i < arraySize; ++i)
        {
            nearlySortedArray.add(i, i);
        }
        int chunkSize = 4;
        for (int i = 0; i < arraySize; i += chunkSize)
        {
            int cap = Math.min(chunkSize + i, arraySize);
            for (int j = i; j < (cap - 1); ++j)
            {
                int swapTarget = (int) (Math.random() * (cap - j)) + j;
                swapElements(nearlySortedArray, j, swapTarget);
            }
        }
    }

    private void swapElements(MyCollection array, int from, int to)
    {
        int fromContents = array.get(from).getContents();
        int toContents = array.get(to).getContents();

        array.update(fromContents, to);
        array.update(toContents, from);
    }

    private void createDescendingArray()
    {
        switch (arrayType)
        {
            case MyLinkedList:
                descendingArray = new MyLinkedList();
                break;
            case Array:
                descendingArray = new MyArrayList();
                break;
        }
        
        for (int i = 0; i < arraySize; ++i)
        {
            descendingArray.add(arraySize - i, i);
        }
    }

    private void doSort(Method method, MyCollection arrayToSort)
    {
        startTimer();
        sortMethod.sort(arrayToSort);
        stopTimerAndSaveElapsedTime(method);
    }

    private void startTimer()
    {
        startTime = System.nanoTime();
    }

    private void stopTimerAndSaveElapsedTime(Method method)
    {
        switch (method)
        {
            case scrambledArray:
                elapsedTimes[0] += System.nanoTime() - startTime;
                break;
            case nearlySortedArray:
                elapsedTimes[1] += System.nanoTime() - startTime;
                break;
            case descendingArray:
                elapsedTimes[2] += System.nanoTime() - startTime;
                break;
        }
    }

    public ArrayList<MyCollection> returnSortedArraysForTesting(Sortable sortMethod)
    {
        this.sortMethod = sortMethod;
        ArrayList<MyCollection> returnArrays = new ArrayList<>();

        createRandomArray();
        createNearlySortedArray();
        createDescendingArray();

        doSort(Method.scrambledArray, scrambledArray);
        doSort(Method.nearlySortedArray, nearlySortedArray);
        doSort(Method.descendingArray, descendingArray);

        returnArrays.add(scrambledArray);
        returnArrays.add(nearlySortedArray);
        returnArrays.add(descendingArray);

        return returnArrays;
    }

    public ArrayList<MyCollection> returnComparisonArraysForTesting(ArrayList<MyCollection> arrays)
    {
        ArrayList<MyCollection> returnArrays = new ArrayList<>();

        for (int i = 0; i < arrays.size(); ++i)
        {
            int[] sortedArrayEntry = arrays.get(i).toArray();
            java.util.Arrays.sort(sortedArrayEntry);
            returnArrays.add(new MyLinkedList().createFromArray(sortedArrayEntry));
        }

        return returnArrays;
    }

    private void printArray(int[] array)
    {
        for (int element : array)
        {
            System.out.println(Integer.toString(element));
        }
    }
}

enum Method
{

    scrambledArray, nearlySortedArray, descendingArray
}
