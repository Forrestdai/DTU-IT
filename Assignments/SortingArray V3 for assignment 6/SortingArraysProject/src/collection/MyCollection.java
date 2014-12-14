/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import collection.nodes.BetweenNode;

/**
 *
 * @author JamesFoxes
 */
public interface MyCollection
{
    public void addFirst(int dataToStore);
    public void addLast(int dataToStore);
    public void add(int dataToStore, int index);
    public BetweenNode get(int index);
    public int size();
    public void update(int dataToAdd, int index);
    public int[] toArray();
    public MyCollection createFromArray(int[] array);
    public void printArrayInLine();
    public BetweenNode getFirst();
    public BetweenNode getLast();
    public void removeFirst();
    public void removeLast();
    public void remove(int index);
}
