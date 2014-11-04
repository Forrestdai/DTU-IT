/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sort;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author JamesFoxes
 */
public class RecursiveSelectionSortTest
{
    
    public RecursiveSelectionSortTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    @Test
    public void testSort()
    {
        ArrayList<Integer> array = new ArrayList<>();
        for (int i = 0; i < 10; i++)
        {
            array.add((int) (Math.random() * 1000));
        }
        
        RecursiveSelectionSort sorter = new RecursiveSelectionSort(array);
        
        sorter.sort();
    }
    
}
