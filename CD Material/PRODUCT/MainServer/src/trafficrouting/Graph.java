/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficrouting;

import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author JamesFoxes
 */
public class Graph
{

    private DirectedGraph<TransportNode> graph;
    private Integer goalIdentity;

    public Graph(int goalIdentity)
    {
        graph = new DirectedGraph<>();
        this.goalIdentity = goalIdentity;
    }

    public void addNodes(Map<Integer, TransportNode> nodes)
    {
        for (Map.Entry<Integer, TransportNode> entry : nodes.entrySet())
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
            for (Iterator<Edge> iterator = node.iterator(); iterator.hasNext();)
            {
                Edge edge = iterator.next();
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
                cost = (edge.fromNode.getDistanceTo(edge.toNode)) / TransportSettings.SPEED_WALKING; //((distance in meters) / speed)
                break;
            case BUS:
                cost = (edge.fromNode.getDistanceTo(edge.toNode)) / TransportSettings.SPEED_BUS;
                break;
            case BUS_A:
                cost = (edge.fromNode.getDistanceTo(edge.toNode)) / TransportSettings.SPEED_BUS_A;
                break;
            case BUS_E:
                cost = (edge.fromNode.getDistanceTo(edge.toNode)) / TransportSettings.SPEED_BUS_E;
                break;
            case BUS_N:
                cost = (edge.fromNode.getDistanceTo(edge.toNode)) / TransportSettings.SPEED_BUS_N;
                break;
            case BUS_S:
                cost = (edge.fromNode.getDistanceTo(edge.toNode)) / TransportSettings.SPEED_BUS_S;
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

    public DirectedGraph<TransportNode> getDirectedGraph()
    {
        return graph;
    }
}
