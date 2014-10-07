/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection.nodes;

/**
 *
 * @author James
 */
public class BetweenNode extends Node
{

    private int contents;

    public BetweenNode(int contents)
    {
        this.contents = contents;
    }

    @Override
    public int getContents()
    {
        return contents;
    }

    @Override
    public void setContents(int dataToSet)
    {
        contents = dataToSet;
    }

    public void removeNode()
    {
        nextNode.setPreviousNode(previousNode);
        previousNode.setNextNode(nextNode);
    }
}
