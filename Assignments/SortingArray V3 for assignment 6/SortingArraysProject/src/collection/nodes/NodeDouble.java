/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection.nodes;

/**
 *
 * @author JamesFoxes
 */
public abstract class NodeDouble
{

    protected NodeDouble previousNode;
    protected NodeDouble nextNode;

    public void setNextNode(NodeDouble node)
    {
        nextNode = node;
    }

    public void setPreviousNode(NodeDouble node)
    {
        previousNode = node;
    }
    
    public void addNodeBefore(double dataToAdd)
    {
        NodeDouble toAdd = new BetweenNodeDouble(dataToAdd);

        toAdd.setPreviousNode(previousNode);
        previousNode.setNextNode(toAdd);

        previousNode = toAdd;
        toAdd.setNextNode(this);
    }

    public void addNodeAfter(double dataToAdd)
    {
        NodeDouble toAdd = new BetweenNodeDouble(dataToAdd);

        toAdd.setNextNode(nextNode);
        nextNode.setPreviousNode(toAdd);

        nextNode = toAdd;
        toAdd.setPreviousNode(this);
    }
    
    public NodeDouble getNextNode()
    {
        return nextNode;
    }
    
    public NodeDouble getNextNodeForced()
    {
        if(nextNode instanceof SentinelNodeDouble)
        {
            addNodeAfter('0');
        }
        return nextNode;
    }

    public NodeDouble getPreviousNode()
    {
        return previousNode;
    }

    public abstract double getContents();
    public abstract void setContents(double dataToSet);
}
