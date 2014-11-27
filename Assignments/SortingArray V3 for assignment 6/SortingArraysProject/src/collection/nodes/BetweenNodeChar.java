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
public class BetweenNodeChar extends NodeChar
{

    private char contents;

    public BetweenNodeChar(char contents)
    {
        this.contents = contents;
    }

    @Override
    public char getContents()
    {
        return contents;
    }

    @Override
    public void setContents(char dataToSet)
    {
        contents = dataToSet;
    }

    public void removeNode()
    {
        nextNode.setPreviousNode(previousNode);
        previousNode.setNextNode(nextNode);
    }
}
