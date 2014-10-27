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
public class MyLinkedList
{
    Node firstSentinel;
    Node lastSentinel;

    public MyLinkedList()
    {
        firstSentinel = new SentinelNode(false);
        lastSentinel = new SentinelNode(true);
    }
    
    public void insertElement(int index)
    {
        
    }
}
