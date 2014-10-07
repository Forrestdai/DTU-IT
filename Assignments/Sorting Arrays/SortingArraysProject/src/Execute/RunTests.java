package Execute;

import efficiency.CollectionEfficiencyTest;
import efficiency.SortingEfficiencyTest;

public class RunTests
{

    public static void main(String[] args)
    {
        System.err.println("SORTING");
        //new SortingEfficiencyTest().runTest();
        System.err.println("");
        System.err.println("COLLECTION");
        new CollectionEfficiencyTest().runTest();
    }
}
