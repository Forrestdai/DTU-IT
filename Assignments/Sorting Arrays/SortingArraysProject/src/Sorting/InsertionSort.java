package Sorting;

public class InsertionSort implements Sortable
{

    private int[] arrayToSearch;
    private int currentSortIndex;
    private int currentInsertElementID;

    @Override
    public int[] sort(int[] array)
    {
        arrayToSearch = array;
        currentSortIndex = 1;
        for (; currentSortIndex < arrayToSearch.length; ++currentSortIndex)
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
        return arrayToSearch[currentInsertElementID] < arrayToSearch[currentInsertElementID - 1];
    }

    private void shiftOneElementDown()
    {
        int smallElementPlaceHolder = arrayToSearch[currentInsertElementID];
        arrayToSearch[currentInsertElementID] = arrayToSearch[currentInsertElementID - 1];
        arrayToSearch[currentInsertElementID - 1] = smallElementPlaceHolder;
    }
}
