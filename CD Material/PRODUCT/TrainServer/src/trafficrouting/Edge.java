/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficrouting;

import java.io.Serializable;

/**
 *
 * @author JamesFoxes
 */
public class Edge implements Serializable
{

    public EdgeType edgeType;
    public TransportNode fromNode, toNode;
    public Double cost;
    public int transportLineReference;
    public String transportLineName;

    public Edge(EdgeType edgeType, TransportNode fromNode, TransportNode toNode, int transportLineReference, String transportLineName)
    {
        this.edgeType = edgeType;
        this.fromNode = fromNode;
        this.toNode = toNode;
        this.transportLineReference = transportLineReference;
        this.transportLineName = transportLineName;
    }

    enum EdgeType implements Serializable
    {

        TRAIN, BUS_A, BUS_E, BUS_N, BUS_S, BUS, METRO, WALKING, BOAT
    }
}
