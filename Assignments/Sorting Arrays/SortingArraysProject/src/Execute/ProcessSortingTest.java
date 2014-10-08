/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Execute;

import Helpers.ArrayProp;
import Helpers.SortingType;
import collection.CollectionType;
import common.interfaces.ProcessorRequest;
import efficiency.SortingTester;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLongArray;

/**
 *
 * @author James
 */
public class ProcessSortingTest implements ProcessorRequest
{

    private AtomicLongArray array;
    private ArrayProp arrayProperties;
    private SortingType sortingMethod;
    private CollectionType collectionType;
    private AtomicInteger finishedSorts;
    
    public ProcessSortingTest(AtomicLongArray array, ArrayProp arrayProperties, SortingType sortingMethod, CollectionType collectionType, AtomicInteger finishedSorts)
    {
        this.finishedSorts = finishedSorts;
        this.arrayProperties = arrayProperties;
        this.collectionType = collectionType;
        this.sortingMethod = sortingMethod;
        this.array = array;
    }
    
    @Override
    public void process()
    {
        ArrayProp aP = new ArrayProp();
        
        aP.numberOfTimesToRun = 1;
        aP.sizeOfSortArray = arrayProperties.sizeOfSortArray;
        aP.testFromZeroTo = arrayProperties.testFromZeroTo;
        
        long[] executionTimes = new SortingTester(aP, collectionType).run(sortingMethod);
        
        array.addAndGet(0, executionTimes[0]);
        array.addAndGet(1, executionTimes[1]);
        array.addAndGet(2, executionTimes[2]);
        
        finishedSorts.incrementAndGet();
    }
}
