package Execute;

import efficiency.SortingTester;
import Helpers.ArrayProperties;
import Sorting.InsertionSort;
import Sorting.InsertionSortFSM;
import Sorting.SelectionSort;
import collection.CollectionType;
import collection.MyCollection;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class TesterTest
{
    @Test
    public void SelectionSortTest()
    {
        ArrayProperties shortTest = new ArrayProperties();
        SortingTester instance = new SortingTester(shortTest, CollectionType.MyLinkedList);
        shortTest.sizeOfSortArray = 1000;
        shortTest.numberOfTimesToRun = 1;
        shortTest.testFromZeroTo = 5000;
        
        ArrayList<MyCollection> result = instance.returnSortedArraysForTesting(new SelectionSort());
        ArrayList<MyCollection> expResult = instance.returnComparisonArraysForTesting(result);
        
        assertArrayEquals(expResult.get(0).toArray(), result.get(0).toArray());
        assertArrayEquals(expResult.get(1).toArray(), result.get(1).toArray());
        assertArrayEquals(expResult.get(2).toArray(), result.get(2).toArray());
    }
    
    @Test
    public void InsertionSortTest()
    {
        ArrayProperties shortTest = new ArrayProperties();
        SortingTester instance = new SortingTester(shortTest, CollectionType.MyLinkedList);
        shortTest.sizeOfSortArray = 1000;
        shortTest.numberOfTimesToRun = 1;
        shortTest.testFromZeroTo = 5000;
        
        ArrayList<MyCollection> result = instance.returnSortedArraysForTesting(new InsertionSort());
        ArrayList<MyCollection> expResult = instance.returnComparisonArraysForTesting(result);
        
        assertArrayEquals(expResult.get(0).toArray(), result.get(0).toArray());
        assertArrayEquals(expResult.get(1).toArray(), result.get(1).toArray());
        assertArrayEquals(expResult.get(2).toArray(), result.get(2).toArray());
    }
    
    @Test
    public void InsertionSortFSMTest()
    {
        ArrayProperties shortTest = new ArrayProperties();
        SortingTester instance = new SortingTester(shortTest, CollectionType.MyLinkedList);
        shortTest.sizeOfSortArray = 1000;
        shortTest.numberOfTimesToRun = 1;
        shortTest.testFromZeroTo = 5000;
        
        ArrayList<MyCollection> result = instance.returnSortedArraysForTesting(new InsertionSortFSM());
        ArrayList<MyCollection> expResult = instance.returnComparisonArraysForTesting(result);
        
        assertArrayEquals(expResult.get(0).toArray(), result.get(0).toArray());
        assertArrayEquals(expResult.get(1).toArray(), result.get(1).toArray());
        assertArrayEquals(expResult.get(2).toArray(), result.get(2).toArray());
    }
    
}
