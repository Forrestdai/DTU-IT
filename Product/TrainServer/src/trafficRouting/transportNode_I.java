/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficRouting;

/**
 *
 * @author JamesFoxes
 */
public interface transportNode_I
{
    public boolean findTransportIdentifier(RouteIdentifier id);
    public Double getCost();
    public boolean equals(transportNode_I toCompare);
}
