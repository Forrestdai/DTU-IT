/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import collection.nodes.BetweenNodeChar;

/**
 *
 * @author JamesFoxes
 */
public interface MyCollectionChar
{
    public void addFirst(char dataToStore);
    public void addLast(char dataToStore);
    public void add(char dataToStore, int index);
    public BetweenNodeChar get(int index);
    public int size();
    public void update(char dataToAdd, int index);
    public char[] toArray();
    public MyCollectionChar createFromArray(char[] array);
    public void printArrayInLine();
    public BetweenNodeChar getFirst();
    public BetweenNodeChar getLast();
    public void removeFirst();
    public void removeLast();
    public void remove(int index);
}
