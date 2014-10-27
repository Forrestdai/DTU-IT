/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedlist;

/**
 *
 * @author JamesFoxes
 */
public class ElementNode implements Node
{
    private Node nextNode;
    private Node prevNode;
    private int currentValue;
    private int currentIndex;

    @Override
    public int findElementLocation(int value)
    {
        if(value == currentValue)
        {
            return currentIndex;
        }
        
        return nextNode.findElementLocation(value);
    }

    @Override
    public void insertElement(int value, int index)
    {
        if(index < 0)
        {
            this.insertBefore(value);
        }
        if(index == 0)
        {
            this.insertAfter(currentValue);
        } else
        {
            nextNode.insertElement(value, index - 1);
        }
    }

    @Override
    public void insertBefore(int value)
    {
    }

    @Override
    public void insertAfter(int value)
    {
    }
    
}
