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
public interface Node<E>
{

    public void setElementValue(E value);
    
    public void setPreviousNode(Node node);

    public void setNextNode(Node node);
    
    //Recursive
    public int findElementLocationRecursive(E value, int index);

    public void insertElementRecursive(E value, int index);

    public E getElementRecursive(int index);

    public int countElementsRecursive(int counter);
    
    //Iterative
}
