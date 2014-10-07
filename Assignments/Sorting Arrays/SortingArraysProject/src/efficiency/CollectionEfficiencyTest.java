/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package efficiency;

import collection.MyJavaLinkedList;
import collection.*;

/**
 *
 * @author JamesFoxes
 */
public class CollectionEfficiencyTest
{

    CollectionTestResults testResults;
    int numberOfElements;
    int timesToRun = 20;

    private long startTime = 0;

    public void runTest()
    {
        System.out.println("---   My LinkedList efficiency test   ---");
        System.out.println("-5000 elements-");
        runAll(timesToRun, 5000, CollectionType.MyLinkedList);
        printResults();
        
        System.out.println("-10000 elements-");
        runAll(timesToRun, 10000, CollectionType.MyLinkedList);
        printResults();
        
        System.out.println("-20000 elements-");
        runAll(timesToRun, 20000, CollectionType.MyLinkedList);
        printResults();
        
        System.out.println("-40000 elements-");
        runAll(timesToRun, 40000, CollectionType.MyLinkedList);
        printResults();

        System.out.println("---   Java LinkedList efficiency test   ---");
        System.out.println("-5000 elements-");
        runAll(timesToRun, 5000, CollectionType.JavaLinkedList);
        printResults();
        
        System.out.println("-10000 elements-");
        runAll(timesToRun, 10000, CollectionType.JavaLinkedList);
        printResults();
        
        System.out.println("-20000 elements-");
        runAll(timesToRun, 20000, CollectionType.JavaLinkedList);
        printResults();
        
        System.out.println("-40000 elements-");
        runAll(timesToRun, 40000, CollectionType.JavaLinkedList);
        printResults();
        
        System.out.println("---   Java ArrayList efficiency test   ---");
        System.out.println("-5000 elements-");
        runAll(timesToRun, 5000, CollectionType.Array);
        printResults();
        
        System.out.println("-10000 elements-");
        runAll(timesToRun, 10000, CollectionType.Array);
        printResults();
        
        System.out.println("-20000 elements-");
        runAll(timesToRun, 20000, CollectionType.Array);
        printResults();
        
        System.out.println("-40000 elements-");
        runAll(timesToRun, 40000, CollectionType.Array);
        printResults();
        
    }
    
    private void runAll(int numberOfTimes, int numberOfElements, CollectionType type)
    {
        this.numberOfElements = numberOfElements;
        for (int i = 0; i < numberOfTimes; i++)
        {
            testResults = new CollectionTestResults(numberOfElements);
            runAllOnce(type);
            testResults.incrementTimesRun();
        }
    }
    
    private void runAllOnce(CollectionType type)
    {
        testAdd(type);
        testGet(type);
        testAddFirst(type);
        testAddLast(type);
    }

    public void testAdd(CollectionType type)
    {
        MyCollection toTest = createCollection(type);

        for (int i = 0; i < numberOfElements; ++i)
        {
            startTimer();
            toTest.add(i, ((int) (Math.random() * i)));
            stopTimerAndSaveElapsedTime(Operation.add);
        }
    }
    
    public void testGet(CollectionType type)
    {
        MyCollection toTest = createCollection(type);

        for (int i = 0; i < numberOfElements; ++i)
        {
            toTest.add(i, i);
        }
        
        for (int i = 0; i < numberOfElements; ++i)
        {
            startTimer();
            toTest.get(i);
            stopTimerAndSaveElapsedTime(Operation.get);
        }
    }
    
    public void testAddFirst(CollectionType type)
    {
        MyCollection toTest = createCollection(type);

        for (int i = 0; i < numberOfElements; ++i)
        {
            startTimer();
            toTest.addFirst(i);
            stopTimerAndSaveElapsedTime(Operation.addFirst);
        }
    }
    
    public void testAddLast(CollectionType type)
    {
        MyCollection toTest = createCollection(type);

        for (int i = 0; i < numberOfElements; ++i)
        {
            startTimer();
            toTest.addLast(i);
            stopTimerAndSaveElapsedTime(Operation.addLast);
        }
    }

    private MyCollection createCollection(CollectionType type)
    {
        MyCollection testCollection;
        switch (type)
        {
            case MyLinkedList:
                testCollection = new MyLinkedList();
                break;
            case JavaLinkedList:
                testCollection = new MyJavaLinkedList();
                break;
            case Array:
                testCollection = new MyArrayList();
                break;
            default:
                testCollection = null;
                System.err.println("ERROR: createCollection!");
                break;
        }
        return testCollection;
    }

    private void startTimer()
    {
        startTime = System.nanoTime();
    }

    private void stopTimerAndSaveElapsedTime(Operation operation)
    {
        switch (operation)
        {
            case add:
                testResults.addTime += System.nanoTime() - startTime;
                break;
            case get:
                testResults.getTime += System.nanoTime() - startTime;
                break;
            case addFirst:
                testResults.addFirstTime += System.nanoTime() - startTime;
                break;
            case addLast:
                testResults.addLastTime += System.nanoTime() - startTime;
                break;
        }
    }

    private void printResults()
    {
        testResults.printAverageTimes();
    }
}

enum Operation
{

    add, get, addFirst, addLast
}
