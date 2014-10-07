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
public abstract class Node
{

    protected Node previousNode;
    protected Node nextNode;

    public void setNextNode(Node node)
    {
        nextNode = node;
    }

    public void setPreviousNode(Node node)
    {
        previousNode = node;
    }
    
    public void addNodeBefore(int dataToAdd)
    {
        Node toAdd = new BetweenNode(dataToAdd);

        toAdd.setPreviousNode(previousNode);
        previousNode.setNextNode(toAdd);

        previousNode = toAdd;
        toAdd.setNextNode(this);
    }

    public void addNodeAfter(int dataToAdd)
    {
        Node toAdd = new BetweenNode(dataToAdd);

        toAdd.setNextNode(nextNode);
        nextNode.setPreviousNode(toAdd);

        nextNode = toAdd;
        toAdd.setPreviousNode(this);
    }
    
    public Node getNextNode()
    {
        return nextNode;
    }
    
    public Node getNextNodeForced()
    {
        if(nextNode instanceof SentinelNode)
        {
            addNodeAfter(0);
        }
        return nextNode;
    }

    public Node getPreviousNode()
    {
        return previousNode;
    }

    public abstract int getContents();
    public abstract void setContents(int dataToSet);
}
