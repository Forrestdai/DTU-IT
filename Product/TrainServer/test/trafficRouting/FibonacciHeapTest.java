/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficRouting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import trafficRouting.FibonacciHeap.HeapElement;

/**
 *
 * @author JamesFoxes
 */
public class FibonacciHeapTest
{

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
    }

    @Test
    public void testEnqueueSingleElement()
    {
        FibonacciHeap<Integer> myHeap = new FibonacciHeap();

        int integerToAdd = 101;
        myHeap.enqueueElement(integerToAdd, 50);

        assertEquals("Heap size wasn't increased properly", 1, myHeap.getHeapSize());
        assertEquals("Heap either didn't add object corrently, or didn't dequeue it", integerToAdd, (int) myHeap.dequeueMinElement().getNodeContents());
    }

    @Test(timeout = 200)
    public void testEnqueueManyElements()
    {
        int amountOfElementsToAdd = 1000;
        ArrayList<Integer> toAdd = addIntegersToArray(amountOfElementsToAdd);
        FibonacciHeap<Integer> myHeap = addArrayToHeapValues(toAdd);

        assertEquals("Heap size wasn't increased properly", amountOfElementsToAdd, myHeap.getHeapSize());

        for (int i = 0; i < amountOfElementsToAdd; ++i)
        {
            toAdd.remove(myHeap.dequeueMinElement().getNodeContents());
        }

        assertEquals("Heap didn't add or dequeue the corret elements", 0, (int) toAdd.size());
    }

    @Test
    public void testDeQueueMin()
    {
        int amountOfElementsToAdd = 1000;
        ArrayList<Integer> toAdd = addIntegersToArray(amountOfElementsToAdd);
        FibonacciHeap<Integer> myHeap = addArrayToHeapValues(toAdd);

        HeapElement<Integer> firstElement = null;
        HeapElement<Integer> secondElement = null;

        while (myHeap.getHeapSize() > 1)
        {
            firstElement = myHeap.dequeueMinElement();
            secondElement = myHeap.dequeueMinElement();

            if (firstElement != null && secondElement != null)
            {
                assertTrue(firstElement.getNodePriority() <= secondElement.getNodePriority());
            }
        }
    }

    private ArrayList<Integer> addIntegersToArray(int amountOfElementsToAdd)
    {
        ArrayList<Integer> toAdd = new ArrayList<>();
        for (int i = 0; i < amountOfElementsToAdd; ++i)
        {
            toAdd.add((int) (Math.random() * 2000));
        }
        return toAdd;
    }

    private ArrayList<Double> addDoublesToArray(int amountOfElementsToAdd)
    {
        ArrayList<Double> toAdd = new ArrayList<>();
        for (int i = 0; i < amountOfElementsToAdd; ++i)
        {
            toAdd.add(Math.random() * 2000);
        }
        return toAdd;
    }

    private FibonacciHeap<Integer> addArrayToHeapValues(ArrayList<Integer> toAdd)
    {
        FibonacciHeap<Integer> myHeap = new FibonacciHeap();
        for (Integer integer : toAdd)
        {
            myHeap.enqueueElement(integer, Math.random() * 200);
        }
        return myHeap;
    }

    private FibonacciHeap<Integer> addArrayToHeapPriorities(ArrayList<Double> toAdd)
    {
        FibonacciHeap<Integer> myHeap = new FibonacciHeap();
        for (Double number : toAdd)
        {
            myHeap.enqueueElement((int) (Math.random() * 2000), number);
        }
        return myHeap;
    }

    @Test
    public void testMergeNullHeaps()
    {
        int amountOfElementsToAdd = 500;

        ArrayList<Integer> toAdd1 = addIntegersToArray(amountOfElementsToAdd);
        ArrayList<Integer> toAdd2 = addIntegersToArray(amountOfElementsToAdd);

        FibonacciHeap<Integer> heap1 = null;
        FibonacciHeap<Integer> heap2 = null;
        FibonacciHeap<Integer> mergedNullHeap = FibonacciHeap.mergeHeaps(heap1, heap2);

        heap2 = addArrayToHeapValues(toAdd2);
        FibonacciHeap<Integer> mergedFirstNullHeap = FibonacciHeap.mergeHeaps(heap1, heap2);
        assertSame(mergedFirstNullHeap, heap2);

        heap2 = null;
        heap1 = addArrayToHeapValues(toAdd1);
        FibonacciHeap<Integer> mergedSecondNullHeap = FibonacciHeap.mergeHeaps(heap1, heap2);
        assertSame(mergedSecondNullHeap, heap1);

        //size tests
        assertEquals("Null heap had elements", 0, mergedNullHeap.getHeapSize());
        assertEquals("heap with first heap as null, had wrong number of elements", amountOfElementsToAdd, mergedFirstNullHeap.getHeapSize());
        assertEquals("heap with second heap as null, had wrong number of elements", amountOfElementsToAdd, mergedSecondNullHeap.getHeapSize());
    }

    @Test
    public void testMergeSingleElementHeaps()
    {
        FibonacciHeap<Integer> heap1 = new FibonacciHeap();
        FibonacciHeap<Integer> heap2 = new FibonacciHeap();

        heap1.enqueueElement(23, 223.135);
        heap2.enqueueElement(171, 223.134);

        FibonacciHeap<Integer> mergedHeap = FibonacciHeap.mergeHeaps(heap1, heap2);

        assertEquals("meaged heap had wrong number of elements", 2, mergedHeap.getHeapSize());

        FibonacciHeap.HeapElement<Integer> firstElement = mergedHeap.dequeueMinElement();
        FibonacciHeap.HeapElement<Integer> secondElement = mergedHeap.dequeueMinElement();

        assertEquals(223.134, firstElement.getNodePriority(), 0.0001);
        assertEquals(223.135, secondElement.getNodePriority(), 0.0001);

        assertEquals("Wrong merged heap min element", 171, (int) firstElement.getNodeContents());
        assertEquals("Wrong merged heap min element", 23, (int) secondElement.getNodeContents());
    }

    @Test
    public void testMergeManyElementHeaps()
    {
        int amountOfElementsToAdd = 500;

        ArrayList<Double> toAdd1 = addDoublesToArray(amountOfElementsToAdd);
        FibonacciHeap<Integer> heap1 = addArrayToHeapPriorities(toAdd1);

        ArrayList<Double> toAdd2 = addDoublesToArray(amountOfElementsToAdd);
        FibonacciHeap<Integer> heap2 = addArrayToHeapPriorities(toAdd2);

        FibonacciHeap<Integer> mergedHeap = FibonacciHeap.mergeHeaps(heap1, heap2);

        FibonacciHeap.HeapElement<Integer> firstElement;
        FibonacciHeap.HeapElement<Integer> secondElement;

        int i = 0;

        while (mergedHeap.getHeapSize() > 1)
        {
            assertEquals("meaged heap had wrong number of elements", (amountOfElementsToAdd * 2) - i, mergedHeap.getHeapSize());

            firstElement = mergedHeap.dequeueMinElement();
            secondElement = mergedHeap.dequeueMinElement();

            if (firstElement != null && secondElement != null)
            {
                assertTrue(firstElement.getNodePriority() <= secondElement.getNodePriority());
            }

            i += 2;
        }
    }

    @Test
    public void testDecreaseSingleKey()
    {
        FibonacciHeap<Integer> myHeap = new FibonacciHeap();
        myHeap.enqueueElement(101, 50);
        
        assertEquals(1, myHeap.getHeapSize());
        assertEquals(50, myHeap.getMinElement().getNodePriority(), 0.0001);
        
        HeapElement<Integer> element = new HeapElement<>(101, 50);
        myHeap.decreaseKey(element, 41);
        
        assertEquals(1, myHeap.getHeapSize());
        assertEquals(41, myHeap.getMinElement().getNodePriority(), 0.0001);
    }
    
    @Test
    public void testDecreaseKeyOneOfMany()
    {
        FibonacciHeap<Integer> myHeap = new FibonacciHeap();
        myHeap.enqueueElement(301, 50.32);
        
        for (int i = 0; i < 200; i++)
        {
            myHeap.enqueueElement(i, (i * Math.random()) + 1); //all priorities above or equal to 1
        }
        
        assertEquals(201, myHeap.getHeapSize());
        
        HeapElement<Integer> element = new HeapElement<>(301, 50.32);
        myHeap.decreaseKey(element, 0.01);
        
        HeapElement<Integer> elementToTest = myHeap.getMinElement();
        
        assertEquals(0.01, elementToTest.getNodePriority(), 0.0001);
        assertEquals(301, (int) elementToTest.getNodeContents());
    }
    
    @Test
    public void testDecreaseKeyMany()
    {
        FibonacciHeap<Integer> myHeap = new FibonacciHeap();
        Map<Integer, FibonacciHeap.HeapElement<Integer>> heapEntries = new HashMap<>();
        
        for (int i = 0; i < 200; i++)
        {
            heapEntries.put(i, myHeap.enqueueElement(i, (Math.random() * 1000) + 1000));
        }
        
        for (Map.Entry<Integer, HeapElement<Integer>> entrySet : heapEntries.entrySet())
        {
            FibonacciHeap.HeapElement<Integer> toChange = heapEntries.get(entrySet.getKey());
            myHeap.decreaseKey(toChange, (Math.random() * 100) + 100);
        }
        
        for (int i = 0; i < myHeap.getHeapSize(); ++i)
        {
            assertTrue(myHeap.dequeueMinElement().getNodePriority() < 1000);
        }
    }
    
    @Test
    public void testGetMinElement()
    {

    }

    @Test
    public void testIsEmpty()
    {

    }

    @Test
    public void testGetHeapSize()
    {

    }

}
