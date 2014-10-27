/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficRouting;

import java.util.ArrayList;

/**
 *
 * @author JamesFoxes
 */
public class TransportNode
{
    private Double priority;
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
    }
}
