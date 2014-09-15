package Execute;

import Helpers.*;
import Sorting.*;

public class RunTests
{

    public static void main(String[] args)
    {
        arrayProperties veryShortTest = new arrayProperties();
        veryShortTest.sizeOfSortArray = 100;
        veryShortTest.numberOfTimesToRun = 5000000; //5.000.000
        veryShortTest.testFromZeroTo = 500;

        arrayProperties shortTest = new arrayProperties();
        shortTest.sizeOfSortArray = 1000;
        shortTest.numberOfTimesToRun = 50000; //50.000
        shortTest.testFromZeroTo = 5000;

        arrayProperties longTest = new arrayProperties();
        longTest.sizeOfSortArray = 10000;
        longTest.numberOfTimesToRun = 500; //500
        longTest.testFromZeroTo = 50000;

        arrayProperties veryLongTest = new arrayProperties();
        veryLongTest.sizeOfSortArray = 100000;
        veryLongTest.numberOfTimesToRun = 50; //50
        veryLongTest.testFromZeroTo = 500000;

        arrayProperties crazyLongTest = new arrayProperties();
        crazyLongTest.sizeOfSortArray = 200000;
        crazyLongTest.numberOfTimesToRun = 20; //20
        crazyLongTest.testFromZeroTo = 1000000;

        System.out.println("Selection Sorting Method:");
        System.out.println("");

        executeTestsAndPrintAverageTime(veryShortTest, new SelectionSort());
        executeTestsAndPrintAverageTime(shortTest, new SelectionSort());
        executeTestsAndPrintAverageTime(longTest, new SelectionSort());
        executeTestsAndPrintAverageTime(veryLongTest, new SelectionSort());
        executeTestsAndPrintAverageTime(crazyLongTest, new SelectionSort());

        System.out.println("");
        System.out.println("Insertion Sorting Method:");
        System.out.println("");

        executeTestsAndPrintAverageTime(veryShortTest, new InsertionSort());
        executeTestsAndPrintAverageTime(shortTest, new InsertionSort());
        executeTestsAndPrintAverageTime(longTest, new InsertionSort());
        executeTestsAndPrintAverageTime(veryLongTest, new InsertionSort());
        executeTestsAndPrintAverageTime(crazyLongTest, new InsertionSort());

        System.out.println("");
        System.out.println("Builtin Quicksort Sorting Method:");
        System.out.println("");

        executeTestsAndPrintAverageTime(veryShortTest, new BuiltInQuickSort());
        executeTestsAndPrintAverageTime(shortTest, new BuiltInQuickSort());
        executeTestsAndPrintAverageTime(longTest, new BuiltInQuickSort());
        executeTestsAndPrintAverageTime(veryLongTest, new BuiltInQuickSort());
        executeTestsAndPrintAverageTime(crazyLongTest, new BuiltInQuickSort());

        System.out.println("");
        System.out.println("Second Insertion Sorting Method:");
        System.out.println("");

        executeTestsAndPrintAverageTime(veryShortTest, new InsertionSortFSM());
        executeTestsAndPrintAverageTime(shortTest, new InsertionSortFSM());
        executeTestsAndPrintAverageTime(longTest, new InsertionSortFSM());
        executeTestsAndPrintAverageTime(veryLongTest, new InsertionSortFSM());
        executeTestsAndPrintAverageTime(crazyLongTest, new InsertionSortFSM());
    }

    public static void executeTestsAndPrintAverageTime(arrayProperties arrayProperties, Sortable method)
    {
        long[] executionTimes = new Tester(arrayProperties).run(method);

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
