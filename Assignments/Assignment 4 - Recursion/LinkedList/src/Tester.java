import Sort.SelectionSort;
import java.util.ArrayList;
import java.util.Comparator;
import linkedlist.MyLinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author JamesFoxes
 */
public class Tester
{

    ArrayList<Integer> arrayToSearch;
    ArrayList<Long> timeArray;
    long startTime;
    int arraySize = 7000000;
    private MyLinkedList<Integer> linkedList;

    public static void main(String[] args)
    {
        new Tester();
    }
    

    public Tester()
    {
        timeArray = new ArrayList<>();
        linkedList = new MyLinkedList<>();
;

        setUpArray();
        for (int i = 0; i < 1000; i++)
        {
            test();
        }

        printTime();
    }

    private void setUpArray()
    {
        /*
         arrayToSearch = new ArrayList<>();
         for (int i = 0; i < arraySize; ++i)
         {
         arrayToSearch.add(i);
         }
         */

        for (int i = 0; i < arraySize; i++)
        {
            linkedList.insertElementIterative((int) (Math.random() * arraySize), 0);
        }

        //arrayToSearch.sort(Comparator.naturalOrder());

    }

    public void test()
    {
        
        startTimer();
        linkedList.findElementIndexIterative((int) (Math.random() * arraySize));
        stopTimer();
        
        /*
        BinarySearch search = new BinarySearch(arrayToSearch);

        startTimer();
        int index = search.getIndexIterative((int) (Math.random() * arraySize));
        stopTimer();
                
                */
    }

    private void startTimer()
    {
        startTime = System.nanoTime();
    }

    private void stopTimer()
    {
        timeArray.add(System.nanoTime() - startTime);
    }

    private void printTime()
    {
        long totalTimes = 0;
        double averageTimeMs = 0;
        for (Long time : timeArray)
        {
            totalTimes += time;
        }
        totalTimes = totalTimes / timeArray.size();
        averageTimeMs = ((double) totalTimes) / 1000000;

        System.out.println("Average time: " + averageTimeMs + " ms over " + timeArray.size() + " runs with " + arraySize + " elements.");
    }
}
