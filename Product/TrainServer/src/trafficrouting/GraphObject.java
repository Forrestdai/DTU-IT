/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficrouting;

import helpers.Zone;
import java.io.Serializable;
import java.util.Map;

/**
 *
 * @author JamesFoxes
 */
public class GraphObject
{

    private GraphTransmitObject toTransfer;

    public GraphObject(GraphTransmitObject graph)
    {
        this.toTransfer = graph;
    }

    public DirectedGraph<TransportNode> getGraph(int goalIdentity)
    {
        Graph graph = new Graph(goalIdentity);
        graph.addNodes(toTransfer.nodes);
        return graph.getDirectedGraph();
    }
    
    public Map<Integer, Zone> getZones()
    {
        return toTransfer.zoneMap;
    }
    
    public Map<Integer, TransportNode> getNodes()
    {
        return toTransfer.nodes;
    }

    public static class GraphTransmitObject implements Serializable
    {

        public final Map<Integer, TransportNode> nodes;
        public final Map<Integer, Zone> zoneMap;

        public GraphTransmitObject(Map<Integer, TransportNode> nodes, Map<Integer, Zone> zoneMap)
        {
            this.nodes = nodes;
            this.zoneMap = zoneMap;
        }
    }
}
