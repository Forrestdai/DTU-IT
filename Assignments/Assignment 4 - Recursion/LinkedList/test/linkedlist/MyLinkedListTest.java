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
        myList.insertElement(12348, 0);

        assertEquals(0, myList.findElementIndex(12348));
        assertEquals(-1, myList.findElementIndex(123));
    }

    @Test
    public void testInsertAndFindManyRandomElements()
    {

        int numberOfElements = 500;

        for (int i = 0; i < numberOfElements; ++i)
        {
            int index = (int) ((Math.random() * numberOfElements) - (numberOfElements / 10));
            myList.insertElement(i, index);

            if (index < 0)
            {
                index = 0;
            }

            int size = myList.size();

            if (index > size - 1)
            {
                index = size - 1;
            }

            assertEquals(index, myList.findElementIndex(i));
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
            myList.insertElement(value, index);
            assertEquals(index, myList.findElementIndex(value));
        }
    }

    @Test
    public void testInsertLowIndexElement()
    {
        myList.insertElement(6, -5);    //insert LOW index
        assertEquals(0, myList.findElementIndex(6));
    }

    @Test
    public void testInsertHighIndexElement()
    {
        myList.insertElement(12, 5);    //insert HIGH index
        assertEquals(0, myList.findElementIndex(12));
    }

    @Test
    public void testGetElementsSimple()
    {
        for (int i = 0; i < 10; ++i)
        {
            int index = (int) ((Math.random() * 10) - 1);
            myList.insertElement(i, index);
        }
        
        myList.insertElement(12, 6);
        assertEquals((int) 12, (int) myList.getElement(6));
        myList.insertElement(20, 50);
        assertEquals((int) 20, (int) myList.getElement(myList.size()-1));
        myList.insertElement(28, -2);
        assertEquals((int) 28, (int) myList.getElement(0));
    }

}
