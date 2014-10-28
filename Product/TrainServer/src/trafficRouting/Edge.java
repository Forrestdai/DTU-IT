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
public class Edge
{

    public EdgeType edgeType;
    public TransportNode fromNode, toNode;
    public Double cost;

    public Edge(EdgeType edgeType, TransportNode fromNode, TransportNode toNode)
    {
        this.edgeType = edgeType;
        this.fromNode = fromNode;
        this.toNode = toNode;
    }

    enum EdgeType
    {

        TRAIN, BUS, METRO, WALKING, BOAT
    }
}
