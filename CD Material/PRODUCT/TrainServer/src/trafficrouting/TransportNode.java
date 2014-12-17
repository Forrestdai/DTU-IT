/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficrouting;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class TransportNode implements Iterable<Edge>, Serializable
{
    private ArrayList<Edge> edges;
    public Position position;
    public Double distanceFromGoal;
    public String identity;
    public Double cost;
    public TransportNode returnNode;
    public int referenceID;
    public int zone;

    public TransportNode(Position position, String identity, int ref, int zone)
    {
        edges = new ArrayList<>();
        this.position = position;
        this.identity = identity;
        cost = Double.POSITIVE_INFINITY;
        returnNode = this;
        referenceID = ref;
        this.zone = zone;
    }
    
    public void addEdge(Edge edge)
    {
        if (edges.contains(edge))
        {
            throw new IllegalArgumentException("The edge already exists.");
        }
        edges.add(edge);
    }
    
    public void setEdges(ArrayList<Edge> edges)
    {
        this.edges = edges;
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
        
        //Distance calculated with Equirectangular approximation to reduce calculation time.
        //Formula:
        //x = Δλ ⋅ cos φm
        //d = R ⋅ √x² + Δφ²
        
        double EARTH_RAD = 6367447;
        
        double latDiff = Math.toRadians(node.position.lat - position.lat);
        double lonDiff = Math.toRadians(node.position.lon - position.lon);
        
        double x = lonDiff * Math.cos(Math.toRadians((position.lat + node.position.lat) / 2));
        return Math.sqrt(x*x + latDiff*latDiff) * EARTH_RAD;
    }
}
