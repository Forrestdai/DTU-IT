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
public class SentinelFirstNode<E> implements Node<E>
{

    private Node<E> nextNode;

    @Override
    public int findElementLocationRecursive(E value, int index)
    {
        return nextNode.findElementLocationRecursive(value, 0);
    }

    @Override
    public void insertElementRecursive(E value, int index)
    {
        if (index <= 0)
        {
            insertAfter(value);
            return;
        }

        nextNode.insertElementRecursive(value, index - 1);
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
    }

    @Override
    public E getElementRecursive(int index)
    {
        return nextNode.getElementRecursive(index);
    }

    @Override
    public void setPreviousNode(Node node)
    {
    }

    @Override
    public void setNextNode(Node node)
    {
        nextNode = node;
    }

    @Override
    public int countElementsRecursive(int counter)
    {
        return nextNode.countElementsRecursive(counter);
    }

    @Override
    public Node<E> getNextNode()
    {
        return nextNode;
    }

    @Override
    public Node<E> getPreviousNode()
    {
        return null;
    }

    @Override
    public void insertElementIterative(E value)
    {
        insertAfter(value);
    }
}
