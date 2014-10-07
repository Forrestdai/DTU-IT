package Sorting;

import collection.MyCollection;

public class InsertionSort implements Sortable
{

    private MyCollection arrayToSearch;
    private int currentSortIndex;
    private int currentInsertElementID;

    @Override
    public MyCollection sort(MyCollection arrayToSort)
    {
        arrayToSearch = arrayToSort;
        currentSortIndex = 1;
        for (; currentSortIndex < arrayToSearch.size(); ++currentSortIndex)
        {
            shiftElements();
        }
        return arrayToSearch;
    }

    private void shiftElements()
    {
        currentInsertElementID = currentSortIndex;
        boolean keepShifting = isCurrentElementSmallerThanPrevious();

        while (keepShifting && currentInsertElementID > 0)
        {
            keepShifting = isCurrentElementSmallerThanPrevious();

            if (keepShifting)
            {
                shiftOneElementDown();
                --currentInsertElementID;
            }
        }
    }

    private boolean isCurrentElementSmallerThanPrevious()
    {
        int previousElement = arrayToSearch.get(currentInsertElementID - 1).getContents();
        int currentElement = arrayToSearch.get(currentInsertElementID).getContents();
        return currentElement < previousElement;
    }

    private void shiftOneElementDown()
    {
        int smallElementPlaceholder = arrayToSearch.get(currentInsertElementID).getContents();
        int largeELementPlaceholder = arrayToSearch.get(currentInsertElementID - 1).getContents();
        
        arrayToSearch.update(largeELementPlaceholder, currentInsertElementID);
        arrayToSearch.update(smallElementPlaceholder, currentInsertElementID - 1);
    }
}
