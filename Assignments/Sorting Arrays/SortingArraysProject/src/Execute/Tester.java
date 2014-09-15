package Execute;

import Helpers.*;
import Sorting.*;
import java.util.ArrayList;

public class Tester
{

    private final int numberOfTimesToRun;
    private final int arraySize;
    private final int testFromZeroTo;

    private int[] scrambledArray;
    private int[] nearlySortedArray;
    private int[] descendingArray;

    private volatile long startTime;
    private volatile long[] elapsedTimes;

    private Sortable sortMethod;

    public Tester(arrayProperties arrayProperties)
    {
        elapsedTimes = new long[3];

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
        scrambledArray = new int[arraySize];
        for (int i = 0; i < arraySize; ++i)
        {
            scrambledArray[i] = (int) (Math.random() * testFromZeroTo);
        }
    }

    private void createNearlySortedArray()
    {
        nearlySortedArray = new int[arraySize];
        for (int i = 0; i < arraySize; ++i)
        {
            nearlySortedArray[i] = i;
        }
        int chunkSize = 4;
        for (int i = 0; i < arraySize; i += chunkSize)
        {
            int cap = Math.min(chunkSize + i, arraySize);
            for(int j = i; j < (cap - 1); ++j)
            {
                int swapTarget = (int) (Math.random() * (cap - j)) + j;
                swapElements(nearlySortedArray, j, swapTarget);
            }
        }
    }
    
    private void swapElements(int[] array, int from, int to)
    {
        int placeholder = array[to];
        array[to] = array[from];
        array[from] = placeholder;
    }

    private void createDescendingArray()
    {
        descendingArray = new int[arraySize];
        for (int i = 0; i < arraySize; ++i)
        {
            descendingArray[i] = arraySize - i;
        }
    }

    private void doSort(Method method, int[] arrayToSort)
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

    public ArrayList<int[]> returnSortedArraysForTesting(Sortable sortMethod)
    {
        this.sortMethod = sortMethod;
        ArrayList<int[]> returnArrays = new ArrayList<>();
                
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
    
    public ArrayList<int[]> returnComparisonArraysForTesting(ArrayList<int[]> arrays)
    {
        ArrayList<int[]> returnArrays = new ArrayList<>();
        
        java.util.Arrays.sort(arrays.get(0));
        java.util.Arrays.sort(arrays.get(1));
        java.util.Arrays.sort(arrays.get(2));
        
        returnArrays.add(arrays.get(0));
        returnArrays.add(arrays.get(1));
        returnArrays.add(arrays.get(2));
        
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
