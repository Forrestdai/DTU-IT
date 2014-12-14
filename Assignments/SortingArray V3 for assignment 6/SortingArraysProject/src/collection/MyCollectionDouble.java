/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import collection.nodes.BetweenNodeDouble;

/**
 *
 * @author JamesFoxes
 */
public interface MyCollectionDouble
{
    public void addFirst(double dataToStore);
    public void addLast(double dataToStore);
    public void add(double dataToStore, int index);
    public BetweenNodeDouble get(int index);
    public int size();
    public void update(double dataToAdd, int index);
    public double[] toArray();
    public MyCollectionDouble createFromArray(double[] array);
    public void printArrayInLine();
    public BetweenNodeDouble getFirst();
    public BetweenNodeDouble getLast();
    public void removeFirst();
    public void removeLast();
    public void remove(int index);
}
