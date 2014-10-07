package Sorting;

import collection.MyCollection;

public class SelectionSort implements Sortable
{
    private MyCollection listToSearch;
    private int currentSortedIndex;
    private int currentSmallestElementID;

    @Override
    public MyCollection sort(MyCollection arrayToSort)
    {
        currentSortedIndex = 0;
        listToSearch = arrayToSort;

        for (; currentSortedIndex < listToSearch.size() - 1; ++currentSortedIndex)
        {
            searchSmallestElementID();
            swapElements();
        }

        return listToSearch;
    }

    private void searchSmallestElementID()
    {
        currentSmallestElementID = currentSortedIndex;
        for (int i = currentSortedIndex; i < listToSearch.size(); ++i)
        {
            testIfSmallerAndStoreIndex(listToSearch.get(currentSmallestElementID).getContents(), listToSearch.get(i).getContents(), i);
        }
    }

    private void testIfSmallerAndStoreIndex(int storedElement, int testElement, int testIndex)
    {
        if (testElement <= storedElement)
        {
            currentSmallestElementID = testIndex;
        }
    }

    private void swapElements()
    {
        int elementToInsert = listToSearch.get(currentSmallestElementID).getContents();
        int elementAtCurrentRoot = listToSearch.get(currentSortedIndex).getContents();
        
        listToSearch.update(elementAtCurrentRoot, currentSmallestElementID);
        listToSearch.update(elementToInsert, currentSortedIndex);
    }
}
