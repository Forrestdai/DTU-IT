package Sorting;

public class SelectionSort implements Sortable
{
    private int[] arrayToSearch;
    private int currentSortedIndex;
    private int currentSmallestElementID;

    @Override
    public int[] sort(int[] arrayToSort)
    {
        this.arrayToSearch = arrayToSort;
        currentSortedIndex = 0;
        for (; currentSortedIndex < arrayToSearch.length - 1; ++currentSortedIndex)
        {
            searchSmallestElementID();
            swapElements();
        }
        return arrayToSearch;
    }

    private void searchSmallestElementID()
    {
        currentSmallestElementID = currentSortedIndex;
        for (int i = currentSortedIndex; i < arrayToSearch.length; ++i)
        {
            testIfSmallerAndStoreIndex(arrayToSearch[currentSmallestElementID], arrayToSearch[i], i);
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
        int elementPlaceHolder = arrayToSearch[currentSmallestElementID];
        arrayToSearch[currentSmallestElementID] = arrayToSearch[currentSortedIndex];
        arrayToSearch[currentSortedIndex] = elementPlaceHolder;
    }
}
