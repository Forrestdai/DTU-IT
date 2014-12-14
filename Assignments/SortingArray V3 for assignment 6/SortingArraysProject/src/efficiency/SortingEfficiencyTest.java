/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package efficiency;

import Execute.ProcessSortingTest;
import Helpers.ArrayProp;
import Helpers.SortingType;
import collection.CollectionType;
import common.interfaces.ProcessorRequest;
import common.interfaces.Scheduler;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLongArray;
import threading.PersistentExecutorPool;

/**
 *
 * @author JamesFoxes
 */
public class SortingEfficiencyTest
{

    Scheduler threadPool = new PersistentExecutorPool();

    public void runTest()
    {
        System.err.println("Selection sorting --->");
        runOneSortMethod(SortingType.SelectionSort);
        System.err.println("Insertion sorting --->");
        runOneSortMethod(SortingType.InsertionSortFSM);
        System.err.println("Quick sorting --->");
        runOneSortMethod(SortingType.QuickSort);
    }

    private void runOneSortMethod(SortingType sortingMethod)
    {
        System.err.println("My LinkedList");
        executeTestsAndPrintAverageTime(createTestEnvironment(500), sortingMethod, CollectionType.MyLinkedList);
        executeTestsAndPrintAverageTime(createTestEnvironment(1000), sortingMethod, CollectionType.MyLinkedList);
        executeTestsAndPrintAverageTime(createTestEnvironment(2000), sortingMethod, CollectionType.MyLinkedList);
        executeTestsAndPrintAverageTime(createTestEnvironment(4000), sortingMethod, CollectionType.MyLinkedList);
        System.err.println("Java LinkedList");
        executeTestsAndPrintAverageTime(createTestEnvironment(500), sortingMethod, CollectionType.JavaLinkedList);
        executeTestsAndPrintAverageTime(createTestEnvironment(1000), sortingMethod, CollectionType.JavaLinkedList);
        executeTestsAndPrintAverageTime(createTestEnvironment(2000), sortingMethod, CollectionType.JavaLinkedList);
        executeTestsAndPrintAverageTime(createTestEnvironment(4000), sortingMethod, CollectionType.JavaLinkedList);
        System.err.println("Java ArrayList");
        executeTestsAndPrintAverageTime(createTestEnvironment(500), sortingMethod, CollectionType.Array);
        executeTestsAndPrintAverageTime(createTestEnvironment(1000), sortingMethod, CollectionType.Array);
        executeTestsAndPrintAverageTime(createTestEnvironment(2000), sortingMethod, CollectionType.Array);
        executeTestsAndPrintAverageTime(createTestEnvironment(4000), sortingMethod, CollectionType.Array);
    }

    private ArrayProp createTestEnvironment(int arraySize)
    {
        ArrayProp test = new ArrayProp();
        test.sizeOfSortArray = arraySize;
        test.numberOfTimesToRun = (20000 / arraySize);
        test.testFromZeroTo = arraySize * 5;
        return test;
    }

    private void executeTestsAndPrintAverageTime(ArrayProp arrayProperties, SortingType sortingMethod, CollectionType collectionType)
    {
        AtomicLongArray executionTimes = new AtomicLongArray(3);
        AtomicInteger finishedSorts = new AtomicInteger(0);
        for (int i = 0; i < arrayProperties.numberOfTimesToRun; ++i)
        {
            ProcessorRequest requestProcessor = new ProcessSortingTest(executionTimes, arrayProperties, sortingMethod, collectionType, finishedSorts);
            threadPool.schedule(requestProcessor);
        }
        
        while(finishedSorts.get() < arrayProperties.numberOfTimesToRun)
        {
            try
            {
                Thread.sleep(1000);
            } catch (InterruptedException ex)
            {
            }
        }
        
        long[] times = new long[3];
        times[0] = executionTimes.get(0);
        times[1] = executionTimes.get(1);
        times[2] = executionTimes.get(2);

        long averageTime = times[0] / arrayProperties.numberOfTimesToRun;
        double averageTimeMiliseconds = ((double) averageTime) / 1000000;
        System.out.println("Scrambled arrays: Average Runtime of " + averageTimeMiliseconds + "ms, average over " + arrayProperties.numberOfTimesToRun + " runs with " + arrayProperties.sizeOfSortArray + " elements in each array.");

        averageTime = times[1] / arrayProperties.numberOfTimesToRun;
        averageTimeMiliseconds = ((double) averageTime) / 1000000;
        System.out.println("Nearly Sorted arrays: Average Runtime of " + averageTimeMiliseconds + "ms, average over " + arrayProperties.numberOfTimesToRun + " runs with " + arrayProperties.sizeOfSortArray + " elements in each array.");

        averageTime = times[2] / arrayProperties.numberOfTimesToRun;
        averageTimeMiliseconds = ((double) averageTime) / 1000000;
        System.out.println("Descending arrays: Average Runtime of " + averageTimeMiliseconds + "ms, average over " + arrayProperties.numberOfTimesToRun + " runs with " + arrayProperties.sizeOfSortArray + " elements in each array.");
        System.out.println("------------");
    }
}
