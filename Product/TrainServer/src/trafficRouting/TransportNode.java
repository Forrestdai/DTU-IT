/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficRouting;

import java.util.ArrayList;
import java.util.Iterator;

public class TransportNode implements Iterable<Edge>
{
    private final ArrayList<Edge> edges;
    public Position position;
    public Double distanceFromGoal;
    public String identity;
    public Double cost;
    public TransportNode returnNode;

    public TransportNode(Position position, String identity)
    {
        edges = new ArrayList<>();
        this.position = position;
        this.identity = identity;
        cost = Double.POSITIVE_INFINITY;
        returnNode = this;
    }
    
    public void addEdge(Edge edge)
    {
        if (edges.contains(edge))
        {
            throw new IllegalArgumentException("The edge already exists.");
        }

        edges.add(edge);
    }
    
    @Override
    public Iterator<Edge> iterator()
    {
        return edges.iterator();
    }
    
    public Double getDistanceTo(TransportNode node)
    {
        if (node == null)
        {
            return Double.POSITIVE_INFINITY;
        }
        double xDifference = Math.abs(node.position.x - position.x);
        double yDifference = Math.abs(node.position.y - position.y);
        return Math.sqrt(xDifference * xDifference + yDifference * yDifference);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /*private Double priority;
    private String description;
    private NodeType type;
    private Double distanceToTarget;
    private ArrayList<RouteIdentifier> identifiers;

    public TransportNode(NodeType type)
    {
        this.type = type;
    }

    public Double getCost()
    {
        switch(type)
        {
            case BUSSTOP:
                
                break;
            case TRAINSTATION:
                
                break;
                
            default:
                
                break;
        }
        return priority;
    }
    
    public void addTransportIdentifier(RouteIdentifier id)
    {
        identifiers.add(id);
    }
    
    public boolean findTransportIdentifier(RouteIdentifier id)
    {
        return identifiers.contains(id);
    }*/
}
