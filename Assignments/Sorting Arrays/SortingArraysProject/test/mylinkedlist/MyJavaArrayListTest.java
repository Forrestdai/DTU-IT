/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mylinkedlist;

import collection.MyArrayList;
import collection.MyCollection;
import mylinkedlist.MyJavaArrayListTest.OperationType;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author James
 */
public class MyJavaArrayListTest
{

    MyCollection testList;

    @Before
    public void setUp()
    {
        testList = new MyArrayList();
        initializeIncrementingList();
    }

    private void initializeIncrementingList()
    {
        for (int i = 0; i < 10; i++)
        {
            testList.add(i, i);
        }
    }
    
    private void mySizeAssertion(int expected)
    {
        assertEquals(expected, testList.size());
    }
    
    @Test
    public void testAddFirst()
    {
        mySizeAssertion(10);
        int dataToAdd = 5;
        testList.addFirst(dataToAdd);
        assertEquals(dataToAdd, testList.getFirst().getContents());
        mySizeAssertion(11);
    }

    @Test
    public void testAddLast()
    {
        mySizeAssertion(10);
        int dataToAdd = 5;
        testList.addLast(dataToAdd);
        assertEquals(dataToAdd, testList.getLast().getContents());
        mySizeAssertion(11);
    }

    @Test
    public void testRemoveFirst()
    {
        assertEquals(0, testList.getFirst().getContents());
        testList.removeFirst();
        assertEquals(1, testList.getFirst().getContents());
    }

    @Test
    public void testRemoveLast()
    {
        assertEquals(9, testList.getLast().getContents());
        testList.removeLast();
        assertEquals(8, testList.getLast().getContents());
    }

    @Test
    public void testGetFirst()
    {
        assertEquals(0, testList.getFirst().getContents());
    }

    @Test
    public void testGetLast()
    {
        assertEquals(9, testList.getLast().getContents());
    }

    @Test
    public void testAdd()
    {
        mySizeAssertion(10);
        int dataToAdd = 5;
        testList.add(dataToAdd, 5);
        mySizeAssertion(11);
        
        assertEquals(5, testList.get(5).getContents());
        assertEquals(5, testList.get(6).getContents());
        assertEquals(9, testList.get(10).getContents());
    }

    @Test
    public void testUpdate()
    {
        mySizeAssertion(10);
        int dataToAdd = 5;
        testList.update(dataToAdd, 1);
        mySizeAssertion(10);
        
        assertEquals(5, testList.get(1).getContents());
    }

    @Test
    public void testRemove()
    {
        mySizeAssertion(10);
        testList.remove(2);
        mySizeAssertion(9);
        
        assertEquals(1, testList.get(1).getContents());
        assertEquals(3, testList.get(2).getContents());
        assertEquals(4, testList.get(3).getContents());
    }

    @Test
    public void testSizeSimpleAdd()
    {
        int amountOfElementsToAdd = 42;

        for (int i = 0; i < amountOfElementsToAdd; ++i)
        {
            testList.addLast(i);
        }
        assertEquals(amountOfElementsToAdd + 10, testList.size());
    }

    @Test
    public void testSizeRandomOperations()
    {
        int amountOfOperations = 42;
        int amountOfElements = 0;

        for (int i = 0; i < amountOfOperations; ++i)
        {
            switch (chooseRandomOperation())
            {
                case addFirst:
                    ++amountOfElements;
                    testList.addFirst(i);
                    break;
                case addLast:
                    ++amountOfElements;
                    testList.addLast(i);
                    break;
            }
        }

        amountOfOperations = 20;

        for (int i = 0; i < amountOfOperations; ++i)
        {
            switch (chooseRandomOperation())
            {
                case addFirst:
                    ++amountOfElements;
                    testList.addFirst(i);
                    break;
                case addLast:
                    ++amountOfElements;
                    testList.addLast(i);
                    break;
                case removeLast:
                    --amountOfElements;
                    testList.removeLast();
                    break;
                case removeFirst:
                    --amountOfElements;
                    testList.removeFirst();
                    break;
            }
        }

        assertEquals(amountOfElements + 10, testList.size());
    }

    private OperationType chooseRandomOperation()
    {
        OperationType[] types = OperationType.values();
        int randomInt = (int) Math.floor(3 * Math.random());
        return types[randomInt];
    }

    enum OperationType
    {

        addFirst, addLast, removeLast, removeFirst
    }

}
