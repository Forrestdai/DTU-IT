/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package efficiency;

import Helpers.arrayProperties;
import Sorting.BuiltInQuickSort;
import Sorting.InsertionSortFSM;
import Sorting.SelectionSort;
import Sorting.Sortable;
import collection.CollectionType;

/**
 *
 * @author JamesFoxes
 */
public class SortingEfficiencyTest
{

    public void runTest()
    {
        System.out.println("Selection sorting --->");
        runOneSortMethod(new SelectionSort());
        System.out.println("Insertion sorting --->");
        runOneSortMethod(new InsertionSortFSM());
        System.out.println("Quick sorting --->");
        runOneSortMethod(new BuiltInQuickSort());
    }
    
    private void runOneSortMethod(Sortable sortingMethod)
    {
        System.out.println("My LinkedList");
        executeTestsAndPrintAverageTime(createTestEnvironment(500), sortingMethod, CollectionType.MyLinkedList);
        executeTestsAndPrintAverageTime(createTestEnvironment(1000), sortingMethod, CollectionType.MyLinkedList);
        executeTestsAndPrintAverageTime(createTestEnvironment(2000), sortingMethod, CollectionType.MyLinkedList);
        executeTestsAndPrintAverageTime(createTestEnvironment(4000), sortingMethod, CollectionType.MyLinkedList);
        System.out.println("Java LinkedList");
        executeTestsAndPrintAverageTime(createTestEnvironment(500), sortingMethod, CollectionType.JavaLinkedList);
        executeTestsAndPrintAverageTime(createTestEnvironment(1000), sortingMethod, CollectionType.JavaLinkedList);
        executeTestsAndPrintAverageTime(createTestEnvironment(2000), sortingMethod, CollectionType.JavaLinkedList);
        executeTestsAndPrintAverageTime(createTestEnvironment(4000), sortingMethod, CollectionType.JavaLinkedList);
        System.out.println("Java ArrayList");
        executeTestsAndPrintAverageTime(createTestEnvironment(500), sortingMethod, CollectionType.Array);
        executeTestsAndPrintAverageTime(createTestEnvironment(1000), sortingMethod, CollectionType.Array);
        executeTestsAndPrintAverageTime(createTestEnvironment(2000), sortingMethod, CollectionType.Array);
        executeTestsAndPrintAverageTime(createTestEnvironment(4000), sortingMethod, CollectionType.Array);
    }
    
    private arrayProperties createTestEnvironment(int arraySize)
    {
        arrayProperties test = new arrayProperties();
        test.sizeOfSortArray = arraySize;
        test.numberOfTimesToRun = (10000 / arraySize);
        test.testFromZeroTo = arraySize * 5;
        return test;
    }
    
    private void executeTestsAndPrintAverageTime(arrayProperties arrayProperties, Sortable sortingMethod, CollectionType collectionType)
    {
        long[] executionTimes = new SortingTester(arrayProperties, collectionType).run(sortingMethod);

        long averageTime = executionTimes[0] / arrayProperties.numberOfTimesToRun;
        double averageTimeMiliseconds = ((double) averageTime) / 1000000;
        System.out.println("Scrambled arrays: Average Runtime of " + averageTimeMiliseconds + "ms, average over " + arrayProperties.numberOfTimesToRun + " runs with " + arrayProperties.sizeOfSortArray + " elements in each array.");

        averageTime = executionTimes[1] / arrayProperties.numberOfTimesToRun;
        averageTimeMiliseconds = ((double) averageTime) / 1000000;
        System.out.println("Nearly Sorted arrays: Average Runtime of " + averageTimeMiliseconds + "ms, average over " + arrayProperties.numberOfTimesToRun + " runs with " + arrayProperties.sizeOfSortArray + " elements in each array.");

        averageTime = executionTimes[2] / arrayProperties.numberOfTimesToRun;
        averageTimeMiliseconds = ((double) averageTime) / 1000000;
        System.out.println("Descending arrays: Average Runtime of " + averageTimeMiliseconds + "ms, average over " + arrayProperties.numberOfTimesToRun + " runs with " + arrayProperties.sizeOfSortArray + " elements in each array.");
        System.out.println("------------");
    }
}
