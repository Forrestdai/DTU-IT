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
public class MyLinkedList<E>
{

    Node<E> firstSentinel;
    Node<E> lastSentinel;

    public MyLinkedList()
    {
        firstSentinel = new SentinelFirstNode();
        lastSentinel = new SentinelLastNode();

        firstSentinel.setNextNode(lastSentinel);
        lastSentinel.setPreviousNode(firstSentinel);
    }

    public void insertElementRecursive(E value, int index)
    {
        firstSentinel.insertElementRecursive(value, index);
    }

    public void insertElementIterative(E value, int index)
    {
        if (index < 0)
        {
            firstSentinel.insertElementIterative(value);
        } else
        {
            Node<E> currentElement = firstSentinel;
            for (; index > 0; --index)
            {
                if (currentElement instanceof SentinelLastNode)
                {
                    currentElement.insertElementIterative(value);
                } else
                {
                    currentElement = currentElement.getNextNode();
                }
            }

            currentElement.insertElementIterative(value);
        }
    }

    public int findElementIndexRecursive(E value)
    {
        return firstSentinel.findElementLocationRecursive(value, 0);
    }

    public int findElementIndexIterative(E value)
    {
        Node<E> currentElement = firstSentinel.getNextNode();
        int index = 0;
        while (currentElement instanceof ValueNode && !((ValueNode) currentElement).getElementValue().equals(value))
        {
            currentElement = currentElement.getNextNode();
            ++index;
        }
        if (currentElement instanceof SentinelLastNode)
        {
            return -1;
        }
        return index;
    }

    public E getElementRecursive(int index)
    {
        return firstSentinel.getElementRecursive(index);
    }

    public E getElementIterative(int index)
    {
        Node<E> currentElement = firstSentinel.getNextNode();
        for (; index > 0; --index)
        {
            currentElement = currentElement.getNextNode();
        }

        if (currentElement instanceof ValueNode)
        {
            return (E) ((ValueNode) currentElement).getElementValue();
        }

        throw new NullPointerException("reached end of list");
    }

    public int sizeRecursive()
    {
        return firstSentinel.countElementsRecursive(0);
    }

    public void printList()
    {
        for (int i = 0; i < sizeRecursive(); ++i)
        {
            System.out.print(getElementRecursive(i) + " ");
        }
        System.out.println("");
    }

}
