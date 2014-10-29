/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficRouting;

import java.util.Map;

/**
 *
 * @author JamesFoxes
 */
public class Graph
{

    private DirectedGraph<TransportNode> graph;
    private String goalIdentity;

    public Graph(String goalIdentity)
    {
        graph = new DirectedGraph<>();
        this.goalIdentity = goalIdentity;
    }

    public void addNodes(Map<String, TransportNode> nodes)
    {
        for (Map.Entry<String, TransportNode> entry : nodes.entrySet())
        {
            TransportNode node = entry.getValue();
            if (node.equals(nodes.get(goalIdentity)))
            {
                node.distanceFromGoal = 0.0;
            }

            node.distanceFromGoal = node.getDistanceTo(nodes.get(goalIdentity));
            node.cost = node.distanceFromGoal;
            graph.addNode(node);
        }
        addEdges();
    }

    private void addEdges()
    {
        for (TransportNode node : graph)
        {
            for (Edge edge : node)
            {
                edge.cost = calculateEdgeCost(edge);
                graph.addEdge(edge.fromNode, edge.toNode, edge.cost);
            }
        }
    }

    private Double calculateEdgeCost(Edge edge)
    {
        Double cost = Double.POSITIVE_INFINITY;
        switch (edge.edgeType)
        {
            case WALKING:
                cost = (edge.fromNode.getDistanceTo(edge.toNode)) / TransportSettings.SPEED_WALKING; //((distance in meters/100) / speed)
                break;
            case BUS:
                cost = (edge.fromNode.getDistanceTo(edge.toNode)) / TransportSettings.SPEED_BUS;
                break;
            case METRO:
                cost = (edge.fromNode.getDistanceTo(edge.toNode)) / TransportSettings.SPEED_METRO;
                break;
            case TRAIN:
                cost = (edge.fromNode.getDistanceTo(edge.toNode)) / TransportSettings.SPEED_TRAIN;
                break;
            case BOAT:
                cost = (edge.fromNode.getDistanceTo(edge.toNode)) / TransportSettings.SPEED_BOAT;
                break;
        }

        return cost;
    }

    public DirectedGraph<TransportNode> getGraph()
    {
        return graph;
    }
}
