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
public abstract class NodeChar
{

    protected NodeChar previousNode;
    protected NodeChar nextNode;

    public void setNextNode(NodeChar node)
    {
        nextNode = node;
    }

    public void setPreviousNode(NodeChar node)
    {
        previousNode = node;
    }
    
    public void addNodeBefore(char dataToAdd)
    {
        NodeChar toAdd = new BetweenNodeChar(dataToAdd);

        toAdd.setPreviousNode(previousNode);
        previousNode.setNextNode(toAdd);

        previousNode = toAdd;
        toAdd.setNextNode(this);
    }

    public void addNodeAfter(char dataToAdd)
    {
        NodeChar toAdd = new BetweenNodeChar(dataToAdd);

        toAdd.setNextNode(nextNode);
        nextNode.setPreviousNode(toAdd);

        nextNode = toAdd;
        toAdd.setPreviousNode(this);
    }
    
    public NodeChar getNextNode()
    {
        return nextNode;
    }
    
    public NodeChar getNextNodeForced()
    {
        if(nextNode instanceof SentinelNodeChar)
        {
            addNodeAfter('0');
        }
        return nextNode;
    }

    public NodeChar getPreviousNode()
    {
        return previousNode;
    }

    public abstract char getContents();
    public abstract void setContents(char dataToSet);
}
