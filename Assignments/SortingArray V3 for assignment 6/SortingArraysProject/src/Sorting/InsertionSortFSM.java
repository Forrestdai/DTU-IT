/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sorting;

import collection.MyCollection;

/**
 *
 * @author James
 */
public class InsertionSortFSM implements Sortable
{

    private MyCollection arrayToSearch;
    private int currentSortIndex;
    private int currentExtractedElement;
    private int openSpaceIndex;

    @Override
    public MyCollection sort(MyCollection arrayToSort)
    {
        arrayToSearch = arrayToSort;
        currentSortIndex = 1;
        for (; currentSortIndex < arrayToSearch.size(); ++currentSortIndex)
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
        currentExtractedElement = arrayToSearch.get(currentSortIndex).getContents();
        openSpaceIndex = currentSortIndex;
    }

    private boolean testElement()
    {
        if (openSpaceIndex > 0)
        {
            return currentExtractedElement >= arrayToSearch.get(openSpaceIndex - 1).getContents();
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
        int elementToShift = arrayToSearch.get(openSpaceIndex - 1).getContents();
        arrayToSearch.update(elementToShift, openSpaceIndex);
        --openSpaceIndex;
    }

    private void insertElementToOpen()
    {
        arrayToSearch.update(currentExtractedElement, openSpaceIndex);
    }

}
