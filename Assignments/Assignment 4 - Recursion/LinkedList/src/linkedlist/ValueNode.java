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
public class ValueNode<E> implements Node<E>
{

    private Node<E> nextNode;
    private Node<E> prevNode;
    private E currentValue;

    @Override
    public int findElementLocationRecursive(E value, int index)
    {
        if (value == null && currentValue == null)
        {
            return index;
        }
        
        if (value.equals(currentValue))
        {
            return index;
        }
        
        return nextNode.findElementLocationRecursive(value, ++index);
    }

    @Override
    public void insertElementRecursive(E value, int index)
    {
        if (index == 0)
        {
            this.insertAfter(value);
        } else
        {
            nextNode.insertElementRecursive(value, index - 1);
        }
    }

    public void insertAfter(E value)
    {
        Node newNode = initializeNewNode(value);
        nextNode.setPreviousNode(newNode);
        nextNode = newNode;
    }
    
    private Node initializeNewNode(E value)
    {
        Node newNode = new ValueNode();
        newNode.setElementValue(value);
        
        newNode.setNextNode(nextNode);
        newNode.setPreviousNode(this);
        
        return newNode;
    }

    @Override
    public void setElementValue(E value)
    {
        currentValue = value;
    }

    @Override
    public E getElementRecursive(int index)
    {
        if (index == 0)
        {
            return currentValue;
        }
        return nextNode.getElementRecursive(--index);
    }

    @Override
    public void setPreviousNode(Node node)
    {
        prevNode = node;
    }

    @Override
    public void setNextNode(Node node)
    {
        nextNode = node;
    }

    @Override
    public int countElementsRecursive(int counter)
    {
        return nextNode.countElementsRecursive(++counter);
    }

}
