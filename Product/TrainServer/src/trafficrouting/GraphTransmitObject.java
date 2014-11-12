/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficrouting;

import java.io.Serializable;
import java.util.Map;

/**
 *
 * @author JamesFoxes
 */
public class GraphTransmitObject implements Serializable
{
    private final Map<Integer, TransportNode> nodes;

    public GraphTransmitObject(Map<Integer, TransportNode> nodes)
    {
        this.nodes = nodes;
    }
    
    public DirectedGraph<TransportNode> getGraph(int goalIdentity)
    {
        Graph graph = new Graph(goalIdentity);
        graph.addNodes(nodes);
        return graph.getDirectedGraph();
    }
}
