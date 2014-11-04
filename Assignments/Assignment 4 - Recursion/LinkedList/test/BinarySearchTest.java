/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.Comparator;
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
public class BinarySearchTest
{

    public BinarySearchTest()
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
     * Test of search method, of class BinarySearch.
     */
    @Test
    public void testSearch()
    {
        ArrayList<Integer> arrayToSearch = new ArrayList<>();
        for (int i = 0; i < 1000; i++)
        {
            arrayToSearch.add((int) (Math.random() * 10000) + 500);
        }

        for (int i = 0; i < 200; i++)
        {
            arrayToSearch.add((int) (Math.random() * 400));
        }
        arrayToSearch.add(454);
        arrayToSearch.sort(Comparator.naturalOrder());
        BinarySearch search = new BinarySearch(arrayToSearch);

        int index = search.getIndexRecursive(454);
        assertEquals(200, index);
    }

}
