/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Sorting;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author James
 */
public class InsertionSortFSMTest
{
    
    public InsertionSortFSMTest()
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

    /**
     * Test of sort method, of class InsertionSortFSM.
     */
    @Test
    public void testInsertionFSMSort()
    {
        System.out.println("Insertion Sort FSM Test");
        int[] arrayToSort = {100, 474, 233, 443, 449, 452, 128, 362, 207, 420, 482, 260, 284, 436, 66, 485, 317, 143, 411, 61, 315, 306, 79, 464, 362, 459, 63, 229, 444, 61, 180, 333, 199, 456, 189, 228, 338, 119, 290, 300, 391, 269, 173, 58, 405, 175, 267, 158, 91, 4, 174, 470, 31, 489, 336, 370, 117, 365, 221, 73, 428, 181, 185, 281, 410, 372, 186, 204, 183, 18, 165, 109, 405, 119, 213, 329, 407, 309, 199, 218, 416, 482, 62, 134, 354, 109, 119, 191, 320, 257, 389, 181, 280, 237, 331, 174, 217, 372, 258, 474};
        InsertionSortFSM instance = new InsertionSortFSM();
        int[] expResult = {4, 18, 31, 58, 61, 61, 62, 63, 66, 73, 79, 91, 100, 109, 109, 117, 119, 119, 119, 128, 134, 143, 158, 165, 173, 174, 174, 175, 180, 181, 181, 183, 185, 186, 189, 191, 199, 199, 204, 207, 213, 217, 218, 221, 228, 229, 233, 237, 257, 258, 260, 267, 269, 280, 281, 284, 290, 300, 306, 309, 315, 317, 320, 329, 331, 333, 336, 338, 354, 362, 362, 365, 370, 372, 372, 389, 391, 405, 405, 407, 410, 411, 416, 420, 428, 436, 443, 444, 449, 452, 456, 459, 464, 470, 474, 474, 482, 482, 485, 489};
        int[] result = instance.sort(arrayToSort);
        assertArrayEquals(expResult, result);
    }
    
}
