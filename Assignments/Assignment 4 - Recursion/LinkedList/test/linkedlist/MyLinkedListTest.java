/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedlist;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author James
 */
public class MyLinkedListTest
{

    MyLinkedList<Integer> myList;

    @Before
    public void setUp()
    {
        myList = new MyLinkedList<>();
    }

    @Test
    public void testSimpleInsertElement()
    {
        myList.insertElementRecursive(12348, 0);

        assertEquals(0, myList.findElementIndexRecursive(12348));
        assertEquals(-1, myList.findElementIndexRecursive(123));
        
        myList.insertElementIterative(516, 0);
        assertEquals(0, myList.findElementIndexIterative(516));
        assertEquals(1, myList.findElementIndexIterative(12348));
        assertEquals(-1, myList.findElementIndexIterative(123));
    }

    @Test
    public void testInsertAndFindManyRandomElements()
    {

        int numberOfElements = 500;

        for (int i = 0; i < numberOfElements; ++i)
        {
            int index = (int) ((Math.random() * numberOfElements) - (numberOfElements / 10));
            myList.insertElementRecursive(i, index);

            if (index < 0)
            {
                index = 0;
            }

            int size = myList.sizeRecursive();

            if (index > size - 1)
            {
                index = size - 1;
            }

            assertEquals(index, myList.findElementIndexRecursive(i));
        }
        
        myList = new MyLinkedList<>();
        for (int i = 0; i < numberOfElements; ++i)
        {
            int index = (int) ((Math.random() * numberOfElements) - (numberOfElements / 10));
            myList.insertElementIterative(i, index);

            if (index < 0)
            {
                index = 0;
            }

            int size = myList.sizeRecursive();

            if (index > size - 1)
            {
                index = size - 1;
            }

            assertEquals(index, myList.findElementIndexIterative(i));
        }
    }

    @Test
    public void testInsertManySequentialElements()
    {
        int numberOfElements = 500;

        for (int i = 0; i < numberOfElements; ++i)
        {
            int index = i;
            int value = i;
            myList.insertElementRecursive(value, index);
            assertEquals(index, myList.findElementIndexRecursive(value));
        }
        
        myList = new MyLinkedList<>();
        
        for (int i = 0; i < numberOfElements; ++i)
        {
            int index = i;
            int value = i;
            myList.insertElementIterative(value, index);
            assertEquals(index, myList.findElementIndexIterative(value));
        }
    }

    @Test
    public void testInsertLowIndexElement()
    {
        myList.insertElementRecursive(6, -5);    //insert LOW index
        assertEquals(0, myList.findElementIndexRecursive(6));
        myList = new MyLinkedList<>();
        myList.insertElementIterative(6, -5);    //insert HIGH index
        assertEquals(0, myList.findElementIndexIterative(6));
    }

    @Test
    public void testInsertHighIndexElement()
    {
        myList.insertElementRecursive(12, 5);    //insert HIGH index
        assertEquals(0, myList.findElementIndexRecursive(12));
        myList = new MyLinkedList<>();
        myList.insertElementIterative(12, 5);    //insert HIGH index
        assertEquals(0, myList.findElementIndexIterative(12));
    }

    @Test
    public void testGetElementsSimple()
    {
        for (int i = 0; i < 10; ++i)
        {
            int index = (int) ((Math.random() * 10) - 1);
            myList.insertElementRecursive(i, index);
        }
        
        myList.insertElementRecursive(12, 6);
        assertEquals((int) 12, (int) myList.getElementRecursive(6));
        assertEquals((int) 12, (int) myList.getElementIterative(6));
        myList.insertElementRecursive(20, 50);
        assertEquals((int) 20, (int) myList.getElementRecursive(myList.sizeRecursive()-1));
        assertEquals((int) 20, (int) myList.getElementIterative(myList.sizeRecursive()-1));
        myList.insertElementRecursive(28, -2);
        assertEquals((int) 28, (int) myList.getElementRecursive(0));
        assertEquals((int) 28, (int) myList.getElementIterative(0));
    }
    
    

}
