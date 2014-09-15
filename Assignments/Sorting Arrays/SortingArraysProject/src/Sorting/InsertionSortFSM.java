/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sorting;

/**
 *
 * @author James
 */
public class InsertionSortFSM implements Sortable
{

    private int[] arrayToSearch;
    private int currentSortIndex;
    private int currentExtractedElement;
    private int openSpaceIndex;

    @Override
    public int[] sort(int[] array)
    {
        arrayToSearch = array;
        currentSortIndex = 1;
        for (; currentSortIndex < arrayToSearch.length; ++currentSortIndex)
        {
            extractElement();
            if (!testElement())
            {
                shiftTestContinous();
            }
            insertElementToOpen();
        }
        return arrayToSearch;
    }

    private void extractElement()
    {
        currentExtractedElement = arrayToSearch[currentSortIndex];
        openSpaceIndex = currentSortIndex;
    }

    private boolean testElement()
    {
        if (openSpaceIndex > 0)
        {
            return currentExtractedElement >= arrayToSearch[openSpaceIndex - 1];
        }
        return true;
    }

    private void shiftTestContinous()
    {
        do
        {
            shiftElement();
        } while (!testElement() && openSpaceIndex > 0);
    }

    private void shiftElement()
    {
        arrayToSearch[openSpaceIndex] = arrayToSearch[openSpaceIndex - 1];
        --openSpaceIndex;
    }

    private void insertElementToOpen()
    {
        arrayToSearch[openSpaceIndex] = currentExtractedElement;
    }

}
