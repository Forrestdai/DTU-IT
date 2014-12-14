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
public class BetweenNodeDouble extends NodeDouble
{

    private double contents;

    public BetweenNodeDouble(double contents)
    {
        this.contents = contents;
    }

    @Override
    public double getContents()
    {
        return contents;
    }

    @Override
    public void setContents(double dataToSet)
    {
        contents = dataToSet;
    }

    public void removeNode()
    {
        nextNode.setPreviousNode(previousNode);
        previousNode.setNextNode(nextNode);
    }
}
