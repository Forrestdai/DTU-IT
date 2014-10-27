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
public interface Node
{
    public int findElementLocation(int value);
    public void insertElement(int value, int index);
    public void insertBefore(int value);
    public void insertAfter(int value);
}
