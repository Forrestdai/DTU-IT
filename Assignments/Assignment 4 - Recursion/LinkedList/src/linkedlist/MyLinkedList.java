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
    
    public void insertElement(E value, int index)
    {
        firstSentinel.insertElementRecursive(value, index);
    }
    
    public int findElementIndex(E value)
    {
        return firstSentinel.findElementLocationRecursive(value, 0);
    }

    public E getElement(int index)
    {
        return firstSentinel.getElementRecursive(index);
    }

    public int size()
    {
        return firstSentinel.countElementsRecursive(0);
    }
    
    public void printList()
    {
        for (int i = 0; i < size(); ++i)
        {
            System.out.print(getElement(i) + " ");
        }
        System.out.println("");
    }
}
