package Execute;

import efficiency.CollectionEfficiencyTest;
import efficiency.SortingEfficiencyTest;
import Assignment6.*;

public class RunTests
{

    public static void main(String[] args)
    {
        new Assignment6().Assignment6("2-3*(4/5+6)*((7-8)/9)");
        new Assignment6().Assignment6("2-3*(4/5+6)");
        new Assignment6().Assignment6("4*(7-5)");
        new Assignment6().Assignment6("(3+2)*(9-7)");
        new Assignment6().Assignment6("(7-0)*4");
        new Assignment6().Assignment6("(6/0)*(6-1)");
        new Assignment6().Assignment6("8/9+3*(6+2)");
        new Assignment6().Assignment6("(1+2)*(9-3)+2-(3*(4/5+6)-(3+2)*(2-3*(4/5+6)*((7-8)/9)-(9-7))/8/9)+3*(6+2)");
        
        
       
        /*
        System.err.println("SORTING");
        new SortingEfficiencyTest().runTest();
        System.err.println("");
        System.err.println("COLLECTION");
        new CollectionEfficiencyTest().runTest();
        */
    }
}
