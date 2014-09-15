package Execute;

import Helpers.arrayProperties;
import Sorting.InsertionSort;
import Sorting.InsertionSortFSM;
import Sorting.SelectionSort;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class TesterTest
{
    @Test
    public void SelectionSortTest()
    {
        arrayProperties shortTest = new arrayProperties();
        Tester instance = new Tester(shortTest);
        shortTest.sizeOfSortArray = 1000;
        shortTest.numberOfTimesToRun = 1;
        shortTest.testFromZeroTo = 5000;
        
        ArrayList<int[]> result = instance.returnSortedArraysForTesting(new SelectionSort());
        ArrayList<int[]> expResult = instance.returnComparisonArraysForTesting(result);
        
        assertArrayEquals(expResult.get(0), result.get(0));
        assertArrayEquals(expResult.get(1), result.get(1));
        assertArrayEquals(expResult.get(2), result.get(2));
    }
    
    @Test
    public void InsertionSortTest()
    {
        arrayProperties shortTest = new arrayProperties();
        Tester instance = new Tester(shortTest);
        shortTest.sizeOfSortArray = 1000;
        shortTest.numberOfTimesToRun = 1;
        shortTest.testFromZeroTo = 5000;
        
        ArrayList<int[]> result = instance.returnSortedArraysForTesting(new InsertionSort());
        ArrayList<int[]> expResult = instance.returnComparisonArraysForTesting(result);
        
        assertArrayEquals(expResult.get(0), result.get(0));
        assertArrayEquals(expResult.get(1), result.get(1));
        assertArrayEquals(expResult.get(2), result.get(2));
    }
    
    @Test
    public void InsertionSortFSMTest()
    {
        arrayProperties shortTest = new arrayProperties();
        Tester instance = new Tester(shortTest);
        shortTest.sizeOfSortArray = 1000;
        shortTest.numberOfTimesToRun = 1;
        shortTest.testFromZeroTo = 5000;
        
        ArrayList<int[]> result = instance.returnSortedArraysForTesting(new InsertionSortFSM());
        ArrayList<int[]> expResult = instance.returnComparisonArraysForTesting(result);
        
        assertArrayEquals(expResult.get(0), result.get(0));
        assertArrayEquals(expResult.get(1), result.get(1));
        assertArrayEquals(expResult.get(2), result.get(2));
    }
    
}
